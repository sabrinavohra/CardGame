import java.util.ArrayList;

public class Player {
    // Creates instance variables
    private String name;
    private ArrayList<Card> hand;
    private int points;

    // Constructor that sets Player's name as given name and sets points to 0
    public Player(String name) {
        this.name = name;
        points = 0;
    }

    // Constructor that sets Player's name as given name and sets Hand to given Hand
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    // Getter method for name instance variable
    public String getName() {
        return this.name;
    }

    // Getter method for Hand instance variable
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    // Getter method for Points instance variable
    public int getPoints() {
        return this.points;
    }

    // Adds given number of points to points
    public void addPoints(int pts) {
        points += pts;
    }

    // Adds given Card to Hand instance variable
    public void addCard(Card crd) {
        hand.add(crd);
    }

    // Returns String as "(Name) has (# of Points). (Name)'s cards: (Hand)
    public String toString() {
        return name + " has " + points + " points \n" + name + "'s cards: " + hand;
    }
}