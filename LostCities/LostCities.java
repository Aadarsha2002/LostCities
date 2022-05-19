package LostCities;

import java.util.Scanner;
import java.awt.*;

public class LostCities {
    public static void main(String args[]) {
        Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
        int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
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
            undealt.removeCard(c);
            if (i % 2 == 0)
                p1.addCard(c);
            else
                p2.addCard(c);
        }

        Scanner in = new Scanner(System.in);
        int placing_card_index = 0;
        card placing_card = new card();
        card picking_card = new card();
        String discard_or_undealt;
        String discard_or_place;
        String picked_color;

        // GamePlay!
        while (!undealt.isEmpty()) {
            // Player 1's turn
            System.out.println("It's player 1's turn: ");
            System.out.print("Player 1's Hand: ");
            p1.display();
            System.out.print("Which card do you want to place [index of card 0-7]?");
            placing_card_index = in.nextInt();
            System.out.println("Want to discard [D] or place [P]? ");
            discard_or_place = in.nextLine();

            placing_card = p1.getHand().getCard();
            p1.getHand().removeCard(placing_card);
            if (discard_or_place == "D" || discard_or_place == "d") {
                if (p1.getHand().getCard().getCardColorName() == getColorName(col[0])) {
                    yellow_discard.addCard(placing_card);
                } else if (p1.getHand().getCard().getCardColorName() == getColorName(col[1])) {
                    blue_discard.addCard(placing_card);
                } else if (p1.getHand().getCard().getCardColorName() == getColorName(col[2])) {
                    white_discard.addCard(placing_card);
                } else if (p1.getHand().getCard().getCardColorName() == getColorName(col[3])) {
                    green_discard.addCard(placing_card);
                } else if (p1.getHand().getCard().getCardColorName() == getColorName(col[4])) {
                    red_discard.addCard(placing_card);
                }
            } else {
                p1.placeCard(placing_card);
            }

            System.out.print("Want to pick from Discard Pile [D] or Undealt Pile [U]? ");
            discard_or_undealt = in.next();
            if (discard_or_undealt == "D" || discard_or_undealt == "d") {
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

                System.out.print("Which color do you want to pick [Y, B, W, G, R]? ");
                picked_color = in.nextLine();

                if (picked_color.charAt(0) == getColorName(col[0]).charAt(0)) {
                    picking_card = yellow_discard.getCard();
                    yellow_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[1]).charAt(0)) {
                    picking_card = blue_discard.getCard();
                    blue_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[2]).charAt(0)) {
                    picking_card = white_discard.getCard();
                    white_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[3]).charAt(0)) {
                    picking_card = green_discard.getCard();
                    green_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[4]).charAt(0)) {
                    picking_card = red_discard.getCard();
                    red_discard.removeCard(picking_card);
                }
                p1.addCard(picking_card);
            } else {
                picking_card = undealt.getCard();
                undealt.removeCard(picking_card);
                p1.addCard(picking_card);
            }

            // Player 2's turn
            System.out.println("It's player 2's turn: ");
            System.out.print("Player 2's Hand: ");
            p2.display();
            System.out.print("Which card do you want to place [index of card 0-7]?");
            placing_card_index = in.nextInt();
            System.out.println("Want to discard [D] or place [P]? ");
            discard_or_place = in.nextLine();

            placing_card = p2.getHand().getCard();
            p2.getHand().removeCard(placing_card);
            if (discard_or_place == "D" || discard_or_place == "d") {
                if (p2.getHand().getCard().getCardColorName() == getColorName(col[0])) {
                    yellow_discard.addCard(placing_card);
                } else if (p2.getHand().getCard().getCardColorName() == getColorName(col[1])) {
                    blue_discard.addCard(placing_card);
                } else if (p2.getHand().getCard().getCardColorName() == getColorName(col[2])) {
                    white_discard.addCard(placing_card);
                } else if (p2.getHand().getCard().getCardColorName() == getColorName(col[3])) {
                    green_discard.addCard(placing_card);
                } else if (p2.getHand().getCard().getCardColorName() == getColorName(col[4])) {
                    red_discard.addCard(placing_card);
                }
            } else {
                p2.placeCard(placing_card);
            }

            System.out.print("Want to pick from Discard Pile [D] or Undealt Pile [U]? ");
            discard_or_undealt = in.next();
            if (discard_or_undealt == "D" || discard_or_undealt == "d") {
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

                System.out.print("Which color do you want to pick [Y, B, W, G, R]? ");
                picked_color = in.nextLine();

                if (picked_color.charAt(0) == getColorName(col[0]).charAt(0)) {
                    picking_card = yellow_discard.getCard();
                    yellow_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[1]).charAt(0)) {
                    picking_card = blue_discard.getCard();
                    blue_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[2]).charAt(0)) {
                    picking_card = white_discard.getCard();
                    white_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[3]).charAt(0)) {
                    picking_card = green_discard.getCard();
                    green_discard.removeCard(picking_card);
                } else if (picked_color.charAt(0) == getColorName(col[4]).charAt(0)) {
                    picking_card = red_discard.getCard();
                    red_discard.removeCard(picking_card);
                }
                p2.addCard(picking_card);
            } else {
                picking_card = undealt.getCard();
                undealt.removeCard(picking_card);
                p2.addCard(picking_card);
            }
        }
    }

    private static String getColorName(Color c) {
        Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
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
