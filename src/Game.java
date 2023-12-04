public class Game {
    private Player p1;
    private ArrayList<Card> p1Hand;
    private Player p2;
    private ArrayList<Card> p2Hand;
    private Deck theDeck;

    public Game(Player player1, Player player2)
    {
        p1 = player1;
        p1Hand = new ArrayList<Card>();
        p2 = player2;
        p2Hand = new ArrayList<Card>();
    }
    public void printInstructions() {
        System.out.println("This is a classic game of Uno!");
        System.out.println("Put down a card that matches the one on the top of the deck in either color or number, " +
                "until you have no cards left!");
    }
    private void playGame() {
        printInstructions();
        dealCards();
        System.out.println(theDeck.deal());
    }

    private void dealCards() {
        theDeck.shuffle();
        for(int i = 0; i < 8; i++) {
            p1Hand.add(theDeck.deal());
            p2Hand.add(theDeck.deal());
        }
    }
    private void placeCard() {
        System.out.println("Enter the index of the card you want to play!");
        int index = p1.nextInt();
        if(p1.getHand().size() < index) {

    }
}