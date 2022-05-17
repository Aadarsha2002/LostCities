package LostCities;

import java.util.ArrayList;
import java.awt.*;

public class undealtCards {
    private ArrayList<card> cards;

    // sets up the undealt cards
    public undealtCards() {
        Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
        int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int i = 0; i < col.length; i++) {
            for (int j = 0; j < num.length - 1; j++) {
                card new_card = new card(num[j], col[i]);
                cards.add(new_card);
            }
        }
    }

    // displays the undealt cards
    public void display() {
        System.out.println(cards.toString());
    }

    // add a card to the undealt cards pile
    public void addCard(card c) {
        cards.add(c);
    }

    // remove a card from the undealt cards pile
    public void removeCard(card c) {
        cards.remove(c);
    }
}