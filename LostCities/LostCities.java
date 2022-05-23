package LostCities;

import java.util.Scanner;
import java.awt.*;

public class LostCities {
    public static void main(String args[]) {

        // GamePlay!
        while (!undealt.isEmpty()) {
            // Player 1's turn
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

                System.out.print("\nWhich color do you want to pick [Y, B, W, G, R]? ");
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
            System.out.println("**********************************");
            System.out.println("It's player 2's turn: ");
            System.out.print("Player 2's Hand: ");
            p2.display();
            System.out.print("\nWhich card do you want to place [index of card 0-7]?");
            placing_card_index = in.nextInt();
            placing_card = p2.getHand().getCard();
            p2.getHand().removeCard(placing_card);

            System.out.print("\nWant to discard [D] or place [P]? ");
            discard_or_place = in.nextLine();
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

            System.out.print("\nWant to pick from Discard Pile [D] or Undealt Pile [U]? ");
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

                System.out.print("\nWhich color do you want to pick [Y, B, W, G, R]? ");
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

            System.out.println("There are " + undealt.size() + " undealt cards left\n");
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
        }

        // calculate player's hands now!
        int p1_score = p1.calculateScore();
        int p2_score = p2.calculateScore();

        System.out.println("***********************************");
        System.out.println("\n\nPlayer 1 scored " + p1_score);
        System.out.println("Player 2 scored " + p2_score);
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
