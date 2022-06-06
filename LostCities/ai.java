import java.awt.*;
import java.util.*;

public class ai extends player {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    // Returns true because player is AI
    public boolean isAI() {
        return true;
    }

    @Override
    public void play(player opponent) {
        // TODO Auto-generated method stub

    }

    @Override
    public String ask(String s, char[] c, ArrayList<cards> opponent_placed_down, discardPiles discards) {
        char ch = c[0];
        switch (ch) {
            // choices: d or p - outgoingPlay
            case 'd': {
                return toDiscardOrPlace(opponent_placed_down, discards);
            }
            // choices: 0, 1, 2, 3, 4, 5, 6, 7 - outgoingPlay
            case '0': {
                return fromHandIndex(opponent_placed_down, discards);
            }
            // choices: u or d - incomingPlay
            case 'u': {
                return fromUndealtOrDiscard(opponent_placed_down, discards);
            }
            // choices: y, b, w, g, r - incomingPlay
            case 'y': {
                return fromDiscardsColors(opponent_placed_down, discards);
            }
            default: {
                throw new java.lang.Error("Wrong Choice [internal error]");
            }
        }
    }

    private String toDiscardOrPlace(ArrayList<cards> opponent_placed_down, discardPiles discards) {

        return null;
    }

    private String fromHandIndex(ArrayList<cards> opponent_placed_down, discardPiles discards) {
        return null;
    }

    private String fromUndealtOrDiscard(ArrayList<cards> opponent_placed_down, discardPiles discards) {
        return null;
    }

    private String fromDiscardsColors(ArrayList<cards> opponent_placed_down, discardPiles discards) {
        return null;
    }
}