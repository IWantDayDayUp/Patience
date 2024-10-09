public enum Value {
    ACE('A'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('T'),
    JACK('J'), QUEEN('Q'), KING('K');

    private final char value;

    /**
     * Constructor for value
     * 
     * @param value: the value of the card
     */
    private Value(char value) {
        this.value = value;
    }

    /**
     * @return the string representation of the value
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
