import java.util.ArrayList;

public class LanePile extends Pile {
    public final String lineLabel;

    public LanePile(String lineLabel) {
        this.lineLabel = lineLabel;
    }

    public String getLineLabel() {
        return this.lineLabel;
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
     * Remove a few of cards(the top ones) from the pile by calling
     * 'this.removeCard()'
     * 
     * @param num: the number of cards to be removed
     */
    public void removeCards(int num) {

        for (int i = 0; i < num; i++) {
            // TODO
        }
    }
}
