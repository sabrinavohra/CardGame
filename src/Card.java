public class Card {
    // Declares instance variables
    private int point;
    private String suit;
    private String rank;

    // Constructor for any given card
    public Card(int point, String suit, String rank) {
        this.point = point;
        this.suit = suit;
        this.rank = rank;
    }
    // Sets points to a given value
    public void setPoint(int point) {
        this.point = point;
    }

    // Sets suit to a given value
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // Sets rank to a given value
    public void setRank(String rank) {
        this.rank = rank;
    }

    // Gets instance variable "point"'s value
    public int getPoint() {
        return point;
    }

    // Gets instance variable "suit"'s value
    public String getSuit() {
        return suit;
    }

    // Gets instance variable's "rank"'s value
    public String getRank() {
        return rank;
    }

    // Prints out Card in Uno format - "color rank"
    public String toString() {
        return this.suit + " " + this.rank;
    }
}


