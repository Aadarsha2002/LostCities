package LostCities;

import java.util.ArrayList;
import java.awt.*;
/*
Holds:
    cards in hand
*/

public class player {
    private ArrayList<card> hand;

    public void display() {
        System.out.println(hand.toString());
    }

    // adds a card to the hand
    public void addCard(card c) {
        hand.add(c);
    }

    // removes a card from the hand
    public void getCard(card c) {
        hand.remove(c);
    }

    //returns all cards of requested color
    public ArrayList<card> getCardsbyColor(Color col) {
        ArrayList<card> cards;
        for (card c : hand) {
            if (c.getCardColor() == col) {
                cards.add(c);
            }
        }
        return;
    }
}
