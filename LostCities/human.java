import java.util.ArrayList;

public class human extends player {
    /**
     * Ask player for an input corresponding to the options shown. Keep asking until
     * player enters something in the given options.
     * Return the string form of that choice
     */
    public String ask(String s, char[] c, ArrayList<cards> opponent__placed_down) {
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
        // if (in.hasNextLine()) {
        // s = in.nextLine();
        // System.out.println(s);
        // return s;
        // } else {
        return in2.nextLine();
        // }
    }
}
