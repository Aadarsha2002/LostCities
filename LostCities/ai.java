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

        System.out.print("\nYour hand is now ");
        display();
        /** Decide to take from undealt or one of the discard piles */
        /** Decide which pile if discard piles */
    }

    /** Return random number between min and max */
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}