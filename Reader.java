import java.io.*;

public class Reader {
    Board board;
    private BufferedReader reader;

    public Reader(Board board) {
        this.board = board;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads and returns user input from console
     * @return null if conditions aren't met, else string
     */
    public String getInput() {
        String input = null;
        try {
            input = reader.readLine();
            if(!isInt(input) || !inBounds(input) || isColFull(input)) {
                input = null;
            }
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return input;
    }

    /**
     * Checks whether user input can be parsed to an integer
     * @param input input string
     * @return true if integer, else false
     */
    private boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e) {
            System.out.println("Input is invalid. Please try again.");
            return false;
        }
    }

    /**
     * Checks whether the integer specified by user input is within column bounds
     * @param input input string
     * @return true if in bounds, else false
    */
    private boolean inBounds(String input) {
        if(board.inBounds(Integer.parseInt(input))) {
            return true;
        }
        else {
            System.out.println("Input is out of bounds. Please try again.");
            return false;
        }
    }

    /**
     * Checks whether the column specified by user input is full
     * @param input input string
     * @return true if column full, else false
     */
    private boolean isColFull(String input) {
        if(board.isColFull(Integer.parseInt(input))) {
            System.out.println("Input column is full. Please try again.");
            return true;
        }
        else {
            return false;
        }
    }

}
