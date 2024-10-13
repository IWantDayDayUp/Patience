import java.util.ArrayList;

/**
 * Model for Board, we can do follows:
 * 
 * - display the board, including all the cards, the score, and the number of
 * moves
 * - display information about the game: the rules, the welcome info, and the
 * game info
 * - verify that the command entered by player is executable, without actually
 * execute the command, for the safety of the code
 * - execute the command, then update the board
 */
public class Board {

    public Deck deck; // A deck of randomly generated cards, total 13x4=52 cards
    public DrawPile drawPile; // cards for draw
    public LanePile[] lanePiles; // cards are in not perfect order
    public SuitPile[] suitPiles; // cards in perfect order

    public Score score; // the score got by player
    public int moveCnt; // the number of commands executed by player

    public boolean isNewBoard; // update the board if true

    public Board() {

        // generate all cards, then shuffle
        this.deck = new Deck();
        this.deck.shuffle();

        this.drawPile = new DrawPile();
        this.lanePiles = new LanePile[7];
        this.suitPiles = new SuitPile[4];
        for (int i = 0; i < 4; i++) {
            this.suitPiles[i] = new SuitPile(Suit.values()[i]);
        }

        // init the info of the player
        this.score = new Score();
        this.moveCnt = 0;

        this.isNewBoard = true;

        // deal the cards to generate the init board
        for (int i = 0; i < 7; i++) {
            this.lanePiles[i] = new LanePile();
            for (int j = 0; j <= i; j++) {
                this.lanePiles[i].addCard(this.deck.dealCard());
            }
            this.lanePiles[i].setTopCardFaceUp();
        }
        while (!this.deck.isEmpty()) {
            this.drawPile.addCard(this.deck.dealCard());
        }
    }

    /**
     * Display the board if the board changes(the command execute successfully)
     */
    public void displayBoard() {
        if (!this.isNewBoard) {
            return;
        }

        System.out.println("Score: " + this.score.toString() + "    Move: " + this.moveCnt);
        this.displayLanePile();
        this.displayDrawPile();
        this.displaySuitPile();
        System.out.println();
    }

    /**
     * Display the DrawPile
     */
    public void displayDrawPile() {
        System.out.println("P: " + this.drawPile);
    }

    /**
     * Display the LanePile
     */
    public void displayLanePile() {
        int maxSize = 0;
        for (LanePile lane : this.lanePiles) {
            maxSize = Math.max(maxSize, lane.getSize());
        }

        System.out.println(" L1  L2  L3  L4  L5  L6  L7");
        for (int i = 0; i < maxSize; i++) {
            for (LanePile lane : this.lanePiles) {
                if (lane.getSize() > i) {
                    System.out.print(lane.cards.get(i));
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Display the DrawPile
     */
    public void displaySuitPile() {
        for (int i = 0; i < 4; i++) {
            System.out.print(Suit.values()[i] + ": " + suitPiles[i] + " ");
        }
    }

    /**
     * Display the related information when the game first start
     */
    public void displayFirstSatrt() {
        this.displayWelcome();
        this.displayGameInfo();
        this.displayRuleInfo();
    }

    /**
     * Display the Welcome info
     */
    public void displayWelcome() {
        System.out.println("Welcome to the Patience Game");
    }

    /**
     * Display the Game info
     */
    public void displayGameInfo() {
        System.out.println("Course: COMP41670 Software Engineering");
        System.out.println("Name: Shaofeng Wang");
        System.out.println("ID: 24202293");
    }

    /**
     * Display the Rule info: how to play the game
     */
    public void displayRuleInfo() {
        System.out.println("");
        System.out.println("You can do follows:");
        System.out.println("- Enter 'Q'/'q' to quit the game");
        System.out.println("- Enter 'D'/'d' to uncover a new card");
        System.out.println("- Enter <label1><label2> to move one card from <label1> to <label2>. E.g. 'P2' or '2D'");
        System.out.println(
                "- Enter <label1><label2><number> to move <number> cards from <label1> to <label2>. E.g.'413'");
        System.out.println("");
    }

    /**
     * Display the re-try info when the command has not been executed
     */
    public void displayCommandNotExecuted() {
        this.isNewBoard = false;
        System.out.println("The command has not been executed, please try again.");

    }

    /**
     * Execute the command enterd by player
     */
    public void executeCommand(Command command) {

        if (command.isDraw()) {
            this.drawPile.draw();
        } else {
            int idxTo = command.getIndex()[1];
            ArrayList<Card> cardsNeedToMove = this.getCardsNeedToMove(command, false);
            if (command.isMoveToLane()) {
                this.lanePiles[idxTo].addCards(cardsNeedToMove);
            } else {
                this.suitPiles[idxTo].addCards(cardsNeedToMove);
            }
        }

        this.updateBoard(command);
    }

    /**
     * Verify that the game is over(All cards should be in right place)
     */
    public boolean isGameOver() {
        for (SuitPile suit : this.suitPiles) {
            if (suit.getSize() != 13) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verify that the command can be executed
     * 
     * 1. the player want to draw a new card
     * 2. Whether the player can move cards from DrawPile/LanePile/SuitPile
     * 3. Whether the player can move cards to DrawPile/LanePile/SuitPile
     * 
     */
    public boolean isValidMove(Command command) {
        if (command.isDraw()) {
            return this.isValidDraw();
        }
        // System.out.println("is valid move from: " + this.isValidMoveFrom(command));
        // System.out.println("is valid move to: " + this.isValidMoveTo(command));
        return this.tryMultiCardsPossible(command) || (this.isValidMoveFrom(command) && this.isValidMoveTo(command));
    }

    /**
     * Verify that player can draw a new card without draw a new card actually
     */
    public boolean isValidDraw() {
        return this.drawPile.isDrawPossible();
    }

    /**
     * Verify that the player can move cards from DrawPile/LanePile/SuitPile
     * 
     * The command cannot be executed in some cases, for example:
     * - cannot move cards from SuitPile to other places
     * - cannot move multiple cards(more than one) from DrawPile
     * - etc.
     */
    public boolean isValidMoveFrom(Command command) {
        boolean isValid = false;
        if (command.isMoveFromSuit()) {
            isValid = this.isMoveFromSuitPilePossible(command);
        } else if (command.isMoveFromDraw()) {
            isValid = this.isMoveFromDrawPilePossible(command);
        } else if (command.isMoveFromLane()) {
            isValid = this.isMoveFromLanePilePossible(command);
        }

        return isValid;
    }

    /**
     * Verify that the player can move cards to DrawPile/LanePile/SuitPile
     * 
     * The command cannot be executed in some cases, for example:
     * - cannot move cards to DrawPile
     * - cannot move card to SuitPile if the card have a different suit
     * - etc.
     */
    public boolean isValidMoveTo(Command command) {
        boolean isValid = false;
        if (command.isMoveToSuit()) {
            isValid = this.isMoveToSuitPilePossible(command);
        } else if (command.isMoveToDraw()) {
            isValid = this.isMoveToDrawPilePossible(command);
        } else if (command.isMoveToLane()) {
            isValid = this.isMoveToLanePilePossible(command);
        }

        return isValid;
    }

    /*
     * Verify that whether player can move cards from DrawPile
     */
    private boolean isMoveFromDrawPilePossible(Command command) {
        return !this.drawPile.faceUpCards.isEmpty() && command.numCards == 1;
    }

    /*
     * Verify that whether player can move cards from LanePile
     */
    private boolean isMoveFromLanePilePossible(Command command) {
        int idxFrom = command.getIndex()[0];
        int num = 0;
        int idx = this.lanePiles[idxFrom].getSize() - 1;

        // count the number of cards that can be moved(means the card is face up)
        while (idx >= 0 && this.lanePiles[idxFrom].cards.get(idx).getIsFaceUp()) {
            idx -= 1;
            num += 1;
        }

        // The number of cards that can be moved is greater than the number player want
        // to move, return true
        return num >= command.numCards;
    }

    /**
     * Verify that whether player can move cards from SuitPile
     * 
     * @return false: we can never move Card from SuitPile
     */
    private boolean isMoveFromSuitPilePossible(Command command) {
        return false;
    }

    /**
     * Verify that whether player can move cards to DrawPile
     *
     * @return false: we can never move Card to DrawPile
     */
    private boolean isMoveToDrawPilePossible(Command command) {
        return false;
    }

    /**
     * Verify that whether player can move cards to LanePile
     */
    private boolean isMoveToLanePilePossible(Command command) {
        int idxTo = command.getIndex()[1];
        ArrayList<Card> cardsNeedToMove = this.getCardsNeedToMove(command, true);

        if (cardsNeedToMove.isEmpty()) {
            return false;
        }
        Card topCard = cardsNeedToMove.get(0);

        // System.out.println("card to move: " + topCard);
        // if (this.lanePiles[idxTo].getSize() > 0) {
        // System.out.println("card to comp: " + this.lanePiles[idxTo].cards.getLast());
        // }

        return this.lanePiles[idxTo].isNextCard(topCard);
    }

    /**
     * Verify that whether player can move cards to SuitPile
     */
    private boolean isMoveToSuitPilePossible(Command command) {
        int idxTo = command.getIndex()[1];
        ArrayList<Card> cardsNeedToMove = this.getCardsNeedToMove(command, true);

        if (cardsNeedToMove.isEmpty()) {
            return false;
        }
        Card topCard = cardsNeedToMove.get(0);

        // System.out.println("card to move: " + topCard);
        // if (this.suitPiles[idxTo].getSize() > 0) {
        // System.out.println("card to comp: " + this.suitPiles[idxTo].cards.getLast());
        // }

        return this.suitPiles[idxTo].isNextCard(topCard);
    }

    /**
     * 
     */
    public boolean tryMultiCardsPossible(Command command) {
        if (!command.isMoveFromLane() || !command.isMoveToLane()) {
            return false;
        }

        int idxFrom = command.getIndex()[0];
        int tryMaxtime = this.lanePiles[idxFrom].getSize();

        for (int i = tryMaxtime; i >= 0; i--) {
            command.numCards = i;
            if (this.isMoveFromLanePilePossible(command) && this.isMoveToLanePilePossible(command)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the cards need to move based on the isReadOnly
     * 
     * @param isReadOnly whether we get a copy of the cards need to move or we
     *                   actually move out the cards
     */
    private ArrayList<Card> getCardsNeedToMove(Command command, boolean isReadOnly) {
        ArrayList<Card> cards = new ArrayList<>();

        int idxFrom = command.getIndex()[0];
        int numCards = command.numCards;

        if (command.isMoveFromLane()) {
            cards = this.getCardsFromPile(this.lanePiles[idxFrom], numCards, isReadOnly);
        } else {
            cards.addFirst(isReadOnly ? this.drawPile.faceUpCards.get(0) : this.drawPile.faceUpCards.removeFirst());
        }

        return cards;
    }

    /**
     * Get a certain number of cards from a specific pile based on the isReadOnly
     * 
     * @param pile       LanePile/DrawPile
     * @param num        The number of cards that need to be moved
     * @param isReadOnly 'true' -> read only, 'false' -> read & remove
     */
    private ArrayList<Card> getCardsFromPile(Pile pile, int num, boolean isReadOnly) {
        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            if (isReadOnly) {
                cards.addFirst(pile.cards.get(pile.getSize() - 1 - i));
            } else {
                cards.addFirst(pile.removeCard());
            }
        }

        // for (Card card : cards) {
        // System.out.print(card);
        // }

        return cards;
    }

    /**
     * Update the board when the command is executed successfully
     */
    public void updateBoard(Command command) {
        this.score.update(command);
        this.moveCnt += 1;

        this.showNewCard();

        this.isNewBoard = true;
    }

    /**
     * Show card if the card is the top card of LanePile/SuitPile
     */
    private void showNewCard() {
        // show new card of LanePile
        for (LanePile lane : this.lanePiles) {
            lane.setTopCardFaceUp();
        }

        // show new card of SuitPile
        for (SuitPile suit : this.suitPiles) {
            suit.setTopCardFaceUp();
        }
    }
}
