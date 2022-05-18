package LostCities;

import java.util.ArrayList;
import java.awt.*;

/*
Holds
    A pile of cards
    Whether the pile is a discard pile or not
    Whether the pile is the undealt cards pile or not
    Array of colors possible
    Array of numbers possible (0 for handshake card)
*/

public class cards {
    private ArrayList<card> cards;
    private boolean is_discard_pile;
    private boolean is_undealtCards;
    static Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public cards(char c) {
        if (c == 'U' || c == 'u') {// if undealt cards pile
            is_undealtCards = true;
            is_discard_pile = false;
        } else if (c == 'D' || c == 'd') {// if discard pile
            is_undealtCards = false;
            is_discard_pile = true;
        } else {
            is_undealtCards = false;
            is_discard_pile = false;
        }
    }

    public void display() {
        System.out.println(cards.toString());
    }

    public void addCard(card c) {
        if (is_discard_pile) {
            if (c.getCardColor() == cards.get(0).getCardColor())
                cards.add(c);
        } else if (!is_undealtCards) {
            cards.add(c);
            sort();
        } else {
            cards.add(c);
        }
    }

    public void getCard(card c) {
        cards.remove(c);
    }

    public ArrayList<card> getCardsbyColor(Color col) {
        ArrayList<card> c1 = new ArrayList<card>();
        for (card c : cards) {
            if (c.getCardColor() == col) {
                c1.add(c);
            }
        }
        return c1;
    }

    public void makeUndealtCardsPile() {
        /*
         * Reference:
         * col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
         * num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
         */
        for (int i = 0; i < col.length; i++) {
            // add normal number cards
            for (int j = 1; j < num.length; j++) {
                addCard(new card(num[j], col[i]));
            }
            // add handshake cards
            for (int j = 0; j < 3; j++) {
                addCard(new card(num[0], col[i]));
            }
        }
    }

    public boolean sort() {
        ArrayList<card> sorted_cards = new ArrayList<card>();
        for (int i = 0; i < col.length && !is_discard_pile; i++) {
            ArrayList<card> c = getCardsbyColor(col[i]);
            // sort c
            for (int j = 0; j < c.size() - 1; j++) {
                int min_card_index = j;
                for (int k = j + 1; k < c.size(); k++) {
                    if (c.get(k).getCardNumber() < c.get(min_card_index).getCardNumber())
                        min_card_index = k;
                }
                card temp = c.get(min_card_index);
                c.add(min_card_index, c.get(j));
                c.add(j, temp);
            }
            // append c to sorted_cards
            appendCards(sorted_cards, c);
        }
        cards = sorted_cards;
        return true;
    }

    private boolean checkAllSameColor() {
        Color col = cards.get(0).getCardColor();
        for (card c : cards) {
            if (c.getCardColor() != col) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<card> appendCards(ArrayList<card> to, ArrayList<card> from) {
        for (card c : from) {
            to.add(c);
        }
        return to;
    }
}