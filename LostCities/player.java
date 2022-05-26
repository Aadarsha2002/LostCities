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
        for (int i = 0; i < 5; i++) {
            placed_down.add(new cards());
        }
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
        for (Color c : col) {
            System.out.print(getColorName(c) + ":\t");
            placed_down.get(getIndex(c)).display();
        }
    }

    /** Return hand */
    public cards getHand() {
        return hand;
    }

    /** Return placed cards */
    public ArrayList<cards> getPlacedCards() {
        return placed_down;
    }

    /** Return topmost card in a specific color of placed piles */
    public card getTopPlacedCard(Color col) {
        return placed_down.get(getIndex(col)).getTopCard();
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
        if (placed_down.get(getIndex(c.getCardColor())).isEmpty() || isValidCardtoPlace(c))
            placed_down.get(getIndex(c.getCardColor())).addCard(c);
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
        for (Color color : col) {
            ArrayList<card> cards;
            cards = hand.getCardsbyColor(color);
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
            if (!cards.isEmpty())// cost
                sum -= 20;
            System.out.println(getColorName(color) + " Sum\t= " + sum);
            System.out.println("Multiplier\t= " + multiplier);
            sum *= multiplier;// multiplier
            if (cards.size() >= 8) {// bonus points
                sum += 20;
                System.out.println("Bonus Points\t= 20");
            }
            total += sum;// add to total
        }
        return total;
    }

    /**
     * Returns true if the given card is either
     * handshake card (0), or
     * higher than the topmost placed card of its color
     */
    private boolean isValidCardtoPlace(card c) {
        return placed_down.isEmpty() || c.getCardNumber() >= getTopPlacedCard(c.getCardColor()).getCardNumber();
    }

    /** Return index of placed cards pile according to given color */
    private int getIndex(Color col) {
        for (int i = 0; i < 5; i++) {
            if (player.col[i] == col)
                return i;
        }
        return -1;
    }

    /** Return string form of color passed as parameter */
    private String getColorName(Color c) {
        if (c == col[0]) {
            return "Yellow";
        } else if (c == col[1]) {
            return "Blue";
        } else if (c == col[2]) {
            return "White";
        } else if (c == col[3]) {
            return "Green";
        } else if (c == col[4]) {
            return "Red";
        }
        return "";
    }
}
