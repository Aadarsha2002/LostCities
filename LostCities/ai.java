import java.awt.*;
import java.util.*;

public class Ai extends Player {
    Random rand = new Random(0);

    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

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
    public void play(Player opponent, DiscardPiles discards, CardsCollection undealt) {
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
            System.out.println("\nAI took card from undealt pile only because there are no cards in discard piles");
            incoming_card = undealt.getTopCard();
            undealt.removeCard(incoming_card);
        } else {
            /** Decide to take from undealt or one of the discard piles */
            random_number = getRandomNumber(0, 2);
            if (random_number == 0) {

                /** Decide which pile if discard piles */
                random_number = getRandomNumber(0, 5);
                incoming_card = discards.getCard(colors[random_number]);
                while (incoming_card.getCardColor() == Color.black) {
                    random_number = getRandomNumber(0, 5);
                    incoming_card = discards.getCard(colors[random_number]);
                }
                if (incoming_card == outgoing_card) {
                    incoming_card = undealt.getTopCard();
                    undealt.removeCard(incoming_card);
                    System.out.print("AI chose undealt pile\n");
                } else {
                    discards.removeCard(incoming_card);
                    System.out.print("AI chose discard pile.\n");
                }
            } else {
                incoming_card = undealt.getTopCard();
                undealt.removeCard(incoming_card);
                System.out.print("AI chose undealt pile\n");
            }

        }

        System.out.print("AI's getting ");
        incoming_card.display();
        addCard(incoming_card);// add the card to player's hand

        System.out.print("\nAI's hand is now ");
        display();
    }

    public Card outgoingPlay(Player opponent, DiscardPiles discards, CardsCollection undealt) {
        return new Card();
    }

}