// Author: Joe Lentini
// Card Sorting Deck Class
// -With bonus sorting methods

import java.util.Arrays;
import java.util.Random;

public class Deck { // represents full deck of cards with suit and rank
	
	// Fields
    private Card[] cards;
    private int topCardIndex;

    // Default 
    public Deck() {
        cards = new Card[52];
        topCardIndex = 51;
        
        int index = 0;
        for (int suit = 0; suit < 4; suit++) { // builds the FULL deck of 52 cards in ORDER
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card(suit, rank);
                index++;
            }
        }
    }

    // One Boolean parameter (true - sorted, false - shuffled)
    public Deck(boolean sorted) {
        this(); // create a sorted deck
        if (!sorted) {
            shuffle();
        }
    }

    // Copy constructor
    public Deck(Deck other) {
        this.cards = new Card[other.cards.length];
        for (int i = 0; i < other.cards.length; i++) {
            this.cards[i] = new Card(other.cards[i]);
        }
        this.topCardIndex = other.topCardIndex; // copies over cards to a new deck 1 by 1
    }

    // shuffles the deck
    public void shuffle() {
        Random random = new Random();
        for (int i = topCardIndex; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swaps cards[i] and cards[j]
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    // toString
    public String toString() {
        if (cards.length == 52) {
            String result = "";
            for (int i = 0; i < 13; i++) {
                result += cards[i] + "\t\t" + cards[i + 13] + "\t\t" + // used \t to create tabs for columns
                          cards[i + 26] + "\t\t" + cards[i + 39] + "\n";
            }
            return result;
        } else {
            String result = "";
            for (int i = 0; i <= topCardIndex; i++) {
                result += (i + 1) + ". " + cards[i] + "\n";
            }
            return result;
        }
    }
    
    
    // equals 
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Deck)) {
            return false;
        }
        Deck otherDeck = (Deck) other;
        return Arrays.equals(cards, otherDeck.cards);
    }
    

    // Deal 
    public Deck[] deal(int hands, int cardsPerHand) {
        int totalCardsToDeal = hands * cardsPerHand;
        if (totalCardsToDeal > topCardIndex + 1) { // checks if enough
            return null; // Not enough cards
        }

        Deck[] handsDealt = new Deck[hands]; // creates array of decks, representing hands
        for (int i = 0; i < hands; i++) { // enters loop for distribution
            handsDealt[i] = new Deck();
            for (int j = 0; j < cardsPerHand; j++) {
                handsDealt[i].cards[j] = cards[topCardIndex];
                topCardIndex--;
            }
        }

        return handsDealt;
    }

    
    // pick a random card and remove it from the deck
    public Card pick() {
        if (topCardIndex < 0) {
            return null; // deck is empty
        }
        Card pickedCard = cards[topCardIndex];
        cards[topCardIndex] = null; // remove the card
        topCardIndex--;
        return pickedCard;
    }

    
    // Selection sort
    public void selectionSort() {
        for (int i = 0; i < cards.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[j].compareTo(cards[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // Swap cards[i] and cards[minIndex]
            Card temp = cards[i];
            cards[i] = cards[minIndex];
            cards[minIndex] = temp;
        }
    }
    

    // Merge sort
    public void mergeSort() {
        cards = mergeSort(cards);
    }

    private Card[] mergeSort(Card[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return arr;
        }

        int mid = length / 2;
        Card[] left = Arrays.copyOfRange(arr, 0, mid); // creates new array by copying range of existing array
        Card[] right = Arrays.copyOfRange(arr, mid, length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private Card[] merge(Card[] left, Card[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int totalLength = leftLength + rightLength;
        Card[] merged = new Card[totalLength];

        int i = 0, j = 0, k = 0;
        while (i < leftLength && j < rightLength) {
            if (left[i].compareTo(right[j]) <= 0) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < leftLength) {
            merged[k++] = left[i++];
        }

        while (j < rightLength) {
            merged[k++] = right[j++];
        }

        return merged;
    }

    
    
    // BONUS: quickSort 
    public void quickSort() {
        quickSort(cards, 0, topCardIndex);
    }

    private void quickSort(Card[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);

            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private int partition(Card[] arr, int low, int high) {
        Card pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;

                // Swap arr[i] and arr[j]
                Card temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        Card temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    
    
    // BONUS: insertionSort
    public void insertionSort() {
        for (int i = 1; i <= topCardIndex; i++) {
            Card key = cards[i];
            int j = i - 1;

            while (j >= 0 && cards[j].compareTo(key) > 0) {
                cards[j + 1] = cards[j];
                j--;
            }
            cards[j + 1] = key;
        }
    }
    
    
    // Get top card index
    public int getTopCard() {
        return topCardIndex;
    }
}



