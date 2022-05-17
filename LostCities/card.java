package LostCities;

import java.awt.*;

/*
Holds:
    card number
    card color
*/

public class card {
    public int card_number;
    public Color card_color;

    // set default values
    public card() {
        card_number = Integer.MIN_VALUE;
        card_color = Color.black;
    }

    // set passed values to variables
    public card(int num, Color col) {
        card_number = num;
        card_color = col;
    }

    // display the card to console
    public void display() {
        System.out.println(card_color.toString().charAt(0) + card_number);
    }

    // return card number
    public int getCardNumber() {
        return card_number;
    }

    // return card color
    public Color getCardColor() {
        return card_color;
    }
}