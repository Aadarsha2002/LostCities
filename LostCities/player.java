package LostCities;

import java.util.ArrayList;
import java.awt.*;
/*
Holds:
    cards in hand
*/

public class player {
    static Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private cards hand;
    private ArrayList<cards> placed_down;
    /*
     * placed_down[0] = yellow
     * placed_down[1] = blue
     * placed_down[2] = white
     * placed_down[3] = green
     * placed_down[4] = red
     */

    public player() {
        hand = new cards();
        placed_down = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            placed_down.add(new cards());
        }
    }

    public void display() {
        hand.display();
    }

    public cards getHand() {
        return hand;
    }

    public ArrayList<cards> getPlacedCards() {
        return placed_down;
    }

    public void placeCard(card c) {
        hand.removeCard(c);
        if (c.getCardColor() == Color.yellow) {
            placed_down.get(0).addCard(c);
        } else if (c.getCardColor() == Color.blue) {
            placed_down.get(1).addCard(c);
        } else if (c.getCardColor() == Color.white) {
            placed_down.get(2).addCard(c);
        } else if (c.getCardColor() == Color.green) {
            placed_down.get(3).addCard(c);
        } else if (c.getCardColor() == Color.red) {
            placed_down.get(4).addCard(c);
        }
    }

    public void addCard(card c) {
        hand.addCard(c);
    }

    public int calculateScore() {
        hand.sort();
        int total = 0;
        for (Color col : col) {
            ArrayList<card> cards = new ArrayList<>();
            cards = hand.getCardsbyColor(col);
            int multiplier = 1;
            int sum = 0;
            // count multipliers and sum of numbered cards
            for (card c : cards) {
                if (c.getCardNumber() == 0) {
                    multiplier++;
                } else {
                    sum += c.getCardNumber();
                }
            }
            if (cards.size() != 0)// cost
                sum -= 20;
            sum *= multiplier;// multiplier
            if (cards.size() >= 8)// bonus points
                sum += 20;
            total += sum;// add to total
        }
        return total;
    }
}
