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

    private boolean is_ai;
    /*
     * placed_down[0] = yellow
     * placed_down[1] = blue
     * placed_down[2] = white
     * placed_down[3] = green
     * placed_down[4] = red
     */

    /**
     * CONSTRUCTOR
     * Make new hand and placed down card list for human
     */
    public player() {
        hand = new cards();
        placed_down = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            placed_down.add(new cards());
        }
        is_ai = false;
    }

    /**
     * OVERLOAD CONSTRUCTOR
     * Make a new hand and placed down card list for ai
     */
    public player(String s) {
        hand = new cards();
        placed_down = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            placed_down.add(new cards());
        }
        is_ai = true;
    }

    /**
     * OVERLOAD CONSTRUCTOR
     * Make a custom hand based on given cards
     */
    public player(cards c) {
        hand = c;
        placed_down = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            placed_down.add(new cards());
        }
        is_ai = false;
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

    /** Return scores of placed down cards color-wise in ArrayList */
    public ArrayList<Integer> getEachColorsScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 0; i < col.length; i++) {
            scores.add(getColorScore(i));
        }
        return scores;
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
     * - get score, and add to total
     * Return total
     */
    public int calculateScore() {
        int total = 0;
        for (int i = 0; i < col.length; i++) {
            total += getColorScore(i);// add to total
        }
        return total;
    }

    /**
     * Return the score of the placed cards of a specific color
     * - count multipliers (multiplier++)
     * - sum numbered cards (sum+=c.getCardNumber())
     * - deduct 20 (sum-=20)
     * - multiply sum and multipliers (sum*=multiplier)
     * - add 20 bonus if more than 8 cards are placed down (sum+=20)
     * - add sum to total
     */
    public int getColorScore(int i) {
        placed_down.get(i).sort();
        ArrayList<card> cards;
        cards = placed_down.get(i).getCardsbyColor(col[i]);
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
        if (i == col.length - 1)
            System.out.println(getColorName(col[i]) + " Sum\t\t\t= " + sum);
        else
            System.out.println(getColorName(col[i]) + " Sum\t\t= " + sum);
        System.out.println("\tMultiplier\t= " + multiplier);
        sum *= multiplier;// multiplier
        System.out.println("\tSum Now\t\t= " + sum);
        if (cards.size() >= 8) {// bonus points
            sum += 20;
            System.out.println("Bonus Points\t= 20");
            System.out.println("\tSum after bonus points\t= " + sum);
        }
        return sum;
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
