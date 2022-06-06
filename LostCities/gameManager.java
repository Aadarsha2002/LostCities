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
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    player p1;
    player p2;
    cards undealt;
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
        undealt = new cards('U');
        discards = new discardPiles();
    }

    /** Deal cards to both players from undealt cards pile */
    public void dealCards() {
        for (int i = 0; i < 8 * 2; i++) {
            card c = undealt.getTopCard();
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
    private void playPlayer(player p) {

        /**
         * Display pre-turn statistics
         * - indicate Player's turn
         * - their hand
         */
        System.out.println("\n**********************************\n");
        System.out.println("It's player " + ((p == p1) ? 1 : 2) + "'s turn:");
        System.out.println("\n*_*_*_*_*_*_*_*_*_*_");
        System.out.println("Pre Turn Statistics: ");
        System.out.println("Player " + ((p == p1) ? 2 : 1) + "'s Placed Down cards:");
        ((p == p1) ? p2 : p1).displayPlacedDownCards();
        discards.displayPiles();
        System.out.println("Player " + ((p == p1) ? 1 : 2) + "'s Placed Down cards:");
        ((p == p1) ? p1 : p2).displayPlacedDownCards();
        System.out.println("There are " + undealt.size() + " undealt cards left");
        System.out.print("\nHand: ");
        p.display();
        System.out.println("_*_*_*_*_*_*_*_*_*_*");

        Play(p); // placing/discarding card from hand
        incomingPlay(p); // taking card from undealt pile/discard piles

        /*
         * Display post-turn statistics
         * - number of undealt cards left
         * - discard piles
         */

        System.out.println("\n_*_*_*_*_*_*_*_*_*_*");
        System.out.println("Post Turn Statistics: ");
        System.out.println("Player " + ((p == p1) ? 2 : 1) + "'s Placed Down cards:");
        ((p == p1) ? p2 : p1).displayPlacedDownCards();
        discards.displayPiles();
        System.out.println("Player " + ((p == p1) ? 1 : 2) + "'s Placed Down cards:");
        ((p == p1) ? p1 : p2).displayPlacedDownCards();
        System.out.print("There " + (undealt.size() == 1 ? "is " : "are ") + undealt.size() + " undealt card" + (undealt
                .size() == 1 ? "" : "s") + " left");
        System.out.print("\nHand: ");
        p.display();
        System.out.println("*_*_*_*_*_*_*_*_*_*_");
    }

    private void Play(player p) {
        // outgoing

        /** Ask whether player wants to discard or place card */
        char[] choices1 = { 'd', 'p' };
        String discard_or_place = p.ask("\nDiscard or Place", choices1,
                ((p == p1) ? p2 : p1).getPlacedCards(), discards);

        /** Ask which card player wants to place */
        char[] choices2 = { '0', '1', '2', '3', '4', '5', '6', '7' };
        String outgoing_card_index_str = p.ask("Pick a card to play", choices2, ((p == p1) ? p2 : p1).getPlacedCards(),
                discards);
        int outgoing_card_index = Integer.parseInt(outgoing_card_index_str); // convert string into integer
        card outgoing_card = p.getCardAt(outgoing_card_index); // get the card at index
        p.removeCard(outgoing_card); // remove that card from the hand

        if (discard_or_place.equalsIgnoreCase("d")) {
            discards.addCard(outgoing_card); // if player wants to discard, add to discard pile
            System.out.print("You chose to discard ");
        } else {
            p.placeCard(outgoing_card); // if player wants to place, add to player's placed cards
            System.out.print("You chose to place ");
        }
        outgoing_card.display();

        System.out.print("\nYour hand is now ");
        p.display();

        // incoming
        card incoming_card;

        if (discards.isEmpty()) {
            /**
             * if player can take a card from undealt pile only because there are no cards
             * in discard pile
             */
            System.out.println("\nDiscard piles are empty. You can take a card from Undealt Pile only.");
            incoming_card = undealt.getTopCard();
            undealt.removeCard(incoming_card); // remove the card from the undealt pile
        } else {
            /** Ask whether player wants to take card from discard pile or undealt pile */
            char[] choices1 = { 'u', 'd' };
            String discard_or_undealt = p.ask("\nPick from Discard or Undealt", choices1,
                    ((p == p1) ? p2 : p1).getPlacedCards(), discards);

            if (discard_or_undealt.equalsIgnoreCase("d")) {
                System.out.print("You chose discard pile.\n");
                String picked_color;
                while (true) {
                    discards.displayPiles(); // if player wants a discarded card, display the discard piles
                    char[] choices2 = { 'y', 'b', 'w', 'g', 'r' };
                    picked_color = p.ask("Pick a color", choices2,
                            ((p == p1) ? p2 : p1).getPlacedCards(), discards); // ask which color card player wants
                    if (discards.getPile(picked_color).isEmpty())
                        // if that discard pile is empty, then tell it's empty and ask again
                        System.out.println("Discard Pile chosen is empty");
                    else
                        break;
                }
                incoming_card = discards.getCard(picked_color); // get the card that player chose
                discards.removeCard(incoming_card); // remove the card from the discard piles
            } else {
                // if player chose to take undealt card, get the card from undealt pile
                incoming_card = undealt.getTopCard();
                undealt.removeCard(incoming_card); // remove the card from the undealt pile
                System.out.print("You chose undealt pile\n");
            }
        }
        System.out.print("You're getting ");
        incoming_card.display();
        p.addCard(incoming_card);// add the card to player's hand

        System.out.print("\nYour hand is now ");
        p.display();
    }
}
