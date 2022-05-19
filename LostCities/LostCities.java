package LostCities;

import java.util.Scanner;

public class LostCities {
    public static void main(String args[]) {
        // setup
        player p1 = new player();
        player p2 = new player();
        cards undealt = new cards('U');
        cards yellow_discard = new cards('D');
        cards blue_discard = new cards('D');
        cards white_discard = new cards('D');
        cards green_discard = new cards('D');
        cards red_discard = new cards('D');

        // deal 8 cards cards to each player
        for (int i = 0; i < 8 * 2; i++) {
            card c = undealt.getCard();
            if (i % 2 == 0)
                p1.addCard(c);
            else
                p2.addCard(c);
        }

        System.out.println("Player 2's Hand: ");
        p2.display();

        Scanner in = new Scanner(System.in);

        // GamePlay!
        while (!undealt.isEmpty()) {
            // Player 1's turn
            System.out.println("It's player 1's turn: ");
            System.out.print("Player 1's Hand: ");
            p1.display();
            System.out.print("Which card do you want to play? ");
            

            
            System.out.println("Discard Piles: ");
            System.out.print("Yellow: ");
            yellow_discard.display();
            System.out.print("Blue: ");
            blue_discard.display();
            System.out.print("White: ");
            white_discard.display();
            System.out.print("Green: ");
            green_discard.display();
            System.out.print("Red: ");
            red_discard.display();
            System.out.print("Which card do you want to pick? ");

            // Player 2's turn
        }
    }
}
