import java.util.ArrayList;

/**
 * A model for a set of cards, we can do the following:
 * 
 * - Get the number of cards in this pile
 * - Get whether this pile is empty
 * - Add a new card to this pile
 * - Remove a card from this pile
 * - etc
 * 
 */
public class Pile {

    public ArrayList<Card> cards;

    public Pile() {
        this.cards = new ArrayList<>();
    }

    /**
     * Return the number of cards of this pile
     */
    public int getSize() {
        return this.cards.size();
    }

    /**
     * Return whether this pile is empty
     */
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    /**
     * Set the top card of this pile to 'face up'
     */
    public void setTopCardFaceUp() {
        if (this.getSize() > 0) {
            this.cards.getLast().setIsFaceUp(true);
        }
    }

    /**
     * Add a new card as the last element
     */
    public void addCard(Card card) {
        this.cards.addLast(card);
    }

    /**
     * Add a few of cards to the pile by calling 'this.addCard()'
     */
    public void addCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            this.addCard(card);
        }
    }

    /**
     * Remove and return the last card of this pile
     */
    public Card removeCard() {
        return this.cards.removeLast();
    }

    /**
     * Verify that the cards are consecutive
     */
    public boolean isNextCard(Card card) {
        return true;
    }

    /**
     * Verify that the cards have same suit
     * 
     * @param card
     * @return
     */
    public boolean isSameSuit(Card card) {
        return true;
    }

    /**
     * Verify that the card values are consecutive
     * 
     * @param card
     * @return
     */
    public boolean isNextValue(Card card) {
        return true;
    }

    /**
     * Verify that the cards have same color
     * 
     * @param card
     * @return
     */
    public boolean isSameColor(Card card) {
        return true;
    }
}