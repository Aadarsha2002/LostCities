import java.io.*;
import java.awt.*;
import java.util.*;

public class human extends player {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    File file;
    Scanner in2;
    Scanner in3;

    public human(String file_name) {
        file = new File(file_name);
        try {
            in2 = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        in3 = new Scanner(System.in);
    }

    // Returns true because player is human
    boolean isHuman() {
        return true;
    }

    @Override
    public void play(player opponent, discardPiles discards, cards undealt) {
        outgoingPlay(opponent, discards, undealt);
        incomingPlay(opponent, discards, undealt);
    }

    private void outgoingPlay(player opponent, discardPiles discards, cards undealt) {
        // outgoing part

        card outgoing_card;

        /** Ask whether player wants to discard or place card */
        char[] choices1 = { 'd', 'p' };
        String discard_or_place = ask("\nDiscard or Place", choices1);

        /** Ask which card player wants to place */
        char[] choices2 = { '0', '1', '2', '3', '4', '5', '6', '7' };
        String outgoing_card_index_str = ask("Pick a card to play", choices2);
        int outgoing_card_index = Integer.parseInt(outgoing_card_index_str);
        outgoing_card = getCardAt(outgoing_card_index);
        removeCard(outgoing_card);

        if (discard_or_place.equalsIgnoreCase("d")) {
            discards.addCard(outgoing_card);
            System.out.print("You chose to discard ");
        } else {
            placeCard(outgoing_card);
            System.out.print("You chose to place ");
        }
        outgoing_card.display();

        System.out.print("\nYour hand is now ");
        display();

    }

    private void incomingPlay(player opponent, discardPiles discards, cards undealt) {
        card incoming_card;
        if (discards.isEmpty()) {
            /**
             * if player can take a card from undealt pile only because there are no cards
             * in2 discard pile
             */
            System.out.println("\nDiscard piles are empty. You can take a card from Undealt Pile only.");
            incoming_card = undealt.getTopCard();
            undealt.removeCard(incoming_card);
        } else {
            /** Ask whether player wants to take card from discard pile or undealt pile */
            char[] choices3 = { 'u', 'd' };
            String discard_or_undealt = ask("\nPick from Discard or Undealt", choices3);

            if (discard_or_undealt.equalsIgnoreCase("d")) {
                System.out.print("You chose discard pile.\n");
                String picked_color;
                while (true) {
                    discards.displayPiles();
                    char[] choices4 = { 'y', 'b', 'w', 'g', 'r' };
                    picked_color = ask("Pick a color", choices4);
                    if (discards.getPile(picked_color).isEmpty())
                        System.out.println("Discard Pile chosen is empty");
                    else
                        break;
                }
                incoming_card = discards.getCard(picked_color);
                discards.removeCard(incoming_card);
            } else {
                incoming_card = undealt.getTopCard();
                undealt.removeCard(incoming_card);
                System.out.print("You chose undealt pile\n");
            }
        }
        System.out.print("You're getting ");
        incoming_card.display();
        addCard(incoming_card);// add the card to player's hand

        System.out.print("\nYour hand is now ");
        display();
    }

    /**
     * Ask player for an input corresponding to the options shown. Keep asking until
     * player enters something in2 the given options (character array).
     * Return the string form of that choice
     */
    private String ask(String s, char[] c) {
        System.out.print(s);
        displayChoices(c);
        String input = "";
        while (true) {
            input = getNextString();
            char input_char = Character.toLowerCase(input.charAt(0));
            boolean match = false;
            for (char ch : c) {
                if (ch == input_char) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                System.out.print("Wrong input! " + s + " again");
                displayChoices(c);
            } else {
                break;
            }
        }
        return input;
    }

    /**
     * Output to console the choices player has to choose from in2 above ask()
     * function
     */
    private void displayChoices(char[] c) {
        System.out.print(" [");
        for (int i = 0; i < c.length; i++) {
            if (i == c.length - 1)
                System.out.print(c[i] + "]: ");
            else
                System.out.print(c[i] + ", ");
        }
    }

    /**
     * If the file has another line, reads it and returns it
     * If not, takes input from player and returns it
     */
    private String getNextString() {
        String s;
        if (in2.hasNextLine()) {
            s = in2.nextLine();
            System.out.println(s);
            return s;
        } else {
            return in3.nextLine();
        }
    }
}
