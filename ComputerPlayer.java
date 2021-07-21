import java.lang.Math;

public class ComputerPlayer extends Player {

    public ComputerPlayer(char color, int playerN, Board board) {
        super(color, playerN, board);
    }

    /**
     * Generate random integer between min and max range
     * @param min
     * @param max
     * @return integer
     */
    private int randInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    /**
     * Place player counter in random column
     * If column is full, generate new random number
     * @param s abitrary string for overiding purposes
     */
    @Override
    public void makeMove(String s) {
        int max = board.getCols();
        int min = 1;
        int rand_int = randInt(min, max);
        while(board.isColFull(rand_int)) {
            rand_int = randInt(min, max);
        }
        board.placeCounter(color, rand_int);
    }

}
