import java.awt.*;
import java.util.*;

/*
Holds
    array of colors possible
    array of numbers possible (0 for handshake card)

    a pile of cards
    whether the pile is a discard pile or not
    whether the pile is the undealt cards pile or not
*/

public class CardsCollection {
    static Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    Random rnd = new Random(0);

    private ArrayList<Card> pile = new ArrayList<>(); // just holds a list of cards
    private boolean is_discard_pile; // holds whether the pile is a discard pile or not
    private boolean is_undealtCards; // holds whether the pile is undealt cards pile or not

    /**
     * CONSTRUCTOR
     * Takes in a character (U/D) indicating whether pile of cards is undealt pile
     * of discard cards
     */
    public CardsCollection(char c) {
        if (c == 'U' || c == 'u') {// if undealt cards pile
            is_undealtCards = true;
            is_discard_pile = false;
            makeUndealtCardsPile();
        } else if (c == 'D' || c == 'd') {// if discard pile
            is_undealtCards = false;
            is_discard_pile = true;

        } else {
            is_undealtCards = false;
            is_discard_pile = false;
        }
    }

    /**
     * CONSTRUCTOR
     * Makes non-special pile of cards
     */
    public CardsCollection() {
        is_undealtCards = false;
        is_discard_pile = false;
    }

    /** Return size of cards */
    public int size() {
        return pile.size();
    }

    /** Display cards to console */
    public void display() {
        if (pile.isEmpty()) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        if (is_discard_pile)
            getTopCard().display();
        else
            for (Card c : pile) {
                c.display();
                if (c != getTopCard())
                    System.out.print(", ");
            }
        System.out.println("]");
    }

    /** Add card passed as parameter to cards */
    public void addCard(Card c) {
        if (is_discard_pile) {
            pile.add(c);
        } else if (is_undealtCards) {
            pile.add(c);
        } else {
            pile.add(c);
            sort();
        }
    }

    /** Return topmost card */
    public Card getTopCard() {
        return pile.get(pile.size() - 1);
    }

    /** Return specific card from index */
    public Card getCardAt(int index) {
        return pile.get(index);
    }

    public Card getSmallestCard(Color col) {
        return getCardsbyColor(col).get(0);
    }

    /** Remove a specific card */
    public void removeCard(Card c) {
        pile.remove(c);
    }

    /**
     * OVERLOAD FUNCTION
     * Takes in a color and number
     * Creates a card using parameters and removes that card from cards
     */
    public void removeCard(Color col, int n) {
        pile.remove(new Card(n, col));
    }

    /** Returns true if a card exists in the cards */
    public boolean hasCard(Card c) {
        return pile.indexOf(c) != -1;
    }

    /** Returns true if empty */
    public boolean isEmpty() {
        return (size() == 0);
    }

    /** Return cards of a specific color as ArrayList */
    public ArrayList<Card> getCardsbyColor(Color col) {
        ArrayList<Card> c1 = new ArrayList<>();
        for (Card c : pile) {
            if (c.getCardColor() == col) {
                c1.add(c);
            }
        }
        return c1;
    }

    /** Make a pile of Undealt Cards and shuffle it */
    public void makeUndealtCardsPile() {
        for (Color c : col) {
            // add normal number cards
            for (int n : num) {
                addCard(new Card(n, c));
            }
            // add 2 more handshake cards (1 was added in above loop)
            for (int j = 0; j < 2; j++) {
                addCard(new Card(num[0], c));
            }
        }

        for (int i = 0; i < 6; i++) {
            if (!is_discard_pile) {
                Collections.shuffle(pile, rnd);
            }
        }
    }

    /** If not discard pile, sort cards according to numbers in each color */
    public void sort() {
        ArrayList<Card> sorted_cards = new ArrayList<>();
        for (int x = 0; x < col.length && !is_discard_pile; x++) {
            ArrayList<Card> c = getCardsbyColor(col[x]);
            // sort c
            for (int i = 0; i < c.size() - 1; i++) {
                int min_card_index = i;
                for (int j = i + 1; j < c.size(); j++) {
                    if (c.get(j).getCardNumber() < c.get(min_card_index).getCardNumber())
                        min_card_index = j;
                }
                Card temp = c.get(min_card_index);
                c.remove(min_card_index);
                c.add(min_card_index, c.get(i));
                c.remove(i);
                c.add(i, temp);
            }
            // append c to sorted_cards
            appendCards(sorted_cards, c);
        }
        pile = sorted_cards;
    }

    /**
     * Append 2nd ArrayList parameter to 1st ArrayList parameter
     * Return 1st ArrayList
     */
    private ArrayList<Card> appendCards(ArrayList<Card> to, ArrayList<Card> from) {
        for (Card c : from) {
            to.add(c);
        }
        return to;
    }
}