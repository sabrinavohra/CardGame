import java.util.ArrayList;

public class Deck {
    private ArrayList<int> cards = new ArrayList<int>();
    private int cardsLeft = cards.size();

    public Deck(String[] ranks, String[] suits, int[] values) {
        ranks = cards.getRank();
        suits = cards.getSuit();
        values = cards.getPoint();
        for (int i = 0; i < ranks; i++) {
            for (int j = 0; j < suits; j++) {
                Card newCard = new Card(ranks[i], suits[j], values[i]);
                cards.add(newCard);
                cardsLeft++;
            }
        }
    }
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal()

}
