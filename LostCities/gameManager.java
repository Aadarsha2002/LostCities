package LostCities;

import java.awt.*;
import java.util.Scanner;

/*
Holds:
    Player 1 object
    Player 2 object
    Undealt Cards Pile
    Discard Piles
*/

public class gameManager {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    player p1 = new player();
    player p2 = new player();
    cards undealt = new cards('U');
    discardPiles discards = new discardPiles();

    // Constructor
    public gameManager() {
        dealCards();
        playGame();
    }

    // Deal cards to players
    public void dealCards() {
        for (int i = 0; i < 8 * 2; i++) {
            card c = undealt.getCard();
            undealt.removeCard(c);
            if (i % 2 == 0)
                p1.addCard(c);
            else
                p2.addCard(c);
        }
        System.out.println("Player 1's Hand: ");
        displayPlayerHand(p1);
        System.out.println("Player 2's Hand: ");
        displayPlayerHand(p2);
    }

    // Play the game!!!
    public void playGame() {

        while (!undealt.isEmpty()) {
            // Player 1's Turn
            playPlayer(p1);
            playPlayer(p2);
        }
    }

    // Player 1's actions
    private void playPlayer(player p) {

        /**
         * Display pre-turn statistics
         * - indicate Player's turn
         * - their hand
         */
        System.out.println("**********************************");
        System.out.println("It's player " + ((p == p1) ? 1 : 2) + "'s turn: ");
        System.out.print("Player " + ((p == p1) ? 1 : 2) + "'s Hand: ");
        p.display();

        outgoingPlay(p); // placing/discarding card from hand
        incomingPlay(p); // taking card from undealt pile/discard piles

        /*
         * display post-turn statistics
         * - number of undealt cards left
         * - discard piles
         */
        System.out.println("There are " + undealt.size() + " undealt cards left\n");
        discards.displayPiles();
    }

    // Output player's hand to console
    private void displayPlayerHand(player p) {
        p.display();
    }

    private void outgoingPlay(player p) {
        Scanner in = new Scanner(System.in);
        /** Ask which card player wants to place */
        System.out.print("\nWhich card do you want to place [index of card 0-7]? ");
        int outgoing_card_index = in.nextInt(); // index of card in hand
        card outgoing_card = p.getCardAt(outgoing_card_index); // get the card at index
        p.removeCard(outgoing_card);// remove that card from the hand

        // NEED THIS FOR SCANNER TO WORK PROPERLY: clear scanner
        // (why you ask? I don't know!)
        in.nextLine();

        /** Ash whether player wants to discard or place card */
        System.out.print("\nWant to discard [D] or place [P]? ");
        String discard_or_place = in.nextLine(); // get choice
        if (discard_or_place == "D" || discard_or_place == "d") {
            discards.addCard(outgoing_card); // if player wants to discard, add to discard pile
        } else {
            p.placeCard(outgoing_card); // if player wants to place, add to player's placed cards
        }

        // clear again
        in.nextLine();
    }

    private void incomingPlay(player p) {
        Scanner in = new Scanner(System.in);

        /** Ask whether player wants to take card from discard pile or undealt pile */
        System.out.print("\nWant to pick from Discard Pile [D] or Undealt Pile [U]? ");
        String discard_or_undealt = in.next();
        card incoming_card;

        if (discard_or_undealt == "D" || discard_or_undealt == "d") {
            discards.displayPiles(); // if player wants a discarded card, display the discard piles

            System.out.print("\nYou chose discard pile. Which color do you want to pick [Y, B, W, G, R]? ");
            String picked_color = in.nextLine(); // input which color's card player wants

            incoming_card = discards.getCard(picked_color); // get the card that player chose
            discards.removeCard(incoming_card); // remove the card from the discard piles
        } else {
            incoming_card = undealt.getCard(); // if player chose to take undealt card, get the card from undealt pile
            undealt.removeCard(incoming_card); // remove the card from the undealt pile
            System.out.print("\nYou chose undealt pile.");
            incoming_card.display();
        }
        System.out.print("\nYou're getting this card: ");
        incoming_card.display();
        p.addCard(incoming_card);// add the card to player's hand

        // clear yet again
        in.nextLine();
    }
}
