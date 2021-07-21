public class Game {
	int N; //ConnectN
	Reader reader;
	Board board;
	Player player1;
	Player player2;
	Player player3;

	/** 
	 * Intialise N with command line argument
	 * start game
	*/
	public static void main(String args[]) {
		Game game = new Game();
		if(args.length != 1) {
			System.err.println("Either no argument or more than one argument passed.");
			System.exit(1);
		}
		else {
			try {
				int n = Integer.parseInt(args[0]);
				if(n <= 2 || n >= 7) {
					System.err.println("Illegal integer passed. Please ensure 2 < N < 7");
					System.exit(1);
				}
				game.N = n;
			}
			catch(NumberFormatException e) {
				System.err.println("Argument is invalid. Please ensure argument is an integer");
				System.exit(1);
			}
		}
		game.playGame();
	}
	
	/**
	 * Adds contents of game
	 * Executes game logic while there is no winner or a draw
	 */
	public void playGame() {
		//Print game information
		System.out.printf("Welcome to Connect %d\n", N);
		System.out.println("There are 3 players: red, yellow and blue");
		System.out.println("Player 1 is Red, Player 2 is Yellow, Player 3 is Blue");
		System.out.println("To play the game type in the number of the column you want to drop you counter in"); 
		System.out.printf("A player wins by connecting %d counters in a row - vertically, horizontally or diagonally\n", N);
		System.out.println("");
		//Game Logic
		board = new Board(N);
		reader = new Reader(board);
		player1 = new Player('r', 1, board);
		player2 = new ComputerPlayer('y', 2, board);
		player3 = new ComputerPlayer('b', 3, board);
		board.printBoard();
		boolean endGame = false;
		while(!endGame){
			//Player1
			String userInput = reader.getInput();
			while(userInput == null) {
				userInput = reader.getInput();
			}
			player1.makeMove(userInput);
			board.printBoard();
			if(player1.hasWon() || board.checkDraw()){
				endGame = true;
				continue;
			}
			//Player2(Computer)
			player2.makeMove(null);
			board.printBoard();
			if(player2.hasWon() || board.checkDraw()){
				endGame = true;
				continue;
			}
			//Player3(Computer)
			player3.makeMove(null);
			board.printBoard();
			if(player3.hasWon() || board.checkDraw()){
				endGame = true;
			}
		}
	}

}
