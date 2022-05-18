package LostCities;

import java.util.ArrayList;
import java.awt.*;

/*
Holds
    A pile of cards
*/

public class cards {
    private ArrayList<card> cards;
    private boolean is_discard_pile;
    private boolean is_undealtCards;

    public cards(char c) {
        if (c == 'U' || c == 'u') {
            is_undealtCards = true;
            is_discard_pile = false;
        } else if (c == 'D' || c == 'd') {
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
        } else {
            cards.add(c);
        }
    }

    public void getCard(card c) {
        cards.remove(c);
    }

    public ArrayList<card> getCardsbyColor(Color col) {
        ArrayList<card> c;
        for (card card : cards) {
            if (card.getCardColor() == col) {
                c.add(card);
            }
        }
        return c;
    }

    public void makeUndealtCardsPile() {
        Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
        int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int i = 0; i < col.length; i++) {
            for (int j = 0; j < num.length - 1; j++) {
                addCard(new card(num[j], col[i]));
            }
        }
    }

    public boolean sort() {
        if (checkAllSameColor()) {
            for (int i = 0; i < cards.size() - 1; i++) {
                int min_card_index = i;
                for (int j = i + 1; j < cards.size(); j++) {
                    if (cards.get(j).getCardNumber() < cards.get(min_card_index).getCardNumber())
                        min_card_index = j;
                }
                card temp = cards.get(min_card_index);
                cards.add(min_card_index, cards.get(i));
                cards.add(i, temp);
            }
            return true;
        } else {
            return false;
        }
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
}