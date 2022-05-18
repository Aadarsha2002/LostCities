package LostCities;

import java.util.ArrayList;
import java.awt.*;
/*
Holds:
    cards in hand
*/

public class player {
    private ArrayList<cards> hand;

    // returns all cards of requested color
    public ArrayList<card> getCardsbyColor(Color col) {
        ArrayList<card> cards;
        for (card c : hand) {
            if (c.getCardColor() == col) {
                cards.add(c);
            }
        }
        return cards;
    }
}
