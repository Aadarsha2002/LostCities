package LostCities;

public class LostCities {
    public static void main(String args[]) {
        // setup
        player p1 = new player();
        player p2 = new player();
        cards undealt = new cards('U');
        cards yellow_discard = new cards('D');
        cards blue_discard = new cards('D');
        cards white_discard = new cards('D');
        cards green_discard = new cards('D');
        cards red_discard = new cards('D');

        // deal 8 cards cards to each player
        for (int i = 0; i < 8 * 2; i++) {
            card c = undealt.getCard();
            if (i % 2 == 0)
                p1.addCard(c);
            else
                p2.addCard(c);
        }

        undealt.display();
        p1.display();
        p2.display();

        // GamePlay!
        while (!undealt.isEmpty()) {
            
        }
    }
}
