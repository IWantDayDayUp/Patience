public class SuitPile extends Pile {
    public final char suit;
    public final int maxSize;

    public SuitPile(char suit) {
        this.suit = suit;
        this.maxSize = 13;
    }

    /**
     * @return the suit of the pile
     */
    public char getSuit() {
        return this.suit;
    }

    /**
     * @return whether the cards of the pile is in perfect order
     */
    public boolean isPefectOrder() {
        // TODO
        return true;
    }

    /**
     * Return a string representation of the Suit Pile
     * which is either an empty pile or the top card.
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        } else {
            return this.getTopcard().toString();
        }
    }
}
