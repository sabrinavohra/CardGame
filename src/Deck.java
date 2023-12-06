import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<Card>();
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Card newCard = new Card(values[i], suits[j], ranks[i]);
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
        return false;
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
        for(int i = cardsLeft; i > 0; i--) {
            int r = (int)(Math.random() * cardsLeft);
            Card exchange = cards.get(r);
            cards.set(r, cards.get(i));
            cards.set(i, exchange);
        }
    }
}
