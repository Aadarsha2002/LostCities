import java.awt.*;
import java.util.ArrayList;

public class ai extends player {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    // Returns true because player is AI
    public boolean isAI() {
        return true;
    }

    @Override
    public void play(player opponent, discardPiles discards, cards undealt) {
        ArrayList<Double> optimal_cards = new ArrayList<>(hand.size());

    }
}