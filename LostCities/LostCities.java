package LostCities;

import java.util.Scanner;
import java.awt.*;

public class LostCities {
    public static void main(String args[]) {
        while (true) {
            Scanner in = new Scanner(System.in);
            gameManager gm = new gameManager();
            System.out.println("**********************************");
            System.out.println("\n\nPlay again [Y/N]?");
            char ch = in.nextLine().charAt(0);
            if (ch == 'n' || ch == 'N')
                break;
        }
    }
}
