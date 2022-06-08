import java.io.*;
import java.awt.*;
import java.util.*;

/*
Holds:
    array of colors possible
    array of numbers possible (0 for handshake card)

    Player 1
    Player 2
    Undealt Cards Pile
    Discard Piles
*/

public class gameManager {
    static Color[] colors = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    Player p1;
    Player p2;
    CardsCollection undealt;
    discardPiles discards;

    File file;
    Scanner in;
    Scanner in2;

    /**
     * CONSTRUCTOR
     * - initializes the internal variables
     */
    public gameManager(String one, String two) {
        if (one == "human") {
            p1 = new human("testCasesp1.txt");
        } else {
            p1 = new ai();
        }

        if (two == "human") {
            p2 = new human("testCasesp2.txt");
        } else {
            p2 = new ai();
        }
        undealt = new CardsCollection('U');
        discards = new discardPiles();
    }

    /** Deal cards to both players from undealt cards pile */
    public void dealCards() {
        for (int i = 0; i < 8 * 2; i++) {
            Card c = undealt.getTopCard();
            undealt.removeCard(c);
            if (i % 2 == 0)
                p1.addCard(c);
            else
                p2.addCard(c);
        }
        System.out.println("Player 1's Hand: ");
        p1.display();
        System.out.println("Player 2's Hand: ");
        p2.display();
    }

    /**
     * Play the game!!
     * Until undealt pile is empty, play players
     * Oncce undealt pile is empty, calculate score
     */
    public void playGame() {
        while (!undealt.isEmpty()) {
            // Player 1's Turn
            playPlayer(p1);
            playPlayer(p2);
        }

        // Calculate scores
        System.out.println("\nPlayer 1 Score Calculation: ");
        int p1_score = p1.calculateScore();
        System.out.println("\nPlayer 2 Score Calculation: ");
        int p2_score = p2.calculateScore();

        // Output Scores
        System.out.println("\n***********************************");
        System.out.println("\n\nPlayer 1 scored " + p1_score);
        System.out.println("Player 2 scored " + p2_score);
        System.out.println(((p1_score > p2_score) ? "Player 1 won!" : "Player 2 won!"));
    }

    /**
     * A Player's turn
     * - Display pre-turn statistics
     * - Play outgoing card part of turn
     * - Play incoming card part of turn
     * - Display post-turn statistics
     */
    private void playPlayer(plaPlayerer p) {

        /**
         * Display pre-turn statistics
         * - indicate Player's turn
         * - their hand
         */
        System.out.println("\n**********************************\n");
        System.out.println("It's player " + ((p == p1) ? 1 : 2) + "'s turn:");
        System.out.println("\n*_*_*_*_*_*_*_*_*_*_");
        System.out.println("Pre Turn Statistics: ");
        displayStatistics(p);

        p.play(((p == p1) ? p2 : p1), discards, undealt); // execute player's turn

        /*
         * Display post-turn statistics
         * - number of undealt cards left
         * - discard piles
         */

        System.out.println("\n_*_*_*_*_*_*_*_*_*_*");
        System.out.println("Post Turn Statistics: ");
        displayStatistics(p);
    }

    // Outputs to console the current statistics of the games
    private void displayStatistics(Player p) {
        System.out.println("Player " + ((p == p1) ? 2 : 1) + "'s Placed Down cards:");
        ((p == p1) ? p2 : p1).displayPlacedDownCards();
        discards.displayPiles();
        System.out.println("Player " + ((p == p1) ? 1 : 2) + "'s Placed Down cards:");
        ((p == p1) ? p1 : p2).displayPlacedDownCards();
        System.out.print("There " + (undealt.size() == 1 ? "is " : "are ")
                + undealt.size() + " undealt card" + (undealt.size() == 1 ? "" : "s") + " left");
        System.out.print("\nHand: ");
        p.display();
        System.out.println("*_*_*_*_*_*_*_*_*_*_");
    }
}