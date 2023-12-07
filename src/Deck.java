import java.util.ArrayList;

public class Deck {
    // Declares instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor for a deck
    public Deck(String[] ranks, String[] suits, int[] values) {
        // Declares an ArrayList of Cards
        cards = new ArrayList<Card>();
        // Populates ArrayList of Cards
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                // Creates every possible card and adds to Deck
                Card newCard = new Card(values[i], suits[j], ranks[i]);
                cards.add(newCard);
                // Increases instance variable for cardsLeft in the Deck because Cards are being added
                cardsLeft++;
            }
        }
        // Makes sure that cardsLeft has the same value as the number of Cards in the Deck
        cardsLeft = cards.size();
    }
    // Checks that the ArrayList of Cards is not empty
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    // Getter method for cardsLeft instance variable
    public int getCardsLeft() {
        return cardsLeft;
    }

    // Deals cards
    public Card deal() {
        // Checks to make sure that there are Cards left in Deck
        if(cardsLeft == 0) {
            return null;
        }
        // Removes a card from instance variable Cards left (one Card is being dealt and so one must be removed)
        cardsLeft--;
        // Gets card from index of cardsLeft variable in cards ArrayList
        return cards.get(cardsLeft);
    }

    // Shuffles deck
    public void shuffle() {
        // Resets cardsLeft variable to original size of Deck
        cardsLeft = cards.size();
        // Shuffles every Card starting from the end of the cards ArrayList
        for(int i = cardsLeft - 1; i > 0; i--) {
            // Chooses random index to start shuffling at
            int r = (int)(Math.random() * (i + 1));
            // Creates card for swapping
            Card exchange = cards.get(r);
            // Sets card at random index r as the value of cards at index i
            cards.set(r, cards.get(i));
            // Sets card at index i as the value of the exchange Card
            cards.set(i, exchange);
        }
    }
}