import java.util.ArrayList;
import java.awt.*;

public class ai {
    static Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    player ai_player;

    ai() {
        ai_player = new player();
    }

    public ArrayList<Integer> getEachColorsScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 0; i < col.length; i++) {
            scores.add(ai_player.getColorScore(i));
        }
        return scores;
    }
}