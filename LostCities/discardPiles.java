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

    public card getCard(String col) {
        
    }

    public void addCard(card c) {
        discard_piles.get(getDiscardPileIndex(c.getCardColor())).addCard(c);
    }

    public void displayPiles() {
        System.out.println("Discard Piles: ");
        System.out.print("Yellow: ");
        discard_piles.get(getDiscardPileIndex(Color.yellow)).display();
        System.out.print("Blue: ");
        discard_piles.get(getDiscardPileIndex(Color.blue)).display();
        System.out.print("White: ");
        discard_piles.get(getDiscardPileIndex(Color.white)).display();
        System.out.print("Green: ");
        discard_piles.get(getDiscardPileIndex(Color.green)).display();
        System.out.print("Red: ");
        discard_piles.get(getDiscardPileIndex(Color.red)).display();
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
