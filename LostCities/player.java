import java.awt.*;
import java.util.*;
/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)

    cards in hand
    piles of cards placed down (outside of board)
*/

public abstract class Player {
    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    Scanner in;

    protected CardsCollection hand;
    protected ArrayList<CardsCollection> placed_down;
    /*
     * placed_down[0] = yellow
     * placed_down[1] = blue
     * placed_down[2] = white
     * placed_down[3] = green
     * placed_down[4] = red
     */

    /* CONSTRUCTORS */

    /**
     * Make new hand and placed down card list for human
     */
    Player() {
        hand = new CardsCollection();
        placed_down = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            placed_down.add(new CardsCollection());
        }
        in = new Scanner(System.in);
    }

    /**
     * Make a custom hand based on given cards
     */
    Player(CardsCollection c) {
        hand = c;
        placed_down = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            placed_down.add(new CardsCollection());
        }
        in = new Scanner(System.in);
    }

    /* ABSTRACT DEFINITIONS */

    /*
     * Redirects to the appropriate play() in human.java or ai.java and conducts the
     * player's turn (incoming and outgoing card and changes the cards too)
     */
    public abstract void play(Player opponent, DiscardPiles discards, CardsCollection undealt);

    /* GETTER FUNCTIONS */

    /* Return hand */
    public CardsCollection getHand() {
        return hand;
    }

    /* Return placed cards */
    public ArrayList<CardsCollection> getPlacedCards() {
        return placed_down;
    }

    /* Return topmost card in a specific color of placed piles */
    public Card getTopPlacedCard(Color col) {
        return placed_down.get(getIndex(col)).getTopCard();
    }

    /* Returns the index of the requested card in the hand */
    public int getCardIndex(Card c) {
        for (int i = 0; i < hand.size(); i++) {
            if (c == hand.getCardAt(i))
                return i;
        }
        return -1;
    }

    /* Return scores of placed down cards color-wise in ArrayList */
    public ArrayList<Integer> getEachColorsScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            scores.add(getColorScore(i));
        }
        return scores;
    }

    /* Return card at specific index in hand */
    public Card getCardAt(int index) {
        return hand.getCardAt(index);
    }

    /*
     * Return the score of player
     * -> In each color
     * - get score, and add to total
     * Return total
     */
    public int getScore() {
        int total = 0;
        for (int i = 0; i < colors.length; i++) {
            total += getColorScore(i);// add to total
        }
        return total;
    }

    /*
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
        ArrayList<Card> cards;
        cards = placed_down.get(i).getCardsbyColor(colors[i]);
        int multiplier = 1;
        int sum = 0;
        // count multipliers and sum of numbered cards
        for (Card c : cards) {
            if (c.getCardNumber() == 0) {
                multiplier++;
            } else {
                sum += c.getCardNumber();
            }
        }
        if (!cards.isEmpty())// cost
            sum -= 20;
        if (i == colors.length - 1)
            System.out.println(getColorName(colors[i]) + " Sum\t\t\t= " + sum);
        else
            System.out.println(getColorName(colors[i]) + " Sum\t\t= " + sum);
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

    /* DISPLAY FUNCTIONS */

    /* Output hand to console */
    public void display() {
        hand.display();
    }

    /* Output placed down cards of player */
    public void displayPlacedDownCards() {
        for (Color col : colors) {
            System.out.print(getColorName(col) + ":\t");
            placed_down.get(getIndex(col)).display();
        }
    }

    /* AUXILIARY FUNCTIONS */

    /* Insert a card into hand */
    public void addCard(Card c) {
        hand.addCard(c);
    }

    /* Remove a card from hand */
    public void removeCard(Card c) {
        hand.removeCard(c);
    }

    /*
     * Remove specific card from hand
     * Add it to placed cards list appropriately
     */
    public void placeCard(Card c) {
        hand.removeCard(c);
        if (placed_down.get(getIndex(c.getCardColor())).isEmpty()
                || (placed_down.isEmpty()
                        || c.getCardNumber() >= getTopPlacedCard(c.getCardColor()).getCardNumber()))
            placed_down.get(getIndex(c.getCardColor())).addCard(c);
    }

    /* PROTECTED FUNCTIONS */

    protected ArrayList<Integer> getCardCountsByColor() {
        ArrayList<Integer> counts = new ArrayList<>(colors.length);
        for (Color color : colors) {
            counts.add(hand.getCardsbyColor(color).size());
        }
        return counts;
    }

    /* Return index of placed cards pile according to given color */
    protected int getIndex(Color col) {
        for (int i = 0; i < 5; i++) {
            if (Player.colors[i] == col)
                return i;
        }
        return -1;
    }

    /* Return string form of color passed as parameter */
    protected String getColorName(Color col) {
        return (col == colors[0]) ? "Yellow"
                : (col == colors[1]) ? "Blue"
                        : (col == colors[2]) ? "White"
                                : (col == colors[3]) ? "Green"
                                        : (col == colors[4]) ? "Red" : "";
    }
}