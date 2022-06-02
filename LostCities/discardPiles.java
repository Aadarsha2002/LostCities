import java.awt.*;
import java.util.*;
/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)
    
    List of discard piles
*/

public class discardPiles {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    ArrayList<cards> discard_piles = new ArrayList<>();
    /*
     * discard_piles[0] = yellow
     * discard_piles[1] = blue
     * discard_piles[2] = white
     * discard_piles[3] = green
     * discard_piles[4] = red
     */

    /**
     * CONSTRUCTOR
     * Make discard piles (5 piles)
     */
    public discardPiles() {

        discard_piles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            discard_piles.add(new cards('d'));
        }
    }

    /** Return the corresponding color's discard pile */
    public cards getPile(Color col) {
        return discard_piles.get(getIndex(col));
    }

    /**
     * OVERLOAD FUNCTION
     * Return the corresponding color's discard pile (color passed as String)
     */
    public cards getPile(String picked_color) {
        for (Color c : col) {
            if (isColorsMatching(picked_color, c))
                return discard_piles.get(getIndex(c));
        }
        return new cards();
    }

    /** Return topmost card */
    public card getCard(Color col) {
        if (!discard_piles.get(getIndex(col)).isEmpty())
            return discard_piles.get(getIndex(col)).getTopCard();
        return new card();
    }

    /**
     * OVERLOAD FUNCTION
     * Returns the topmost card from the given color's discard pile
     * (color passed as String)
     */
    public card getCard(String picked_color) {
        for (Color c : col) {
            if (!discard_piles.get(getIndex(c)).isEmpty()
                    && isColorsMatching(picked_color, c))
                return getCard(c);
        }
        return new card();
    }

    /** Remove topmost card from given color's discard pile */
    public void removeCard(Color col) {
        discard_piles.get(getIndex(col)).removeCard(getCard(col));
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
        discard_piles.get(getIndex(c.getCardColor())).addCard(c);
    }

    /** Output discard piles to console */
    public void displayPiles() {
        System.out.println("Discard Piles: ");
        for (Color c : col) {
            System.out.print(getColorName(c) + ":\t");
            discard_piles.get(getIndex(c)).display();
        }
    }

    /**
     * Return true if all discard piles are empty
     */
    public boolean isEmpty() {
        for (cards c : discard_piles) {
            if (!c.isEmpty())
                return false;
        }
        return true;
    }

    /** Return true if given card is topmost card */
    private boolean isTopCard(card c) {
        return c == getCard(c.getCardColor());
    }

    /**
     * Return true if the given string form of the color matches the given color
     */
    private boolean isColorsMatching(String picked_color, Color col) {
        return Character.toLowerCase(picked_color.charAt(0)) == Character.toLowerCase(getColorName(col).charAt(0));
    }

    /** Return index of discard pile according to given color */
    private int getIndex(Color col) {
        for (int i = 0; i < 5; i++) {
            if (this.col[i] == col)
                return i;
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
