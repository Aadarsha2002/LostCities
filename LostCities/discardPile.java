package LostCities;

import java.util.ArrayList;
import java.awt.*;

/*
Holds:
    discard pile
*/
public class discardPile {
    private ArrayList<ArrayList<card>> discard_pile;
    // discard_pile[0]=yellow
    // discard_pile[1]=blue
    // discard_pile[2]=white
    // discard_pile[3]=green
    // discard_pile[4]=red

    // sorts each discard pile by their numbers
    public void sort() {
        // go through each color
        for (ArrayList<card> cards : discard_pile) {
            // selection sort on each color
            for (int i = 0; i < cards.size() - 1; i++) {
                int min_card_index = i;
                for (int j = i + 1; j < cards.size(); j++) {
                    if (cards.get(j).getCardNumber() < cards.get(min_card_index).getCardNumber())
                        min_card_index = j;
                }
                card temp = cards.get(min_card_index);
                cards.add(min_card_index, cards.get(i));
                cards.add(i, temp);
            }
        }
    }

    // displays the discard pile
    public void display() {
        sort();
        System.out.println(discard_pile.toString());
    }

    //returns the entire discard pile
    public ArrayList<ArrayList<card>> getFullDiscardPile() {
        return discard_pile;
    }

    //returns the request color's discard pile
    public ArrayList<card> getDiscardPile(Color c) {
        if (c == Color.yellow) {
            return discard_pile.get(0);
        } else if (c == Color.blue) {
            return discard_pile.get(1);
        } else if (c == Color.white) {
            return discard_pile.get(2);
        } else if (c == Color.green) {
            return discard_pile.get(3);
        } else {
            return discard_pile.get(4);
        }
    }
}