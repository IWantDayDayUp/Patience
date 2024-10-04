import java.util.ArrayList;

public class Pile {

    public ArrayList<Card> cards;

    public Pile() {
    }


    /**
     * @return all cards in the pile
     */
    public ArrayList<Card> getAllCards() {
        return this.cards;
    }

    /**
     * @return the top card of the pile
     */
    public Card getTopcard() {
        return this.cards.get(0);
    }

    /**
     * @return the size(the number of cards) of the pile
     */
    public int getSize() {
        return this.cards.size();
    }

    /**
     * @return whether the pile is empty
     */
    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    /**
     * set the top card of the pile to 'face up'
     */
    public void setTopCardFaceUp() {
        Card card = this.cards.get(0);
        card.setIsFaceUp(true);
    }
    /**
     * Add a new card to the pile
     */
    public void addCard(Card card) {
        this.cards.add(0, card);
    }

    /**
     * 
     * Remove a card(the top one) from the pile
     */
    public Card removeCard() {
        Card card = this.getTopcard();
        this.cards.removeFirst();

        return card;
    }

}