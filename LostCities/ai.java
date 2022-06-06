import java.awt.*;
import java.util.*;

public class ai extends player {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    @Override
    public String ask(String s, char[] c, ArrayList<cards> opponent_placed_down, discardPiles discards) {
        char ch = c[0];
        switch (ch) {
            // choices: d or p - incomingPlay
            case 'd': {
                return pickDiscardOrPlace(opponent_placed_down, discards);
            }
            // choices: 0, 1, 2, 3, 4, 5, 6, 7 - incomingPlay
            case '0': {
                return pickHandIndex(opponent_placed_down, discards);
            }
            // choices: u or d - outgoingPlay
            case 'u': {
                return pickUndealtOrDiscard(opponent_placed_down, discards);
            }
            // choices: y, b, w, g, r - outgoingPlay
            case 'y': {
                return pickColors(opponent_placed_down, discards);
            }
            default: {
                throw new java.lang.Error("Wrong Choice [internal error]");
            }
        }
    }

    private String pickDiscardOrPlace(ArrayList<cards> opponent_placed_down, discardPiles discards) {
        
        return null;
    }

    private String pickHandIndex(ArrayList<cards> opponent_placed_down, discardPiles discards) {
        return null;
    }

    private String pickUndealtOrDiscard(ArrayList<cards> opponent_placed_down, discardPiles discards) {
        return null;
    }

    private String pickColors(ArrayList<cards> opponent_placed_down, discardPiles discards) {
        return null;
    }
}