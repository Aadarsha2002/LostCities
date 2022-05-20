package LostCities;

import java.awt.*;
/*
Holds:
    Player 1 object
    Player 2 object
    Undealt Cards
    Yellow Discard Pile
    Blue Discard Pile
    White Discard Pile
    Green Discard Pile
    Red Discard Pile
*/

public class gameManager {
    Color[] col = { Color.yellow, Color.blue, Color.white, Color.green, Color.red };
    int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    player p1 = new player();
    player p2 = new player();
    cards undealt = new cards('U');
    discardPiles discards = new discardPiles();
}
