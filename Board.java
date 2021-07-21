public class Board {
    private char[][] board;
    private int rows;
    private int cols;
    private int n; //ConnectN

    public Board(int n) {
        rows = 6;
        cols = 7;
        //Board is represented by a char array
        //of 6 rows by 7 columns
        board = new char[rows][cols];
        this.n = n;
        fillBoard();
    }

    /**
     * Fills array with 0s to denote empty spaces
     */
    private void fillBoard() {
        for(int i=0; i < rows; i++) {
            for(int j=0; j < cols; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Fills array with counter in the last available row in specified column
     * @param player player colour
     * @param position column index + 1
     */
    public void placeCounter(char player, int position) {
		boolean placed = false;
        for(int i=rows-1; i>=0; i--){
            if(!placed){
                if(board[i][position-1] == 0){
                    board[i][position-1] = player;
                    placed = true;
                }
                else {
                    continue;
                }
            }
        }
    }

    /**
     * Checks for a horizontal connection for specified player
     * @param player player colour
     * @return true for connection, else false
     */
    public boolean checkHorizontal(char player) {
        // - - - -
        // * * * *
        // - - - -
        // - - - -
        int count;
        for(int i=0; i<rows; i++){
            count = 0;
            for(int j=0; j<cols; j++){
                if(board[i][j] == player){
                    count++;
                    if(count >= n){
                        return true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }
        return false;
    }   

    /**
     * Checks for a vertical connection for a specified player
     * @param player player colour
     * @return true for connection, else false
     */
    public boolean checkVertical(char player) {
        // * - - -
        // * - - -
        // * - - -
        // * - - -
        int count;
        for(int i=0; i<cols; i++){
            count = 0;
            for(int j=0; j<rows; j++){
                if(board[j][i] == player){
                    count++;
                    if(count >= n){
                        return true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }
        return false;
    }

    /**
     * Checks for both ascending and descending diagonal connections
     * for a specified player
     * @param player player colour
     * @return true for connection, false otherwise
     */
    public boolean checkDiagonal(char player) {
        boolean hasWon = false;
        int count;

        // Case 1
        // * - - -
        // * * - -
        // * * * -
        // * * * *
        for(int i=0; i<rows; i++) {
            count = 0;
            for(int j=i, k=0; j < rows && k < cols; j++, k++) {
                if(board[j][k] == player) {
                    count++;
                    if(count >= n) {
                        hasWon = true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        // Case 2
        // - * * *
        // - - * *
        // - - - *
        // - - - -
        for(int i=1; i<cols; i++) {
            count = 0;
            for(int j=0, k=i; j < rows && k < cols; j++, k++) {
                if(board[j][k] == player) {
                    count++;
                    if(count >= n) {
                        hasWon = true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        // Case 3
        // - - - *
        // - - * *
        // - * * *
        // * * * *
        for(int i=0; i < cols; i++) {
            count = 0;
            for(int j=rows-1, k=i; j >= 0 && k < cols; j--, k++) {
                if(board[j][k] == player) {
                    count++;
                    if(count >= n) {
                        hasWon = true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        // Case 4
        // * * * -
        // * * - -
        // * - - -
        // - - - -
        for(int i=rows-2; i >= 0; i--) {
            count = 0;
            for(int j=i, k=0; j >= 0 && k < cols; j--, k++) {
                if(board[j][k] == player) {
                    count++;
                    if(count >= n) {
                        hasWon = true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        return hasWon;
    }

    /**
     * Checks for a draw i.e. all spaces on board are filled with counters
     * @return true for draw, false otherwise
     */
    public boolean checkDraw() {
        for(int i=0; i < cols; i++) {
            if(!isColFull(i+1)) {
                return false;
            }
        }
        System.out.println("This game is a draw!");
        return true;
    }

    /**
     * prints array to display current status of board
     */
    public void printBoard(){
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++){
				if(board[i][j] != 0) {
                    System.out.print(String.format("| %c ", board[i][j]));
                }
				else{
					System.out.print("|   ");
				}
			}
			System.out.println("|");
		}
		System.out.println("  1   2   3   4   5   6   7");
	}

    /**
     * Accessor - return number of columns
     * @return
     */
    public int getCols() {
        return cols;
    }

    /**
     * Checks whether a users move is in bounds of columns
     * i.e. 0 > postion <= 7
     * @param position column number
     * @return true for in bounds, false otherwise
     */
    public boolean inBounds(int position) {
        if(position <= cols && position > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks whether a column is full with player counters
     * By seeing whether first row in column has a counter
     * @param position column index + 1
     * @return
     */
    public boolean isColFull(int position) {
        if(board[0][position-1] != 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
