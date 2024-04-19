package Connect4;


/**
* Tic-Tac-Toe state variables.
*/
public class State
{
    private int gameState = Constants.STANDBY;
    private int whoseMove = Constants.X;
    private String xName = "";
    private String oName = "";
    private int[][] board = new int[Constants.BOARD_ROW][Constants.BOARD_COLUMN];


   public boolean isWinner() {
        int total;
        for (int row=0; row<Constants.BOARD_ROW; row++) {
            total = getBoardCell(row, 0) + getBoardCell(row,1) + getBoardCell(row,2);
            if (total == -3 || total == 3) return true;
        }
        for (int col=0; col<Constants.BOARD_COLUMN; col++) {
            total = getBoardCell(0, col) + getBoardCell(1,col) + getBoardCell(2, col);
            if (total == -3 || total == 3) return true;
        }
        total = getBoardCell(0, 0) + getBoardCell(1,1) + getBoardCell(2, 2);
        if (total == -3 || total == 3) return true;
        total = getBoardCell(2, 0) + getBoardCell(1,1) + getBoardCell(0, 2);
        if (total == -3 || total == 3) return true;
        return false;
    }


   public boolean isTie() {
        for (int row=0; row<Constants.BOARD_ROW; row++) {
            for (int col=0; col<Constants.BOARD_COLUMN; col++) {
                if (getBoardCell(row,col) == Constants.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }


   public int getGameState() {
        return gameState;
    }


   public void setGameState(int gameState) {
        this.gameState = gameState;
    }


   public int getWhoseMove() {
        return whoseMove;
    }


   public void setWhoseMove(int whoseMove) {
        this.whoseMove = whoseMove;
    }


   public String getXName() {
        return xName;
    }


   public void setXName(String xName) {
        this.xName = xName;
    }


   public String getOName() {
        return oName;
    }


   public void setOName(String oName) {
        this.oName = oName;
    }


   public int getBoardCell(int row, int col) {
    // Check if the provided indices are within bounds
    if (row >= 0 && row < Constants.BOARD_ROW && col >= 0 && col < Constants.BOARD_COLUMN) {
        return this.board[row][col];
    } else {
        // If indices are out of bounds, return an invalid value
        return Constants.INVALID_VALUE;
    }
}
    public int [][] getBoard() {
        return this.board;
    }

   public void setBoardCell(int row, int col, int value) {
        this.board[row][col] = value;
    }
    
   public void reset() {
    // Clear the board and reset other game state variables
    for (int row = 0; row < Constants.BOARD_ROW; row++) {
        for (int col = 0; col < Constants.BOARD_COLUMN; col++) {
            board[row][col] = Constants.BLANK;
        }
    }
    whoseMove = Constants.X; // Reset the starting player to X
    gameState = Constants.GET_X_NAME; // Reset the game state
    }
}


