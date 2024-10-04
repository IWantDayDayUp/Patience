public class DrawPile extends Pile {
    public DrawPile() {

    }

    /**
     * Uncover a new card
     * 
     * @return boolean: whether
     */
    public boolean draw() {
        int num = this.cards.size();

        if (num > 1) {

            Card oldCard = this.cards.get(0);
            this.cards.remove(0);
            oldCard.setIsFaceUp(false);

            Card newCard = this.cards.get(0);
            newCard.setIsFaceUp(true);

            this.cards.add(oldCard);

            return true;
        } else if (num == 1) {
            System.out.println("There is only one card left, no more cards!");
            return false;
        } else {
            System.out.println("The drew pile is empty, no more cards!");
            return false;
        }
    }

    /*
     * Returns a string representation of the DrawPile. Shows up to the top
     * three cards.
     */
    @Override
    public String toString() {
        String info = "";
        int num = this.cards.size();

        for (int i = 0; i < Math.min(3, num); i++) {
            info += this.cards.get(i).toString();
        }

        info += " X " + num + " left";

        return info;
    }
}
