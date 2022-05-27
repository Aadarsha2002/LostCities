import java.awt.*;

/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)

    card number
    card color
*/

public class card {
    static Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public int card_number;
    public Color card_color;

    /**
     * CONSTRUCTOR
     * Set defalut values
     */
    
    public card() {
        card_number = Integer.MIN_VALUE;
        card_color = Color.black;
    }

    /** Set internal variables to parameters */
    public card(int num, Color col) {
        for (int i = 0; i < card.col.length; i++) {
            if (card.col[i] == col)
                break;
            else if (card.col[i] != col && i == card.col.length - 1)
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
    private String getColorName(Color col) {
        if (col == card.col[0]) {
            return "Yellow";
        } else if (col == card.col[1]) {
            return "Blue";
        } else if (col == card.col[2]) {
            return "White";
        } else if (col == card.col[3]) {
            return "Green";
        } else if (col == card.col[4]) {
            return "Red";
        }
        return "";
    }
}