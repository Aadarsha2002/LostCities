import java.awt.*;

/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)

    card number
    card color
*/

public class Card {
    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public int card_number;
    public Color card_color;
    
    /*CONSTRUCTORS */

    /**
     * CONSTRUCTOR
     * Set defalut values
     */

    public Card() {
        card_number = Integer.MIN_VALUE;
        card_color = Color.black;
    }

    /** Set internal variables to parameters */
    public Card(int num, Color col) {
        for (int i = 0; i < colors.length; i++) {
            if (col == colors[i])
                break;
            else if (col != colors[i] && i == colors.length - 1)
                throw new java.lang.Error("Invalid Color input");
        }
        card_number = num;
        card_color = col;
    }

    /** Return card number */
    public int getCardNumber() {
        return card_number;
    }

    /** Return card color */
    public Color getCardColor() {
        return card_color;
    }

    /** Output card to console */
    public void display() {
        System.out.print(getColorName(card_color).charAt(0));
        System.out.print(card_number);
    }

    /** Return card color in string type */
    public String getCardColorName() {
        return getColorName(card_color);
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