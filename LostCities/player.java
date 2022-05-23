package LostCities;

import java.util.ArrayList;
import java.awt.*;
/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)

    cards in hand
    piles of cards placed down (outside of board)
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

    /**
     * CONSTRUCTOR
     * Make new hand and placed down card list
     */
    public player() {
        hand = new cards();
        placed_down = new ArrayList<>();
        placed_down.add(new cards());
        placed_down.add(new cards());
        placed_down.add(new cards());
        placed_down.add(new cards());
        placed_down.add(new cards());
    }

    /**
     * CONSTRUCTOR
     * Make a custom hand based on given cards
     */
    public player(cards c) {
        hand = c;
        placed_down = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            placed_down.add(new cards());
        }
    }

    /** Output hand to console */
    public void display() {
        hand.display();
    }

    /** Output placed down cards of player */
    public void displayPlacedDownCards() {
        System.out.print("Yellow: ");
        placed_down.get(0).display();
        System.out.print("Blue: ");
        placed_down.get(1).display();
        System.out.print("White: ");
        placed_down.get(2).display();
        System.out.print("Green: ");
        placed_down.get(3).display();
        System.out.print("Red: ");
        placed_down.get(4).display();
    }

    /** Return hand */
    public cards getHand() {
        return hand;
    }

    /** Return placed cards */
    public ArrayList<cards> getPlacedCards() {
        return placed_down;
    }

    /** Insert a card into hand */
    public void addCard(card c) {
        hand.addCard(c);
    }

    /** Remove a card from hand */
    public void removeCard(card c) {
        hand.removeCard(c);
    }

    /** Return card at specific index in hand */
    public card getCardAt(int index) {
        return hand.getCardAt(index);

    }

    /**
     * Remove specific card from hand
     * Add it to placed cards list appropriately
     */
    public void placeCard(card c) {
        hand.removeCard(c);
        if (c.getCardColor() == Color.yellow && c.getCardNumber() > hand.getTopCard().getCardNumber()) {
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

    /**
     * Return the score of player
     * -> In each color
     * - count multipliers (multiplier++)
     * - sum numbered cards (sum+=c.getCardNumber())
     * - deduct 20 (sum-=20)
     * - multiply sum and multipliers (sum*=multiplier)
     * - add 20 bonus if more than 8 cards are placed down (sum+=20)
     * - add sum to total
     * 
     * Return total
     */
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
