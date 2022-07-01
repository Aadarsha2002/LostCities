import java.awt.*;
import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Ai extends Player {
    Random rand = new Random(0);

    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    ArrayList<Double> good_cards;

    /* CONSTRUCTORS */
    Ai() {
        good_cards = new ArrayList<>(hand.size());
        for (int i = 0; i < good_cards.size(); i++) {
            good_cards.set(i, 100.00);
        }
    }
    /* GETTER FUNCTIONS */

    /* Returns true if string is "ai" or something similar */
    @Override
    public boolean isIt(String s) {
        return s == "ai" || s == "AI" || s == "Ai";
    }

    /** Return random number between min and max */
    private int getRandomNumber(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    /* AUXILIARY FUNCTIONS */

    /* Conducts the turn if called on an ai object */
    @Override
    public void play(ArrayList<CardsCollection> opponent_placed_down, DiscardPiles discards, CardsCollection undealt) {
        int random_number = 0;
        Card outgoing_card;
        /** Decide to discard or play */
        // random_number = getRandomNumber(0, 2);
        // if (random_number == 0) {
        // // discard
        // /** Decide which card */
        // random_number = getRandomNumber(0, 8);
        // outgoing_card = getCardAt(random_number);
        // removeCard(outgoing_card);
        // discards.addCard(outgoing_card);
        // System.out.print("AI chose to discard ");

        // } else {
        // // play
        // /** Decide which card */
        // random_number = getRandomNumber(0, 8);
        // outgoing_card = getCardAt(random_number);
        // removeCard(outgoing_card);
        // placeCard(outgoing_card);
        // System.out.print("AI chose to place ");
        // }
        // outgoing_card.display();

        outgoing_card = outgoingPlay(opponent_placed_down, discards, undealt);

        System.out.print("\nAI's hand is now ");
        display();

        Card incoming_card;
        if (discards.isEmpty()) {
            /**
             * if AI can take a card from undealt pile only because there are no cards
             * in discard pile
             */
            System.out.println("\nAI took card from draw pile only because there are no cards in discard piles");
            incoming_card = undealt.getTopCard();
            undealt.removeCard(incoming_card);
        } else {
            /** Decide to take from undealt or one of the discard piles */
            random_number = getRandomNumber(0, 2);
            if (random_number == 0) {

                /** Decide which pile if discard piles */
                random_number = getRandomNumber(0, 5);
                incoming_card = discards.getTopCard(colors[random_number]);
                while (incoming_card.getCardColor() == Color.black) {
                    random_number = getRandomNumber(0, 5);
                    incoming_card = discards.getTopCard(colors[random_number]);
                }
                if (incoming_card == outgoing_card) {
                    incoming_card = undealt.getTopCard();
                    undealt.removeCard(incoming_card);
                    System.out.print("AI chose draw pile\n");
                } else {
                    discards.removeCard(incoming_card);
                    System.out.print("AI chose discard pile.\n");
                }
            } else {
                incoming_card = undealt.getTopCard();
                undealt.removeCard(incoming_card);
                System.out.print("AI chose draw pile\n");
            }

        }

        System.out.print("AI's getting ");
        incoming_card.display();
        addCard(incoming_card);// add the card to player's hand

        System.out.print("\nAI's hand is now ");
        display();
    }

    /*
     * General Approach:
     * - Calculate a potential score based on the cards known and unknown
     * - If it's greater than the opponent's potential score, place the best card
     * - If it's not, then discard the worst
     */
    public Card outgoingPlay(ArrayList<CardsCollection> opponent_placed_down, DiscardPiles discards,
            CardsCollection undealt) {

        ArrayList<CardsCollection> potential_placed_cards = new ArrayList<>();

        // From a collection of ALL cards remove ineligible-to-place cards, change
        // numbers of undealt cards to be a percentage of themselves, and remove the
        // cards in your hand
        for (int i = 0; i < colors.length; i++) {
            potential_placed_cards.add(new CardsCollection());
            potential_placed_cards.get(i).makeColorPile(colors[i]);

            for (int j = 0; j < potential_placed_cards.get(i).size(); j++) {
                Card c = potential_placed_cards.get(i).getCardAt(j);
                potential_placed_cards.get(i).removeCard(c);

                if (!isPlaced(c)) {
                    // if the card is not placed down, it will contribute PARTIAL points to the
                    // potential placed down score
                    double percentage = (double) undealt.size() / 100;
                    c.setCardNumber(c.getCardNumber() * percentage);
                    potential_placed_cards.get(i).addCard(c);
                    if (c.getCardNumber() == 0 || opponent_placed_down.get(i).contains(c)
                            || c.getCardNumber() < getTopPlacedCard(colors[i]).getCardNumber() || isInHand(c)) {
                        // if the card is placed down by opponent OR less than the highest placed card
                        // OR is in AI's hand, it will contribute NOTHING to the potential placed down
                        // score
                        potential_placed_cards.get(i).removeCard(c);
                    }
                }
            }
        }
        System.out.println("Potential Placed Cards: ");
        for (int i = 0; i < potential_placed_cards.size(); i++) {
            potential_placed_cards.get(i).display();
        }

        ArrayList<Double> placing_expected_score = new ArrayList<>();
        ArrayList<Double> discarding_expected_score = new ArrayList<>();

        // for each card in the hand, calculate an expected score for placing it and
        // discarding it and add it to the appropriate ArrayList
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.getCardAt(i);

            // discarding it
            double total = 0;
            for (Color col : colors) {
                total += potential_placed_cards.get(getColorIndex((col))).getScore();
            }
            // display expected score
            discarding_expected_score.add(total);
            c.display();
            System.out.println("'s discarding exp-score " + (float) total);

            // placing it
            CardsCollection placeable_cards_in_hand = hand.getCardsbyColor(c.getCardColor());
            for (int j = 0; j < placeable_cards_in_hand.size(); j++) {
                if (placeable_cards_in_hand.getCardAt(j).getCardNumber() < c.getCardNumber()) {
                    placeable_cards_in_hand.removeCard(placeable_cards_in_hand.getCardAt(j));
                }
            }
            potential_placed_cards.get(getColorIndex(c.getCardColor())).addCards(placeable_cards_in_hand);
            total = 0;
            for (Color col : colors) {
                total += potential_placed_cards.get(getColorIndex(col)).getScore();
            }
            // display expected score
            placing_expected_score.add(total);
            c.display();
            System.out.println("'s placing exp-score " + (float) total);
        }
        int placing_max_index = 0;
        int discarding_max_index = 0;
        for (int i = 0; i < placing_expected_score.size(); i++) {
            if (placing_expected_score.get(i) > placing_expected_score.get(placing_max_index))
                placing_max_index = i;
            if (discarding_expected_score.get(i) > discarding_expected_score.get(discarding_max_index))
                discarding_max_index = i;
        }

        Card outgoing_card = new Card();
        if (discarding_expected_score.get(discarding_max_index) > placing_expected_score.get(placing_max_index)) {
            // discard the card when discarding gives a higher score than placing it
            outgoing_card = hand.getCardAt(discarding_max_index);
            System.out.print("AI discarded ");
            outgoing_card.display();
            removeCard(outgoing_card);
            discards.addCard(outgoing_card);
        } else {
            // place the card when placing it gives a higher score than discarding it
            outgoing_card = hand.getCardAt(placing_max_index);
            System.out.print("AI placed ");
            outgoing_card.display();
            removeCard(outgoing_card);
            placeCard(outgoing_card);
        }
        return outgoing_card;
    }
}