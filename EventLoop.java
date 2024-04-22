package Connect4;

public class EventLoop {

    // Instance variables for the UI and State classes
    State state = new State();
    UI ui = new UI();
    int row, col;

    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop();
        eventLoop.run();
    }

    public void run() {
    while (state.getGameState() != Constants.QUIT_PROGRAM) {
        int gameState = state.getGameState();
        if (gameState == Constants.STANDBY) {
            state.setGameState(Constants.GET_X_NAME);
        } else if (gameState == Constants.GET_X_NAME) {
            state.setXName(ui.promptForName("X"));
            state.setGameState(Constants.GET_O_NAME);
        } else if (gameState == Constants.GET_O_NAME) {
            state.setOName(ui.promptForName("O"));
            state.setGameState(Constants.GET_X_MOVE);
        } else if (gameState == Constants.GET_X_MOVE || gameState == Constants.GET_O_MOVE) {
            ui.printBoard(state);
            col = ui.getMoveCol(state.getWhoseMove(), state.getXName(), state.getOName());
            row = Constants.BOARD_ROW;
            for( int r = row; r >= 0 ; r-- ) {
                if ( state.getBoardCell(r-1, col-1) == Constants.BLANK ) {
                    row = r;
                    break;
                }
            }
     
            if (ui.isLegalMove(state, row, col)) {
                state.setGameState(Constants.MAKE_MOVE);
            } else {
                ui.printInvalidRowOrColumn();
            }
        } else if (gameState == Constants.MAKE_MOVE) {
            ui.printMove(state, row, col);
            state.setBoardCell(row-1, col-1, state.getWhoseMove());
            state.setGameState(Constants.CHECK_IF_WINNER);
        } else if (gameState == Constants.CHECK_IF_WINNER) {
            if (state.isWinner()) {
                ui.printMove(state, row, col);
                state.setBoardCell(row, col-1, state.getWhoseMove());
                if (state.getWhoseMove() == Constants.X) {
                    state.setGameState(Constants.X_WINS);
                } else {
                    state.setGameState(Constants.O_WINS);
                }
            } else {
                state.setGameState(Constants.CHECK_IF_TIE);
            }
        } else if (gameState == Constants.CHECK_IF_TIE) {
            if (state.isTie()) {
                ui.printTieGame();
                state.setGameState(Constants.GAME_OVER);
            } else {
                state.setWhoseMove(state.getWhoseMove() * -1);
                state.setGameState(state.getWhoseMove() == Constants.X ? Constants.GET_X_MOVE : Constants.GET_O_MOVE);
            }
        } else if (gameState == Constants.X_WINS || gameState == Constants.O_WINS) {
            ui.printWinner(state);
            state.setGameState(Constants.GAME_OVER);
        } else if (gameState == Constants.GAME_OVER) {
            if (ui.startNewGame()) {
                state.reset(); // Reset the state to start a new game
                state.setGameState(Constants.STANDBY);
            } else {
                state.setGameState(Constants.QUIT_PROGRAM);
            }
        }
    }
}
}
