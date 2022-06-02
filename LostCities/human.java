import java.io.*;
import java.awt.*;
import java.util.*;

public class human extends player {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    File file;
    Scanner in;
    Scanner in2;

    public human(String file_name) {
        file = new File(file_name);
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        in2 = new Scanner(System.in);
    }

    /**
     * Ask player for an input corresponding to the options shown. Keep asking until
     * player enters something in the given options.
     * Return the string form of that choice
     */
    public String ask(String s, char[] c, ArrayList<cards> opponent_placed_down) {
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
     * Output to console the choices player has to choose from in above ask()
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
        if (in.hasNextLine()) {
            s = in.nextLine();
            System.out.println(s);
            return s;
        } else {
            return in2.nextLine();
        }
    }
}
