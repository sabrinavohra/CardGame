import org.w3c.dom.ls.LSOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Declares instance variables
    // Declares separate different variable for Players
    private Player p1;
    private ArrayList<Card> p1Hand;
    private Player p2;
    private ArrayList<Card> p2Hand;
    private Deck theDeck;
    private ArrayList<Card> stack;
    private Player currentPlayerInfo;
    private boolean currentPlayer;
    public static boolean bPlayer1 = true;
    public static boolean bPlayer2 = false;

    // Constructor for Game with 2 players
    public Game(Player player1, Player player2)
    {
        // Sets instance variables to values
        // Creates Players and their Hands
        p1 = player1;
        p1Hand = new ArrayList<Card>();
        p2 = player2;
        p2Hand = new ArrayList<Card>();
        // Sets currentPlayer to 1st player
        currentPlayer = bPlayer1;
        currentPlayerInfo = p1;
        // Creates deck
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] suits = {"yellow", "green", "blue", "red"};
        int[] points = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        theDeck = new Deck(ranks, suits, points);
        // Sets stack variable for the upwards-facing cards (the ones the user places)
        stack = new ArrayList<Card>();
    }

    // Prints instructions
    public void printInstructions() {
        System.out.println("Welcome to Uno!");
        System.out.println("Put down a card that matches the one on the top of the deck in either color or number, " +
                "until you have no cards left!");
    }

    // Method to play the Game
    private void playGame() {
        // Creates new Scanner for user input
        Scanner input = new Scanner(System.in);
        printInstructions();
        // Deals cards to both players
        dealCards();
        // Makes sure a Player hasn't won while running loop
        while (hasWon() == false) {
            // Runs for Player 1
            //if (currentPlayer == bPlayer1) {
                // Prints and shows hand for user
                System.out.println("Here's " + currentPlayerInfo.getName() + "'s hand: ");
                showHand();
                // Prints top Card in stack
                System.out.println("The top card in the stack is: " + stack.get(0));
                // Asks user if they want to place a card - in case they don't have a playable card
                System.out.println("Would you (" + currentPlayerInfo.getName() + ") like to place a card? Respond no " +
                        "if you have no card that matches the top card in the stack.");
                // Uses Scanner to receive user input
                String response = input.nextLine();
                // Checks user's response and either a) Places a Card or b) Adds a Card to their hand
                if (response.equalsIgnoreCase("yes")) {
                    placeCard();
                } else {
                    addCard();
                }
                // Switches to Player 2
                if(currentPlayer ==  bPlayer1) {
                    currentPlayer = !currentPlayer;
                    currentPlayerInfo = p2;
                }
                else {
                    currentPlayer = !currentPlayer;
                    currentPlayerInfo = p1;
                }
            }
//            // Runs same process but for Player 2
//            System.out.println("Here's " + p2.getName() + "'s hand: ");
//            showHand();
//            System.out.println("The top card in the stack is: " + stack.get(0));
//            System.out.println("Would you (" + p2.getName() + ") like to place a card? Respond no if you have no card that matches the top card in the stack.");
//            String response = input.nextLine();
//            if(response.equalsIgnoreCase("yes")) {
//                placeCard();
//            }
//            else {
//                addCard();
//            }
//            currentPlayer = !currentPlayer;
//        }
        System.out.println("Great game!");
    }
        //while(someone hasn't won / hasn't gotten rid of all their cards)
            //let player put down card that matches with the one on top of stack in at least one way (check) - placeCard();
            //let other play do the same thing - placeCard();
            //if a player (has no cards that match the top one)
                //add card to their hand until they can put down a card - addCard();
            //after each turn check if: they have won - hasWon() and if a player hasn't won then change currentPlayer to other player;
                // outside of while loop: end game (!!) if they have and ask if they want to play again - playAgain();
                //continue game if they haven't

    private void dealCards() {
        theDeck.shuffle();
        for(int i = 0; i < 8; i++) {
            p1Hand.add(theDeck.deal());
            p2Hand.add(theDeck.deal());
        }
        stack.add(theDeck.deal());
    }
    private void showHand() {
        if(currentPlayer == bPlayer1) {
            for(int i = 0; i < p1Hand.size(); i++) {
                System.out.print(p1Hand.get(i) + ", ");
            }
            System.out.println();
        }
        else {
            for(int i = 0; i < p2Hand.size(); i++) {
                System.out.print(p2Hand.get(i) + ", ");
            }
            System.out.println();
        }
    }
    private void placeCard() {
        Scanner response = new Scanner(System.in);
        System.out.println("Enter the index of the card you want to play!");
        int index;
        if(currentPlayer == bPlayer1) {
            do {
                index = response.nextInt();
            } while(index >= p1Hand.size() || index < 0);
            Card topOfStack = stack.get(0);
            String stackCardRank = topOfStack.getRank();
            String stackCardSuit = topOfStack.getSuit();
            Card fromHand = p1Hand.get(index);
            if(fromHand.getRank().equals(stackCardRank) || fromHand.getSuit().equals(stackCardSuit)) {
                stack.add(0, p1Hand.get(index));
                //System.out.println("You placed the card " + fromHand);
                p1Hand.remove(fromHand);
            }
        }
        else {
            do {
                index = response.nextInt();
            } while(index > p2Hand.size() || index < 0);
        }
        Card topOfStack = stack.get(0);
        String stackCardRank = topOfStack.getRank();
        String stackCardSuit = topOfStack.getSuit();
        Card fromHand = p2Hand.get(index);
        if(fromHand.getRank().equals(stackCardRank) || fromHand.getSuit().equals(stackCardSuit)) {
            stack.add(0, p2Hand.get(index));
            //System.out.println("You placed the card " + fromHand);
            p2Hand.remove(fromHand);
            //System.out.println("You placed the card: " + stack.get(0));
        }
    }
    private void addCard() {
        if(currentPlayer == bPlayer1) {
            p1Hand.add(theDeck.deal());
        }
        p2Hand.add(theDeck.deal());
    }
    private boolean hasWon() {
        if(currentPlayer == bPlayer1) {
            if(p1Hand.size() == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(p2Hand.size() == 0) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    public static void main(String[] args) {
        Player sabrina = new Player("sabrina");
        Player other = new Player("other player");
        Game newGame = new Game(sabrina, other);
        newGame.playGame();
    }
}