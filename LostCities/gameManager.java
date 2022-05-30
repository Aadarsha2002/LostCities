import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    public gameManager() {
        p1 = new player();
        p2 = new player();
        undealt = new cards('U');
        discards = new discardPiles();
        file = new File("testCases.txt");
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        in2 = new Scanner(System.in);
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

        outgoingPlay(p); // placing/discarding card from hand
        incomingPlay(p); // taking card from undealt pile/discard piles

        /*
         * display post-turn statistics
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
        System.out.println("\n*_*_*_*_*_*_*_*_*_*_");
    }

    /**
     * Outgoing card part of player's turn
     * - Ask whether player wants to place or discard
     * - Ask which card player wants to play
     * - Do appropriate actions
     */
    private void outgoingPlay(player p) {
        /** Ask whether player wants to discard or place card */
        System.out.print("\nWant to discard [D] or place [P]? ");
        String discard_or_place = getNextString(); // get choice

        /** Ask which card player wants to place */
        System.out.print("Which card do you want to play [index of card 0-7]? ");
        int outgoing_card_index = getNextInt(); // index of card in hand
        card outgoing_card = p.getCardAt(outgoing_card_index); // get the card at index
        p.removeCard(outgoing_card);// remove that card from the hand

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

        // // NEED THIS FOR SCANNER TO WORK PROPERLY: clear scanner
        // // (why you ask? I don't know!)
        getNextString();
    }

    /**
     * Incoming card part of player's turn
     * - Ask whether player wants to pick card from discard pile or undealt pile
     * - Do appropriate actions
     */
    private void incomingPlay(player p) {
        card incoming_card;

        if (discards.isEmpty()) {
            /** if player can take a card from undealt pile only */
            System.out.println("\nDiscard piles are empty. You can take a card from Undealt Pile only.");
            incoming_card = undealt.getTopCard();
            undealt.removeCard(incoming_card); // remove the card from the undealt pile
        } else {

            /** Ask whether player wants to take card from discard pile or undealt pile */
            System.out.print("\nWant to pick from Discard Pile [D] or Undealt Pile [U]? ");
            String discard_or_undealt = getNextString();

            if (discard_or_undealt.equalsIgnoreCase("d")) {
                discards.displayPiles(); // if player wants a discarded card, display the discard piles
                System.out.print("You chose discard pile. Which color do you want to pick [Y, B, W, G, R]? ");
                String picked_color = getNextString(); // input which color's card player wants

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

    private String askColor() {
        System.out.println("Pick a Color [Y, B, W, G, R]: ");
        while (true) {
            String picked_color = getNextString();
            picked_color=String.toLowerCase(picked_color);
        }
    }

    /**
     * If the file has another line, reads it and returns it
     * If not, takes input from user and returns it
     */
    private String getNextString() {
        String s;
        if (in.hasNextLine()) {
            s = in.nextLine();
            System.out.println(s);
            return s;
        } else {
            return in2.nextLine();
        }
    }

    /**
     * If the file has another line, reads it and returns it
     * If not, takes input from user and returns it
     */
    private int getNextInt() {
        int i;
        if (in.hasNextLine()) {
            i = in.nextInt();
            System.out.println(i);
            return i;
        } else {
            return in2.nextInt();
        }
    }

}
