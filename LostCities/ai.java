import java.awt.*;
import java.util.*;

public class Ai extends Player {
    Random rand = new Random(0);

    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    ArrayList<Double> good_cards;

    Ai() {
        good_cards = new ArrayList<>(hand.size());
        for (int i = 0; i < good_cards.size(); i++) {
            good_cards.set(i, 100.00);
        }
    }

    // Returns true because player is AI
    public boolean isAI() {
        return true;
    }

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
            System.out.println("\n AI took card from undealt pile only because there are no cards in discard piles");
            incoming_card = undealt.getTopCard();
            undealt.removeCard(incoming_card);
        } else {
            /** Decide to take from undealt or one of the discard piles */
            random_number = getRandomNumber(0, 2);
            if (random_number == 0) {
                System.out.print("AI chose discard pile.\n");

                /** Decide which pile if discard piles */
                random_number = getRandomNumber(0, 5);
                incoming_card = discards.getCard(colors[random_number]);
                discards.removeCard(incoming_card);
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

    /** Return random number between min and max */
    private int getRandomNumber(int min, int max) {
        return rand.ints(min, max).findFirst().getAsInt();
    }
}