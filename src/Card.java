public class Card {
    // 'S', 'H', 'C', 'D'
    private final String suit;
    // A-2-10-J-Q-K
    private final String number;
    // red/black
    private final String color;
    private boolean isFaceUp;

    // TODO: 下列函数应该不在card类中吧? 应该在controller//pile类中吧?
    // - isSameSuit()
    // - isNextLowerCard()
    // - isNextHigherCard()
    // - isDifferentColor()
    // - isNextInLine()
    // - isNextInSuit()

    public Card(String suit, String number, String color, boolean isFaceUp) {
        this.suit = suit;
        this.number = number;
        this.color = color;
        this.isFaceUp = isFaceUp;
    }

    /**
     * @return the Number of the card
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * @return the Suit of the card
     */
    public String getSuit() {
        return this.suit;
    }

    /**
     * @return the Color of the card
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @return whether the card is faced up
     */
    public boolean getIsFaceUp() {
        return this.isFaceUp;
    }

    /**
     * @param whether the card is faced up, 'true' for 'face up', 'false' foe 'face
     *                down'
     */
    public void setIsFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
    }

    /**
     * 
     * 
     * @return string representation the card
     */
    @Override
    public String toString() {
        if (this.isFaceUp) {
            return "[" + this.number + ' ' + this.suit + "]";
        } else {
            return "[ ]";
        }
    }
}

  
         