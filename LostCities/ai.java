import java.awt.*;
import java.util.ArrayList;

public class ai extends Player {
    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    ArrayList<Double> good_cards;

    ai() {
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
    public void play(Player opponent, discardPiles discards, CardsCollection undealt) {

    }
}