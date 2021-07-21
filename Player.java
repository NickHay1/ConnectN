public class Player {
    char color;
    Board board;
    int playerN;
    int n;

    public Player(char color, int playerN, Board board) {
        this.color = color;
        this.playerN = playerN;
        this.board = board;
    }

    /**
     * Parse user input to integer and place player counter in specified column
     * @param userInput
     */
    public void makeMove(String userInput) {
        board.placeCounter(color, Integer.parseInt(userInput));
    }

    /**
     * Check whether a given player has won the game
     * Either vertically, horizontally or diagonally connecting tokens
     * @return true for win, false otherwise
     */
    public boolean hasWon() {
        if(board.checkVertical(color) || board.checkHorizontal(color) || board.checkDiagonal(color)) {
            System.out.printf("\nPlayer %d has won the game!", playerN);
            return true;
        }
        else {
            return false;
        }
    }

}
