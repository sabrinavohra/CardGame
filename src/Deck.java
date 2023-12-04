import java.util.ArrayList;

public class Deck {
    private ArrayList<Integer> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<Integer>();
        for (int i = 0; i < ranks; i++) {
            for (int j = 0; j < suits; j++) {
                Card newCard = new Card(ranks[i], suits[j], values[i]);
                cards.add(newCard);
                cardsLeft++;
            }
        }
        cardsLeft = cards.size();
    }
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if(cards.size() == 0) {
            return null;
        }
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        cardsLeft = cards.size();
        for(int i = cardsLeft; i < cardsLeft; i--) {
            int r = int(Math.random() * cardsLeft);
            Card exchange = cards[r];
            cards[r] = cards[i];
            cards[i] = exchange;
        }
    }
}
