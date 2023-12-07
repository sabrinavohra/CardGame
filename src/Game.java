import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player p1;
    private ArrayList<Card> p1Hand;
    private Player p2;
    private ArrayList<Card> p2Hand;
    private Deck theDeck;
    private ArrayList<Card> stack;
    private boolean currentPlayer;
    public static boolean bPlayer1 = true;
    public static boolean bPlayer2 = false;

    public Game(Player player1, Player player2)
    {
        p1 = player1;
        p1Hand = new ArrayList<Card>();
        p2 = player2;
        p2Hand = new ArrayList<Card>();
        currentPlayer = true;
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] suits = {"yellow", "green", "blue", "red"};
        int[] points = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        theDeck = new Deck(ranks, suits, points);
        stack = new ArrayList<Card>();
    }

    public static void main(String[] args) {
        Player sabrina = new Player("sabrina");
        Player other = new Player("other person");
        Game newGame = new Game(sabrina, other);
        newGame.playGame();
    }
    public void printInstructions() {
        System.out.println("This is a classic game of Uno!");
        System.out.println("Put down a card that matches the one on the top of the deck in either color or number, " +
                "until you have no cards left!");
    }
    private void playGame() {
        Scanner input = new Scanner(System.in);
        printInstructions();
        dealCards();
        while (hasWon() == false) {
            if (currentPlayer == bPlayer1) {
                System.out.println("Here's " + p1.getName() + "'s hand: ");
                showHand();
                System.out.println("The top card in the stack is: " + stack.get(0));
                System.out.println("Would you (" + p1.getName() + ") like to place a card? Respond no if you have no card that matches the top card in the stack.");
                String response = input.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    placeCard();
                } else {
                    addCard();
                }
                currentPlayer = !currentPlayer;
            }
            System.out.println("Here's" + p2.getName() + "'s hand: ");
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
                System.out.println("You placed the card: " + stack.get(0));
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
            System.out.println("You placed the card: " + stack.get(0));
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