import java.util.Collections;

/**
 * A model for the Deck, inherits from class 'Pile'.
 * 
 * Add additional implementations as follows:
 * - Shuffle the cards in this pile
 * - Deal a card from this pile
 */
public class Deck extends Pile {
    public final int num = 52;

    public Deck() {
        // Generate a deck of cards, total 13x4=52
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                this.cards.add(card);
            }
        }
    }

    /**
     * Shuffles the cards in the deck
     */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    /**
     * Deal a card to a new pile
     */
    public Card dealCard() {
        return this.cards.removeFirst();
    }
}
