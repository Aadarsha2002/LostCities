package LostCities;

import java.util.ArrayList;
import java.awt.*;
/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)
    
    List of discard piles
*/

public class discardPiles {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    ArrayList<cards> discard_piles = new ArrayList<>(5);
    /*
     * discard_piles[0] = yellow
     * discard_piles[0] = blue
     * discard_piles[0] = white
     * discard_piles[0] = green
     * discard_piles[0] = red
     */

    /**
     * CONSTRUCTOR
     * Make discard piles (5 piles)
     */
    public discardPiles() {
        discard_piles = new ArrayList<>(5);
        for (cards c : discard_piles) {
            c = new cards('D');
        }
    }

    /** Return topmost card */
    public card getCard(Color col) {
        return discard_piles.get(getDiscardPileIndex(col)).getTopCard();
    }

    /**
     * OVERLOAD FUNCTION
     * Returns the topmost card from the given color's discard pile
     * (color passed as String)
     */
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

    /** Remove topmost card from given color's discard pile */
    public void removeCard(Color col) {
        discard_piles.get(getDiscardPileIndex(col)).removeCard(getCard(col));
    }

    /**
     * OVERLOAD FUNCTION
     * Takes in a card
     * Verifies it's the topmost card
     * if it is, removes it
     */
    public void removeCard(card c) {
        if (isTopCard(c))
            removeCard(c.getCardColor());
    }

    /** Insert card into the appropriate discard pile */
    public void addCard(card c) {
        discard_piles.get(getDiscardPileIndex(c.getCardColor())).addCard(c);
    }

    /** Output discard piles to console */
    public void displayPiles() {
        System.out.println("Discard Piles: ");
        System.out.print("Yellow: ");
        System.out.println(discard_piles.get(getDiscardPileIndex(Color.yellow)).size());
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

    /** Return true if given card is topmost card */
    private boolean isTopCard(card c) {
        return c == getCard(c.getCardColor()) ? true : false;
    }

    /** Return index of discard pile according to given color */
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

    /** Return string form of color passed as parameter */
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
