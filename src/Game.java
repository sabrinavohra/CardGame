import java.util.ArrayList;

public class Game {
    private Player p1;
    private ArrayList<Card> p1Hand;
    private Player p2;
    private ArrayList<Card> p2Hand;
    private Deck theDeck;
    private ArrayList<Card> stack;
    private boolean currentPlayer;
    private ArrayList<Card> currentPlayerHand;
    public static boolean bPlayer1 = true;
    public static boolean bPlayer2 = false;

    public Game(Player player1, Player player2)
    {
        p1 = player1;
        p1Hand = new ArrayList<Card>();
        p2 = player2;
        p2Hand = new ArrayList<Card>();
        currentPlayer = true;
    }
    public void printInstructions() {
        System.out.println("This is a classic game of Uno!");
        System.out.println("Put down a card that matches the one on the top of the deck in either color or number, " +
                "until you have no cards left!");
    }
    private void playGame() {
        printInstructions();
        dealCards();
        while(hasWon() == false) {
            System.out.println("The top card in the stack is: " + theDeck.get(0));
            System.out.println("Would you like to place a card? Respond false if you have no card that matches the top card in the stack.");
            if(currentPlayer == bPlayer1) {
                boolean match = p1.nextBoolean();
                System.out.println("Here's a look at your hand: " + showHand());
                if(match == false) {
                    addCard();
                }
                placeCard();
            }
            else {
                boolean match2 = p2.nextBoolean();
                System.out.println("Here's a look at your hand: " + showHand());
                if(match2 == false) {
                    addCard();
                }
                placeCard();
            }
            currentPlayer = !currentPlayer;
        }
        System.out.println("Great game!");
        //while(someone hasn't won / hasn't gotten rid of all their cards)
            //let player put down card that matches with the one on top of stack in at least one way (check) - placeCard();
            //let other play do the same thing - placeCard();
            //if a player (has no cards that match the top one)
                //add card to their hand until they can put down a card - addCard();
            //after each turn check if: they have won - hasWon() and if a player hasn't won then change currentPlayer to other player;
                // outside of while loop: end game (!!) if they have and ask if they want to play again - playAgain();
                //continue game if they haven't
    }

    private void dealCards() {
        theDeck.shuffle();
        for(int i = 0; i < 8; i++) {
            p1Hand.add(theDeck.deal());
            p2Hand.add(theDeck.deal());
        }
    }
    private ArrayList<Card> showHand() {
        if(currentPlayer == bPlayer1) {
            for(int i = 0; i < p1Hand.size(); i++) {
                System.out.println(p1Hand.get(i));
            }
        }
        else {
            for(int i = 0; i < p2Hand.size(); i++) {
                System.out.println(p2Hand.get(i));
            }
        }
    }
    private void placeCard() {
        System.out.println("Enter the index of the card you want to play!");
        int index;
        if(currentPlayer == bPlayer1) {
            do {
                index = p1.nextInt();
            } while(index > p1.getHand().size() || index < 0)
            Card topOfStack = stack.get(0);
            String stackCardRank = topOfStack.getRank();
            String stackCardSuit = topOfStack.getSuit();
            Card fromHand = p1Hand.get(index);
            if(fromHand.getRank().equals(stackCardRank) || fromHand.getSuit().equals(stackCardSuit)) {
                stack.add(0, p1.get(index));
                System.out.println("You placed the card: " + stack.get(0));
            }
        }
        else {
            do {
                index = p2.nextInt();
            } while(index > p2.getHand().size() || index < 0)
        }
        Card topOfStack = stack.get(0);
        String stackCardRank = topOfStack.getRank();
        String stackCardSuit = topOfStack.getSuit();
        Card fromHand = p2Hand.get(index);
        if(fromHand.getRank().equals(stackCardRank) || fromHand.getSuit().equals(stackCardSuit)) {
            stack.add(0, p2.get(index));
            System.out.println("You placed the card: " + stack.get(0));
        }
    }
    private void addCard() {
        if(currentPlayer == bPlayer1) {
            p1Hand.deal();
        }
        p2Hand.deal();
    }
    public boolean hasWon() {
        if(currentPlayer == bPlayer1) {
            if(p1Hand.size() == 0) {
                return true;
            }
            return false;
        }
        else {
            if(p2Hand.size() == 0) {
                return true;
            }
            return false;
        }
    }
}