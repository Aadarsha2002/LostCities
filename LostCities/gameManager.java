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
        String discard_or_undealt;
        String discard_or_place;
        String picked_color;

        while (!undealt.isEmpty()) {
            // Player 1's Turn
            playPlayer(p1);
            playPlayer(p2);
        }
    }

    // Output player's hand to console
    private void displayPlayerHand(player p) {
        p.display();
    }

    // Player 1's actions
    private void playPlayer(player p) {
        Scanner in = new Scanner(System.in);
        int player_number = (p == p1) ? 1 : 2;
        card outgoing_card = new card();
        card incoming_card = new card();

        System.out.println("**********************************");
        System.out.println("It's player " + player_number + "'s turn: ");
        System.out.print("Player " + player_number + "'s Hand: ");
        p.display();

        System.out.print("\nWhich card do you want to place [index of card 0-7]? ");
        int outgoing_card_index = in.nextInt();
        outgoing_card = p.getCardAt(outgoing_card_index);
        p.removeCard(outgoing_card);

        
    }
}
