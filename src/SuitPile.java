
/**
 * A model for the SuitPile, inherits from class 'Pile'.
 * 
 * Add additional implementations as follows:
 * - (Override)isNextCard(): Verify that the new card can add to the
 * SuitPile based on the value and suit of the new card
 */
public class SuitPile extends Pile {

    // Suit: HEARTS('H'), DIAMONDS('D'), CLUBS('C'), SPADES('S')
    public final Suit suit;

    public SuitPile(Suit suit) {
        this.suit = suit;
    }

    /**
     * @return the suit of the pile
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * Verify that the card can add to this SuitPile based on follows:
     * 
     * - the suits should be different
     * - the values should be sequential and ascending
     */
    @Override
    public boolean isNextCard(Card card) {
        return this.isNextValue(card) && this.isSameSuit(card);
    }

    /**
     * Verify that whether the new card is the same suit as the last card
     */
    @Override
    public boolean isSameSuit(Card card) {
        return this.getSuit().equals(card.getSuit());
    }

    /**
     * Verify that card values are sequential and ascending (more by 1)
     */
    @Override
    public boolean isNextValue(Card card) {
        if (this.getSize() == 0) {
            return card.getCardNumber() == 1;
        }

        return card.getCardNumber() - this.cards.getLast().getCardNumber() == 1;
    }

    /**
     * Return a string representation of the Suit Pile
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "    ";
        } else {
            return this.cards.getLast().toString();
        }
    }
}
