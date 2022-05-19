package LostCities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
    Random rnd = new Random(0);
    private ArrayList<card> cards = new ArrayList<>();
    private boolean is_discard_pile;
    private boolean is_undealtCards;
    static Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    // Constructor which takes in a character (U or D) indicating whether the pile
    // of cards is undealt pile or discard cards
    public cards(char c) {
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

    // Constructor which makes a non-special pile of cards
    public cards() {
        is_undealtCards = false;
        is_discard_pile = false;
    }

    // Returns the size of the pile
    public int size() {
        return cards.size();
    }

    // Display the cards
    public void display() {
        System.out.print("[");
        for (card c : cards) {
            c.display();
            System.out.print(", ");
        }
        System.out.println("]");
    }

    // Add a card to the cards
    public void addCard(card c) {
        if (is_discard_pile && c.getCardColor() == cards.get(0).getCardColor()) {
            cards.add(c);
        } else if (is_undealtCards) {
            cards.add(c);
        } else {
            cards.add(c);
            sort();
        }
    }

    // get top card
    public card getCard() {
        return cards.get(cards.size() - 1);
    }

    // get card from a specific index
    public card getCardAt(int index) {
        return cards.get(index);
    }

    // Remove a specific card from the cards
    public void removeCard(card c) {
        cards.remove(c);
    }

    // overloaded function to take in a color and number to create a card and then
    // remove
    // Remove a specific card from the cards
    public void removeCard(Color col, int n) {
        cards.remove(new card(n, col));
    }

    // checks whether a specific card exists in the cards
    public boolean hasCard(card c) {
        return cards.indexOf(c) != -1;
    }

    public boolean isEmpty() {
        return (cards.size() == 0) ? true : false;
    }

    // Return cards of a specific color
    public ArrayList<card> getCardsbyColor(Color col) {
        ArrayList<card> c1 = new ArrayList<>();
        for (card c : cards) {
            if (c.getCardColor() == col) {
                c1.add(c);
            }
        }
        return c1;
    }

    // Make the pile of undealt cards and shuffle it
    public void makeUndealtCardsPile() {
        /*
         * Reference:
         * col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
         * num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
         */
        for (Color c : col) {
            // add normal number cards
            for (int n : num) {
                addCard(new card(n, c));
            }
            // add handshake cards
            for (int j = 0; j < 3; j++) {
                addCard(new card(num[0], c));
            }
        }
        shuffleCards();
        shuffleCards();
        shuffleCards();
        shuffleCards();
        shuffleCards();
        shuffleCards();
    }

    // Sort the cards (if not discard pile) according to numbers in each color
    public void sort() {
        ArrayList<card> sorted_cards = new ArrayList<card>();
        for (int x = 0; x < col.length && !is_discard_pile; x++) {
            ArrayList<card> c = getCardsbyColor(col[x]);
            // sort c
            for (int i = 0; i < c.size() - 1; i++) {
                int min_card_index = i;
                for (int j = i + 1; j < c.size(); j++) {
                    if (c.get(j).getCardNumber() < c.get(min_card_index).getCardNumber())
                        min_card_index = j;
                }
                card temp = c.get(min_card_index);
                c.remove(min_card_index);
                c.add(min_card_index, c.get(i));
                c.remove(i);
                c.add(i, temp);
            }
            // append c to sorted_cards
            appendCards(sorted_cards, c);
        }
        cards = sorted_cards;
    }

    // Return true if all cards are same color
    private boolean checkAllSameColor() {
        Color col = cards.get(0).getCardColor();
        for (card c : cards) {
            if (c.getCardColor() != col) {
                return false;
            }
        }
        return true;
    }

    // Append 2nd arraylist to 1st arraylist.
    // Return the 1st arraylist
    private ArrayList<card> appendCards(ArrayList<card> to, ArrayList<card> from) {
        for (card c : from) {
            to.add(c);
        }
        return to;
    }

    // Shuffle the cards
    private void shuffleCards() {
        if (!is_discard_pile) {
            Collections.shuffle(cards, rnd);
        }
    }
}