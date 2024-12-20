import java.util.Scanner;

/**
 * Model for command, we can do follows:
 * 
 * - get command from player
 * - verify that the command is valid
 * - convert command content to index
 */
public class Command {

    Scanner in; // get the command entered by player
    public boolean isTryMultiCards;

    // player can do follows: draw, move, quit
    public enum CommandType {
        DRAW, MOVE, QUIT,
    }

    public CommandType commandType; // the type of the command: draw/move/quit
    public String command; // the content of the command
    public String moveFrom, moveTo; // represents the begin and end point of the card's movement
    public int numCards; // the number of the cards to be moved

    public Command() {
        in = new Scanner(System.in);
        this.isTryMultiCards = false;
    }

    /**
     * Gets the command entered by the player from the terminal
     */
    public void getCommand() {
        String input;
        do {
            System.out.println("Enter command: ");
            input = in.nextLine();

            // System.out.println("Player's input: " + input);

            this.setCommand(input);
            if (this.isValid()) {
                this.getCommandType();
                return;
            } else {
                System.out.println("The command is invalid. Try again.");
            }
        } while (!this.isValid());
        in.close();
    }

    /**
     * Verify that whether the command entered by player is valid
     */
    public boolean isValid() {
        return this.command.equals("Q") || this.command.equals("D")
                || this.command.matches("[P1-7DHCS][1-7DHCS][0-9]*");
    }

    /**
     * Set the command content, and translate to UpperCase
     */
    public void setCommand(String newCommand) {
        this.command = newCommand.trim().toUpperCase();
    }

    /**
     * Return the command type
     */
    public CommandType getCommandType() {
        if (this.command.equals("Q")) {
            this.commandType = CommandType.QUIT;
        } else if (this.command.equals("D")) {
            this.commandType = CommandType.DRAW;
        } else {
            this.commandType = CommandType.MOVE;

            this.moveFrom = this.command.substring(0, 1);
            this.moveTo = this.command.substring(1, 2);

            if (this.command.length() != 2) {
                this.numCards = Integer.parseInt(this.command.substring(2));
                this.isTryMultiCards = false;
            } else {
                this.numCards = 1;
                this.isTryMultiCards = true;
            }
        }

        return this.commandType;
    }

    /**
     * Return whether the command type is 'Quit'
     */
    public boolean isQuit() {
        return this.commandType == CommandType.QUIT;
    }

    /**
     * Return whether the command type is 'Draw'
     */
    public boolean isDraw() {
        return this.commandType == CommandType.DRAW;
    }

    /**
     * Return whether the command type is 'Move'
     */
    public boolean isMove() {
        return this.commandType == CommandType.MOVE;
    }

    /**
     * Return Whether or not to remove cards from drawPile
     */
    public boolean isMoveFromDraw() {
        return this.moveFrom.matches("[P]");
    }

    /**
     * Return Whether or not to remove cards from lanePile
     */
    public boolean isMoveFromLane() {
        return this.moveFrom.matches("[1-7]");
    }

    /**
     * Return Whether or not to remove cards from suitPile
     */
    public boolean isMoveFromSuit() {
        return this.moveFrom.matches("[DHCS]");
    }

    /**
     * Return Whether or not to remove cards to drawPile
     */
    public boolean isMoveToDraw() {
        return this.moveTo.matches("[P]");
    }

    /**
     * Return Whether or not to remove cards to lanePile
     */
    public boolean isMoveToLane() {
        return this.moveTo.matches("[1-7]");
    }

    /**
     * Return Whether or not to remove cards to suitPile
     */
    public boolean isMoveToSuit() {
        return this.moveTo.matches("[DHCS]");
    }

    /**
     * Returns an array, which represents the begin and end point index of the
     * card's movement
     * 
     * @return [begin-point index, end-point index]
     */
    public int[] getIndex() {

        int[] index = new int[2];
        if (this.isMoveFromLane()) {
            index[0] = this.getLaneIndex(this.moveFrom);
        } else if (this.isMoveFromSuit()) {
            index[0] = this.getSuitIndex(this.moveFrom);
        }

        if (this.isMoveToLane()) {
            index[1] = this.getLaneIndex(this.moveTo);
        } else if (this.isMoveToSuit()) {
            index[1] = this.getSuitIndex(this.moveTo);
        }

        // System.out.println("moveFrom: " + this.moveFrom + " index[0]: " + index[0]);
        // System.out.println("moveTo: " + this.moveTo + " index[1]: " + index[1]);

        return index;
    }

    /**
     * Convert suit value to index
     */
    private int getSuitIndex(String s) {
        return switch (s) {
            case "H" -> 0;
            case "D" -> 1;
            case "C" -> 2;
            case "S" -> 3;
            default -> 0;
        };
    }

    /**
     * Convert lane name to index
     */
    private int getLaneIndex(String s) {
        return Integer.parseInt(s) - 1;
    }
}
