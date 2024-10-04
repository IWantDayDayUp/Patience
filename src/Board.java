public class Board {

    public Deck deck;
    public DrawPile drawPile;
    public LanePile[] lanePiles;
    public SuitPile[] suitPiles;

    public Board() {

        this.deck = new Deck();
        this.deck.shuffle();

        this.drawPile = new DrawPile();
        this.lanePiles = new LanePile[7];
        this.suitPiles = new SuitPile[4];

        // - generate all cards, then shuffle to get lines/suits/foundation
        // initLines();
        // initSuits();
        // initDraw();
    }

    public void initLines() {
        for (int i = 0; i < 7; i++) {
            this.lanePiles[i] = new LanePile("null");
            for (int j = 0; j < i; j++) {
                this.lanePiles[i].addCard(this.deck.getTopCard());
            }
            this.lanePiles[i].setTopCardFaceUp();
        }
    }

    public void initDraw() {
        while (!this.deck.isEmpty()) {
            this.drawPile.addCard(this.deck.getTopcard());
        }
    }

    public void initSuits() {
        for (int i = 0; i < 4; i++) {
            this.suitPiles[i] = new SuitPile(Suit.values()[i]);
        }
    }

    public void displayBoard() {
        this.displayDrawPile();
        System.out.println();
        this.displaySuitPile();
        System.out.println();
        this.displayLinePile();
    }

    public void displayDrawPile() {
        System.out.println("  P: " + this.drawPile);
    }


    public void displaySuitPile() {
        for (int i = 0; i < 4; i++) {
            System.err.println(Suit.values()[i] + ": " + suitPiles[i]);
        }
    }


    public boolean draw() {
        return this.drawPile.draw();
    }

}
