package LostCities;

import java.util.ArrayList;
import java.awt.*;
/*
Holds:
    cards in hand
*/

public class player {
    private cards hand;
    private cards placed_down;

    public player() {
    }

    public void display() {
        hand.display();
    }

    public cards getHand() {
        return hand;
    }

    public cards getPlacedCards() {
        return placed_down;
    }

    public void placeCard(card c) {
        hand.removeCard(c);
        placed_down.addCard(c);
    }

    public void addCard(card c) {
        hand.addCard(c);
        if()
    }
}
