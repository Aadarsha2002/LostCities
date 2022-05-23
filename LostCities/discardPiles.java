package LostCities;

import java.util.ArrayList;
import java.awt.*;

public class discardPiles {
    ArrayList<cards> discard_piles = new ArrayList<>();
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
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
        return discard_piles.get(getDiscardPileIndex(col)).getTopCard();
    }

    // removes the top card
    public void removeCard(Color col) {
        discard_piles.get(getDiscardPileIndex(col)).removeCard(getCard(col));
    }

    // OVERLOADED FUNCTION to verify and remove the top card
    public void removeCard(card c) {
        if (isTopCard(c))
            removeCard(c.getCardColor());
    }

    public card getCard(String picked_color) {
        if (picked_color.charAt(0) == getColorName(col[0]).charAt(0)) {
            return getCard(Color.yellow);
        } else if (picked_color.charAt(0) == getColorName(col[1]).charAt(0)) {
            return getCard(Color.blue);
        } else if (picked_color.charAt(0) == getColorName(col[2]).charAt(0)) {
            return getCard(Color.white);
        } else if (picked_color.charAt(0) == getColorName(col[3]).charAt(0)) {
            return getCard(Color.green);
        } else if (picked_color.charAt(0) == getColorName(col[4]).charAt(0)) {
            return getCard(Color.red);
        }
        return new card();
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

    private boolean isTopCard(card c) {
        return c == getCard(c.getCardColor()) ? true : false;
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

    private String getColorName(Color c) {
        if (c == col[0]) {
            return "Yellow";
        } else if (c == col[1]) {
            return "Blue";
        } else if (c == col[2]) {
            return "White";
        } else if (c == col[3]) {
            return "Green";
        } else if (c == col[4]) {
            return "Red";
        }
        return "";
    }
}
