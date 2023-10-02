
public class Card implements Comparable<Card> {
	
    // Fields
    private int suit;  // 0 Clubs 1 Diamonds 2 Hearts 3 Spades
    private int rank;  // 1 Ace 2-10 11Jack 12Queen 13King

    // Default
    public Card() {
        this.suit = 0; // Default to Clubs
        this.rank = 1; // Default to Ace
    }

    // Suit as int, Rank as int
    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Suit as String, Rank as String
    public Card(String suit, String rank) {
        this.suit = suitStringToInt(suit);
        this.rank = rankStringToInt(rank);
    }

    // Suit as String, Rank as int
    public Card(String suit, int rank) {
        this.suit = suitStringToInt(suit);
        this.rank = rank;
    }

    // Suit as int, Rank as String
    public Card(int suit, String rank) {
        this.suit = suit;
        this.rank = rankStringToInt(rank);
    }

    // Copy constructor
    public Card(Card other) { // looked up what a copy constructor was
        this.suit = other.getSuitInt();
        this.rank = other.getRank();
    }

    // Getters
    public String getSuitStr() { // easier to use switch here, looked up how to do it cause forgot
        switch (suit) {
            case 0:
                return "Clubs";
            case 1:
                return "Diamonds";
            case 2:
                return "Hearts";
            case 3:
                return "Spades";
            default:
                return "Invalid Suit";
        }
    }

    public int getRank() {
        return rank;
    }

    // Methods
    public String toString() {
        return getRankStr() + " of " + getSuitStr();
    }

    public String getRankStr() {
        switch (rank) {
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return Integer.toString(rank);
        }
    }

    public int getSuitInt() {
        return suit;
    }

    // compareTo --> compare cards
    public int compareTo(Card other) {
        // Compare by rank first, then by suit if ranks are equal
        int rankComparison = Integer.compare(this.rank, other.rank);
        if (rankComparison != 0) {
            return rankComparison;
        } else {
            return Integer.compare(this.suit, other.suit);
        }
    }

    // equals --> compare cards equaly
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Card)) {
            return false;
        }
        Card otherCard = (Card) other;
        return this.suit == otherCard.suit && this.rank == otherCard.rank;
    }

    // convert suit string to int
    private int suitStringToInt(String suit) {
        switch (suit.toLowerCase()) { //forgot this exists, makes things very easy. definitely over-thinking in pseudocode
            case "clubs":
                return 0;
            case "diamonds":
                return 1;
            case "hearts":
                return 2;
            case "spades":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid suit: " + suit);
        }
    }

    // convert rank string to int
    private int rankStringToInt(String rank) {
        switch (rank.toLowerCase()) {
            case "ace":
                return 1;
            case "jack":
                return 11;
            case "queen":
                return 12;
            case "king":
                return 13;
            default:
                try {
                    int rankValue = Integer.parseInt(rank);
                    if (rankValue >= 2 && rankValue <= 10) {
                        return rankValue;
                    }
                } catch (NumberFormatException e) {
                    // not a rank string
                }
                throw new IllegalArgumentException("wrong: " + rank);
        }
    }
}