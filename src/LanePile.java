
/**
 * A model for the LanePile, inherits from class 'Pile'.
 * 
 * Add additional implementations as follows:
 * - Add/Remove multiple cards
 * - (Override)isNextCard(): Verify that the new card can add to the
 * LanePile based on the value and color of the new card
 */
public class LanePile extends Pile {

    public LanePile() {

    }

    /**
     * Remove a few of cards from the pile
     * 
     * @param num the number of cards to be removed
     */
    public void removeCards(int num) {
        for (int i = 0; i < num; i++) {
            this.removeCard();
        }
    }

    /**
     * Verify that the card can add to this LanePile based on follows:
     * 
     * - the colors should be different
     * - the values should be sequential and descending
     */
    @Override
    public boolean isNextCard(Card card) {
        return this.isNextValue(card) && !this.isSameColor(card);
    }

    /**
     * Verify that card values are sequential and descending (less by 1)
     */
    @Override
    public boolean isNextValue(Card card) {
        if (this.getSize() == 0) {
            return true;
        }

        return this.cards.getLast().getCardNumber() - card.getCardNumber() == 1;
    }

    /**
     * Verify that whether the new card is the same color as the last card
     */
    @Override
    public boolean isSameColor(Card card) {
        if (this.getSize() == 0) {
            return false;
        }

        return this.cards.getLast().getColor().equals(card.getColor());
    }
}
