import java.awt.*;
import java.util.*;
/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)
    
    List of discard piles
*/

public class DiscardPiles {
    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    ArrayList<CardsCollection> discard_piles = new ArrayList<>();
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
    public DiscardPiles() {

        discard_piles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            discard_piles.add(new CardsCollection('d'));
        }
    }

    /** Return the corresponding color's discard pile */
    public CardsCollection getPile(Color col) {
        return discard_piles.get(getIndex(col));
    }

    /**
     * OVERLOAD FUNCTION
     * Return the corresponding color's discard pile (color passed as String)
     */
    public CardsCollection getPile(String picked_color) {
        for (Color col : colors) {
            if (isColorsMatching(picked_color, col))
                return discard_piles.get(getIndex(col));
        }
        return new CardsCollection();
    }

    /** Return topmost card */
    public Card getCard(Color col) {
        if (!discard_piles.get(getIndex(col)).isEmpty())
            return discard_piles.get(getIndex(col)).getTopCard();
        return new Card();
    }

    /**
     * OVERLOAD FUNCTION
     * Returns the topmost card from the given color's discard pile
     * (color passed as String)
     */
    public Card getCard(String picked_color) {
        for (Color col : colors) {
            if (!discard_piles.get(getIndex(col)).isEmpty()
                    && isColorsMatching(picked_color, col))
                return getCard(col);
        }
        return new Card();
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
    public void removeCard(Card c) {
        if (isTopCard(c))
            removeCard(c.getCardColor());
    }

    /** Insert card into the appropriate discard pile */
    public void addCard(Card c) {
        discard_piles.get(getIndex(c.getCardColor())).addCard(c);
    }

    /** Returns the total number of cards in all discard piles together */
    public int totalSize() {
        int sum = 0;
        for (Color col : colors) {
            sum += size(col);
        }
        return sum;
    }

    /** Returns the number of cards in the specific color's discard pile */
    public int size(Color col) {
        return discard_piles.get(getIndex(col)).size();
    }

    public Card getOnlyCard() {
        if (totalSize() == 1) {
            for (Color col : colors) {
                if (size(col) == 1) {
                    return getCard(col);
                }
            }
        }
        return new Card();
    }

    /** Output discard piles to console */
    public void displayPiles() {
        System.out.println("Discard Piles: ");
        for (Color col : colors) {
            System.out.print(getColorName(col) + ":\t");
            discard_piles.get(getIndex(col)).display();
        }
    }

    /**
     * Return true if all discard piles are empty
     */
    public boolean isEmpty() {
        for (CardsCollection cards : discard_piles) {
            if (!cards.isEmpty())
                return false;
        }
        return true;
    }

    /** Return true if given card is topmost card */
    private boolean isTopCard(Card c) {
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
            if (col == colors[i])
                return i;
        }
        return -1;
    }

    /** Return string form of color passed as parameter */
    protected String getColorName(Color col) {
        return (col == colors[0]) ? "Yellow"
                : (col == colors[1]) ? "Blue"
                        : (col == colors[2]) ? "White"
                                : (col == colors[3]) ? "Green"
                                        : (col == colors[4]) ? "Red" : "";
    }
}
