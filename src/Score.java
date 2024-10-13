public class Score {
    private int score; // the score got by player

    public Score() {
        this.score = 0;
    }

    /**
     * Update the score based on the command type and number of cards that to be
     * moved
     */
    public void update(Command command) {

        if (!command.isMove()) {
            return;
        }

        if (command.isMoveFromDraw() && command.isMoveToSuit()) {
            // 10 points per card moving from the uncovered pile to one of the suits
            this.score += 10;
        } else if (command.isMoveFromLane() && command.isMoveToSuit()) {
            // 20 points per card moving from one of the lanes to one of the suits
            this.score += 20 * command.numCards;
        } else {
            // 5 points per card moving between lanes
            this.score += 5 * command.numCards;
        }
    }

    /**
     * @return string representation the score
     */
    @Override
    public String toString() {
        return String.valueOf(this.score);
    }
}
