import java.util.ArrayList;

/**
 * A model for a set of DrawPile, inherits from class 'Pile'.
 *
 * Add additional implementations as follows:
 * - Verify that whether draw a card is possible without actually doing the
 * 'draw' command
 * - Draw a new card,
 * 
 */
public class DrawPile extends Pile {
    public ArrayList<Card> faceUpCards; // the cards that will 'face up'

    public DrawPile() {
        this.faceUpCards = new ArrayList<>();
    }

    /**
     * Draw a new card and maintain a maximum of 3 cards at the 'face up' state
     * 
     * Replace the first 'face up' card with a 'face down' card in the DrawPile
     */
    public void draw() {
        // New card that to show
        Card newCard = this.cards.removeLast();
        newCard.setIsFaceUp(true);
        this.faceUpCards.addLast(newCard);

        // A maximum of 3 cards will be 'face up', and the excess will need to be set
        // 'face down'
        if (this.faceUpCards.size() > 3) {
            Card oldCard = this.faceUpCards.removeFirst();
            oldCard.setIsFaceUp(false);
            this.addCard(oldCard);
        }
    }

    /**
     * Verify that whether draw a card is possible without actually doing the 'draw'
     * command
     */
    public boolean isDrawPossible() {
        return !this.isEmpty() && !this.cards.getLast().getIsFaceUp();
    }

    /*
     * Returns a string representation of the DrawPile. Shows up to the top
     * three cards.
     */
    @Override
    public String toString() {
        String info = "";
        for (Card card : faceUpCards) {
            info += card;
        }

        return info;
    }
}
