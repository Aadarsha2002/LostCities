package LostCities;

import java.util.ArrayList;
import java.awt.*;

public class discardPiles {
    ArrayList<cards> discard_piles = new ArrayList<>();
    /*
     * discard_piles[0] = yellow
     * discard_piles[0] = blue
     * discard_piles[0] = white
     * discard_piles[0] = green
     * discard_piles[0] = red
     */

    // Constructor
    public discardPiles() {
        discard_piles = new ArrayList<>(5);
        for (cards c : discard_piles) {
            c = new cards('D');
        }
    }

    // get the top card
    public card getCard(Color col) {
        return discard_piles.get(getDiscardPileIndex(col)).getCard();
    }

    // Return the index of the discard pile according to its color
    private int getDiscardPileIndex(Color col) {
        if (col == Color.yellow) {
            return 0;
        } else if (col == Color.blue) {
            return 1;
        } else if (col == Color.white) {
            return 2;
        } else if (col == Color.green) {
            return 3;
        } else if (col == Color.red) {
            return 4;
        }
        return -1;
    }
}
