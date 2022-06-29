import java.awt.*;
import java.util.*;

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
        random_number = getRandomNumber(0, 2);
        if (random_number == 0) {
            // discard
            /** Decide which card */
            random_number = getRandomNumber(0, 8);
            outgoing_card = getCardAt(random_number);
            removeCard(outgoing_card);
            discards.addCard(outgoing_card);
            System.out.print("AI chose to discard ");

        } else {
            // play
            /** Decide which card */
            random_number = getRandomNumber(0, 8);
            outgoing_card = getCardAt(random_number);
            removeCard(outgoing_card);
            placeCard(outgoing_card);
            System.out.print("AI chose to place ");
        }
        outgoing_card.display();

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
        ArrayList<CardsCollection> potential_placed_cards = new ArrayList<>(colors.length);

        // remove ineligible cards
        for (int i = 0; i < potential_placed_cards.size(); i++) {
            potential_placed_cards.get(i).makeColorPile(colors[i]);

            for (int j = 0; j < potential_placed_cards.size(); j++) {
                Card c = potential_placed_cards.get(i).getCardAt(j);

                if (!isPlaced(c) && !isInHand(c) && !discards.isTopCard(c)) {
                    // if the card is not in AI's hand AND is not placed down AND is not a top card
                    // in the discard pile, it will not contribute full points to the potential
                    // placed down score
                    c.setCardNumber(c.getCardNumber() * ((double) undealt.size() / 100));

                    if (opponent_placed_down.get(i).contains(c)
                            || c.getCardNumber() < getTopPlacedCard(colors[i]).getCardNumber()) {
                        // if the card is placed down by opponent OR less than the highest placed card,
                        // it cannot contribute any points to the potential placed down score
                        potential_placed_cards.get(i).removeCard(c);
                    }
                }
            }
        }

        // int potential_total_score = calculateScore(potential_placed_cards);

        return new Card();
    }

    /* PROTECTED FUNCTIONS */

    protected int calculateScore(ArrayList<CardsCollection> potential_placed_cards) {
        // TODO
        return 0;
    }
}