package LostCities;

import java.util.Scanner;

public class LostCities {
    public static void main(String args[]) {
        while (true) {// keep looping until user says enough
            gameManager gm = new gameManager();

            System.out.println("**********************************");
            System.out.println("\n\nPlay again [Y/N]?");
            Scanner in = new Scanner(System.in);
            char ch = in.nextLine().charAt(0); // ask user if they want to go again
            if (ch == 'n' || ch == 'N')
                break; // if not break! 
        }
    }
}
