public enum Suit {
    HEARTS('H', "red"), DIAMONDS('D', "red"), CLUBS('C', "black"), SPADES('S', "black");

    private final char suit;
    private final String color;

    /**
     * Constructor for Suit
     * 
     * @param suit: 'H', 'D', 'C', 'S'
     */
    private Suit(char suit, String color) {
        this.suit = suit;
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    /**
     * @return the string representation of the suit
     */
    @Override
    public String toString() {
        return String.valueOf(this.suit);
    }
}
