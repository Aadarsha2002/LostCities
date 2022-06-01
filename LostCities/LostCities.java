import java.util.Scanner;

public class LostCities {
    public static void main(String[] args) {
        while (true) {// keep looping until player says enough
            gameManager gm = new gameManager();
            gm.dealCards();
            gm.playGame();

            System.out.println("\n\nPlay again [Y/N]?");
            try (Scanner in = new Scanner(System.in)) {
                char c = in.nextLine().charAt(0); // ask player if they want to go again
                if (c == 'n' || c == 'N')
                    break; // if not break!
            }
        }
    }
}