/**
 * <p> Project Name: Patience Game </p>
 * <P> Course Name: COMP41670 Software Engineering </P>
 * <P> Description: Patience (Europe), card solitaire or solitaire (US/Canada), is a genre of card games whose common feature is that the aim is to arrange the cards in some systematic order or, in a few cases, to pair them off in order to discard them. </P>
 * <P> Shaofeng Wang </P>
 * <P> 24202293 </P>
 */
public class Patience {

    public static void main(String[] args) {
        Board board = new Board();

        board.displayFirstSatrt();

        do {
            board.displayBoard();
            Command command = new Command();
            command.getCommand();
            if (command.isQuit()) {
                return;
            }

            if (board.isValidMove(command)) {
                board.executeCommand(command);
            } else {
                board.displayCommandNotExecuted();
            }
        } while (!board.isGameOver());
    }
}
