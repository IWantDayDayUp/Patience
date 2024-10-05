public class Board {

    public Deck deck;
    public DrawPile drawPile;
    public LanePile[] lanePiles;
    public SuitPile[] suitPiles;

    public Score score;
    public int moveCnt;

    public boolean isNewBoard;

    public Board() {

        this.deck = new Deck();
        this.deck.shuffle();

        this.drawPile = new DrawPile();
        this.lanePiles = new LanePile[7];
        this.suitPiles = new SuitPile[4];

        this.score = new Score();
        this.moveCnt = 0;

        this.isNewBoard = true;

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

        this.displayGameInfo();
    }

    public void displayDrawPile() {
        System.out.println("  P: " + this.drawPile);
    }

    public void displayLinePile() {
        int maxSize = 0;
        for (LanePile lane : this.lanePiles) {
            maxSize = Math.max(maxSize, lane.getSize());
        }

        System.err.println(" 01  02  03  04  05  06  07");
        for (int i = 0; i < maxSize; i++) {
            for (LanePile lane : this.lanePiles) {
                if (lane.getSize() > i) {
                    System.out.print(lane.cards.get(lane.getSize() - 1 - i));
                } else {
                    System.out.print("    ");
                }
            }
        }
    }

    public void displaySuitPile() {
        for (int i = 0; i < 4; i++) {
            System.err.println(Suit.values()[i] + ": " + suitPiles[i]);
        }
    }

    public void displayGameInfo() {
        // TODO
        System.err.println(this.score + " " + this.moveCnt);
    }

    public boolean draw() {
        return this.drawPile.draw();
    }
    public void displayWelcome() {
        System.out.println("Hi!");
    }

    public void displayCommandNotExecuted() {
        // TODO
        System.out.println("Hi!");

    }

    public void doCommand(Command command) {
        boolean isSuccess;
        if (command.isDraw()) {
            isSuccess = this.drawPile.draw();
        } else {
            isSuccess = this.moveCard(command);
        }

        this.updateBoard(isSuccess, command);
    }

    public boolean isGameOver() {
        // TODO
        for (SuitPile suit : this.suitPiles) {
            if (suit.getSize() != 13) {
                return false;
            }
        }

        return true;
    }

    public void updateBoard(boolean isSuccess, Command command) {
        if (isSuccess) {
            this.isNewBoard = true;
            this.score.update(command);
            this.moveCnt += 1;
        } else {
            this.isNewBoard = false;
        }
    }
}
