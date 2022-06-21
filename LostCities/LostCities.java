import java.util.*;

public class LostCities {
    public static void main(String[] args) {
        while (true) {// keep looping until player says enough

            GameManager gm = new GameManager("human", "human");
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

    protected String askAiHuman(int player_number) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("What is player " + player_number + " [AI/Human]? ");
            return in.nextLine().toLowerCase();
        }
    }
}