public class Card {
    private final Suit suit; // Suit: Hearts, Diamond, Clubs, Spades
    private final Value value; // Value: A, 2, 3, ..., 10, J, Q, K
    private final String color; // red / black

    private boolean isFaceUp; // Whether the card face up

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        this.color = this.suit.getColor();
        this.isFaceUp = false; // default: face down
    }

    /**
     * @return the value of the card
     */
    public Value getValue() {
        return this.value;
    }

    /**
     * @return the Suit of the card
     */
    public Suit getSuit() {
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
     * Convert the Value of the card to number
     * 
     * Example: Value.ACE -> 0, Value.King -> 13
     */
    public int getCardNumber() {
        return switch (this.value) {
            case Value.ACE -> 0;
            case Value.TEN -> 10;
            case Value.JACK -> 11;
            case Value.QUEEN -> 12;
            case Value.KING -> 13;
            default -> Integer.parseInt(this.value.toString());
        };
    }

    /**
     * @param whether the card is faced up
     */
    public void setIsFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
    }

    /**
     * @return string representation the card
     */
    @Override
    public String toString() {
        return this.isFaceUp ? "[" + this.suit + this.value + "]" : "[XX]";
    }
}
