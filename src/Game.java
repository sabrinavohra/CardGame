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
        // Makes sure a Player hasn't won in order to run loop
        while (hasWon() == false) {
            // Runs for Player 1
            if (currentPlayer == bPlayer1) {
                // Prints and shows hand for user
                System.out.println("Here's " + p1.getName() + "'s hand: ");
                showHand();
                // Prints top Card in stack
                System.out.println("The top card in the stack is: " + stack.get(0));
                // Asks user if they want to place a Card - in case they don't have a playable card
                System.out.println("Would you (" + p1.getName() + ") like to place a card? Respond no if you have no card that matches the top card in the stack.");
                // Uses Scanner to receive user input
                String response = input.nextLine();
                // Checks user's response and either a) Places a Card or b) Adds a Card to their hand
                if (response.equalsIgnoreCase("yes")) {
                    placeCard();
                } else {
                    addCard();
                }
                // Switches Player 1 to Player 2
            }
            // Runs process for Player 2
            System.out.println("Here's " + p2.getName() + "'s hand: ");
            showHand();
            System.out.println("The top card in the stack is: " + stack.get(0));
            System.out.println("Would you (" + p2.getName() + ") like to place a card? Respond no if you have no card that matches the top card in the stack.");
            String response = input.nextLine();
            if(response.equalsIgnoreCase("yes")) {
                placeCard();
            }
            else {
                addCard();
            }
            currentPlayer = !currentPlayer;
        }
        System.out.println("Great game!");
    }
    // Deals Cards to Players
    private void dealCards() {
        // Shuffles Deck before dealing
        theDeck.shuffle();
        // Deals hand to both Players
        for(int i = 0; i < 8; i++) {
            p1Hand.add(theDeck.deal());
            p2Hand.add(theDeck.deal());
        }
        // Flips over first card
        stack.add(theDeck.deal());
    }
    // Shows hand for given Player
    private void showHand() {
        // Shows Player 1's hand
        if(currentPlayer == bPlayer1) {
            // Prints each Card in hand individually
            for(int i = 0; i < p1Hand.size(); i++) {
                System.out.print(p1Hand.get(i) + ", ");
            }
            // Prints new line
            System.out.println();
        }
        // Does same process for Player 2
        else {
            for(int i = 0; i < p2Hand.size(); i++) {
                System.out.print(p2Hand.get(i) + ", ");
            }
            System.out.println();
        }
    }
    // Places Card on stack
    private void placeCard() {
        // Creates Scanner to get user input later
        Scanner response = new Scanner(System.in);
        System.out.println("Enter the index of the card you want to play!");
        int index;
        if(currentPlayer == bPlayer1) {
            // Makes sure user inputs valid response
            do {
                index = response.nextInt();
            } while(index >= p1Hand.size() || index < 0);
            // Creates variable for the Card on the top of the stack
            Card topOfStack = stack.get(0);
            // Gets rank of top Card
            String stackCardRank = topOfStack.getRank();
            // Gets suit of top Card
            String stackCardSuit = topOfStack.getSuit();
            // Gets Card from user's input
            Card fromHand = p1Hand.get(index);
            // Checks to make sure Card is valid by comparing it to top Card on stack
            if(fromHand.getRank().equals(stackCardRank) || fromHand.getSuit().equals(stackCardSuit)) {
                // Adds user's Card to top of stack
                stack.add(0, p1Hand.get(index));
                // Tells user which Card they placed
                System.out.println("You placed the card " + fromHand);
                // Removes their Card from their hand
                p1Hand.remove(fromHand);
            }
        }
        // Repeats process for Player 2
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
            System.out.println("You placed the card " + fromHand);
            p2Hand.remove(fromHand);
        }
    }
    // Adds Card to Player's hand
    private void addCard() {
        // Adds card from Deck to Player 1's hand
        if(currentPlayer == bPlayer1) {
            p1Hand.add(theDeck.deal());
        }
        // Adds card from Deck to Player 2's hand
        p2Hand.add(theDeck.deal());
    }

    // Checks to see if user has won
    private boolean hasWon() {
        // Checks for Player 1
        if(currentPlayer == bPlayer1) {
            // Checks if the size of Player 1's hand is 0 - meaning they have no Cards
            if(p1Hand.size() == 0) {
                return true;
            }
            // Returns false if Player 1's hand has more than 1 Card
            return false;
        }
        // Repeats process for Player 2
        else {
            if(p2Hand.size() == 0) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    // Main method
    public static void main(String[] args) {
        // Initializes two Players
        Player sabrina = new Player("sabrina");
        Player other = new Player("other player");
        // Creates new Game using Constructor with two Players
        Game newGame = new Game(sabrina, other);
        // Plays a game!
        newGame.playGame();
    }
}