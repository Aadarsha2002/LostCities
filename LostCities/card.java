package LostCities;

import java.awt.*;

/*
Holds:
    card number
    card color
    array of colors possible
    array of numbers possible (0 for handshake card)
*/

public class card {
    public int card_number;
    public Color card_color;
    static Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    // set default values
    public card() {
        card_number = Integer.MIN_VALUE;
        card_color = Color.black;
    }

    // set passed values to variables
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

    // display the card to console
    public void display() {
        System.out.print(getColorName(card_color) + card_number);
    }

    // return card number
    public int getCardNumber() {
        return card_number;
    }

    // return card color
    public Color getCardColor() {
        return card_color;
    }

    // return card color name in string form
    public String getCardColorName() {
        return getColorName(card_color);
    }

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