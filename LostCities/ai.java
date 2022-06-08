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
        ArrayList<Boolean> placeable = new ArrayList<>(hand.size());
        ArrayList<Integer> scores = new ArrayList<>(hand.size());
        // set the lowest number card in each color as placeable
        for (Color color : col) {
            ArrayList<card> cards = hand.getCardsbyColor(color);
            if (cards.size() > 1) {
                placeable.set(getCardIndex(cards.get(0)), true);
            }
            for (card c : cards) {
                if (c == cards.get(0))
                    continue;
                placeable.set(getCardIndex(cards.get(0)), true);
            }
        }
        for (int i = 0; i < scores.size(); i++) {
            if (placeable.get(i).equals(false))
                scores.set(i, Integer.MIN_VALUE);
            else {
                placeCard(hand.getCardAt(i));
                scores.set(i, getColorScore(getIndex(hand.getCardAt(i).getCardColor())));
            }
        }
        ArrayList<Integer> cards_color_counts = getCardCountsByColor();

    }
}