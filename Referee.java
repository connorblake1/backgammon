/**
 * The Referee class keeps track of the board, any dice, and all interactions
 * with the players. It keeps track of whose turn it is, displays the board,
 * rolls dice, and asks the users to make their moves. 
 */
import java.util.Scanner;
public class Referee {

	// TODO: decide which private member variables the Referee should have and declare them here.
	// suggestion: the dice are an array of integers, typically 1-6, where 0 means unrolled or 
	// used up already.
	int playerNum = 1;
	Board b1 = new Board();
	DiceCup dice = new DiceCup();
	Scanner scan = new Scanner(System.in);
	
	/**
	 * constructor - set up the board and players 
	 */
	// TODO: you write the Referee's constructor
	
	/**
	 * playGame - the main game loop. Roll the dice, ask the user for a
	 * move, determine whether it is legal, and then execute the move. 
	 * Repeat for any remaining dice.
	 */
	public void playGame()
	{
		// TODO: you write the Referee's playGame method.
		System.out.println("Playing game."); // placeholder code.

		while (!b1.gameIsOver()) {
			//print whose turn

			if (playerNum == 1) {
				System.out.println("Players A's Turn:");

			} else {
				System.out.println("Player B's Turn:");
			}
			//TURN PLAYS OUT HERE

			//turn swap

			boolean validTaken = false;
			//one move
			dice.roll();


			while (!areAllLegalMovesMade(playerNum)) {
				b1.printBoard();
				System.out.println(dice.toString());
				System.out.println("-------------------------------------");
				while (true) {
					System.out.println("Where would you like to move? Separate the board index and move length by a space. To access the bars, use 0 for X and 25 for O.");
					int moveIndex = scan.nextInt();
					int moveDistance = scan.nextInt();
					if (dice.isLegal(moveDistance)) {
						if (b1.playerHasPieceAtLocation(playerNum, moveIndex)) {
							if (b1.isLegal(moveIndex, moveDistance, playerNum)) {

								b1.makeMove(moveIndex, moveDistance, playerNum);
								dice.moveMade(moveDistance);
								break;
							}
						}
					}
					System.out.println("This move is invalid. Please pick a valid move.");
				}


			}

			if (playerNum == 1) {
				playerNum = 2;
			} else {
				playerNum = 1;}
		}
		}
		public boolean areAllLegalMovesMade(int player) {
			for (int i = 0; i < dice.getAvailableMoves().length; i++) {
				if (dice.getAvailableMoves()[i] != 0) {
					for (int j = 0; j < 26; j++) {
						if (b1.playerHasPieceAtLocation(player,j)) {
							if (b1.isLegal(j,dice.getAvailableMoves()[i],player)) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}
	}
