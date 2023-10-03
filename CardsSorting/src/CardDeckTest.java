// Joe Lentini
// implement brainstorm testing 

public class CardDeckTest {
	
    public static void main(String[] args) {
        // Test Card class
        testCardClass();

        // Test Deck class
        testDeckClass();
    }

    private static void testCardClass() {
        System.out.println("Testing Card class:");

        // Test Constructors
        Card card1 = new Card(); // default constructor makes an Ace of Clubs
        Card card2 = new Card(1, 3); // constructor with suit and rank
        Card card3 = new Card("Hearts", "King"); // constructor with suit and rank as strings

        System.out.println("Card 1: " + card1);
        System.out.println("Card 2: " + card2);
        System.out.println("Card 3: " + card3);

        // Test getters
        System.out.println("Card 1 Suit: " + card1.getSuitStr());
        System.out.println("Card 1 Rank: " + card1.getRank());

        // Test compareTo
        int compareResult = card1.compareTo(card2);
        System.out.println("(Card 1 .compareTo Card 2): " + compareResult);

        // Test equals
        boolean areEqual = card1.equals(card2);
        System.out.println("(Card 1 .equal Card 2): " + areEqual);
    }

    private static void testDeckClass() {
        System.out.println("\nTesting Deck class:");

        // Test Constructors
        Deck sortedDeck = new Deck(true); // Sorted deck
        Deck shuffledDeck = new Deck(false); // Shuffled deck

        System.out.println("Sorted Deck:");
        System.out.println(sortedDeck);

        System.out.println("Shuffled Deck:");
        System.out.println(shuffledDeck);

        // Test shuffle
        shuffledDeck.shuffle();
        System.out.println("Deck After Shuffle:");
        System.out.println(shuffledDeck);

        // Test deal
        int hands = 4;
        int cardsPerHand = 5;
        Deck[] dealtHands = shuffledDeck.deal(hands, cardsPerHand);
        if (dealtHands != null) {
            for (int i = 0; i < hands; i++) {
                System.out.println("Hand " + (i + 1) + ":");
                System.out.println(dealtHands[i]);
            }
        } else {
            System.out.println("not enough cards");
        }

        // Test pick
        Card pickedCard = shuffledDeck.pick();
        System.out.println("picked Card: " + pickedCard);

        // Test sorting algorithms
        Deck unsortedDeck = new Deck(false); // Unsorted deck
        System.out.println("Unsorted Deck:");
        System.out.println(unsortedDeck);

        unsortedDeck.selectionSort();
        System.out.println("Unsorted Deck After Selection Sort:");
        System.out.println(unsortedDeck);

        unsortedDeck.mergeSort();
        System.out.println("Unsorted Deck After Merge Sort:");
        System.out.println(unsortedDeck);
        
        unsortedDeck.quickSort();
        System.out.println("Unsorted Deck After quick sort Sort:");
        System.out.println(unsortedDeck);
        
        unsortedDeck.insertionSort();
        System.out.println("Unsorted Deck After insertion Sort:");
        System.out.println(unsortedDeck);
        
    }
}