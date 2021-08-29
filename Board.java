/**
 * The board class keeps track of 24 spaces (the skinny triangles on 
 * a regular board) and the bar for each player. It keeps track of 
 * these values and determines whether a given move is legal or not.
 */
public class Board {
	// TODO: decide which private member variables the Board class needs, and declare them here.
	// suggestion - for the 24 spaces, I suggest an array that holds the number
	// of pieces on each square. One player will have positive numbers, and
	// the other will have negative numbers. So if space (5) contains 3, that
	// means that there are 3 white pieces trying to move to higher numbered
	// spaces, and if space (7) contains -2, that means that there are 2 black
	// pieces trying to move to lower numbered spaces. 

	// Locations 0 and 25 are the bars (penalty zones) for the two teams - if 
	// the "negative" team is trying to move its pieces to smaller numbers,
	// then moving them to 0 or less actually removes them from the board - they
	// don't go to position 0. On the other hand if a "positive-moving" piece is
	// captured, it goes to position 0, the farthest point from it's goal of 25 or
	// more.

	private int[] boardint = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
	private int[] playerlocations = {0, 2, 0, 0, 0, 0, -5, 0, -3, 0, 0, 0, 5, -5, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0,-2, 0};



	/**
	 * constructor - set up the starting locations of the pieces.
	 */
	public Board()
	{

		//playerlocations = new int[]{0, -3, -3, -3, -3, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 3, 10, 0};
		//--------------------
		// TODO: insert your code here.

		//--------------------
	}

	/**
	 * toString - create a string representing the state of the board.
	 * @return a string containing the board state.
	 * for example, it might look like:
	 * 0 (BAR) O
	 * 1
	 * 2 OO
	 * 3 OOO
	 * 4 XX
	 * 5
	 * 6 XXXXX
	 * ....
	 * 23 O
	 * 24 XX
	 * 25 (BAR) XX
	 */
	public void printBoard() {
		System.out.println("12-11-10-09-08-07-BAR-06-05-04-03-02-01");
		int boardMax = 0;
		for (int i = 0; i < 26; i++) {
			if (Math.abs(playerlocations[i]) > boardMax) {
				boardMax = Math.abs(playerlocations[i]);
			}
		}
		//upperboard
		for (int j = 0; j < boardMax; j++) {
			for (int i = 12; i > 0; i--) {
				if (i == 6) {
					if (playerlocations[0] > j) {
						System.out.print("|X| ");}
					else {System.out.print("| | ");}
				}
				char printer;
				if (playerlocations[i] < -j) {
					printer = 'O';
				} else if (playerlocations[i] > j) {
					printer = 'X';
				} else {
					printer = ' ';
				}
				System.out.print(" " + printer + " ");
				}
			System.out.println();
			}
		System.out.println("---------------------------------------");
		for (int j = boardMax; j > 0; j--) {
			for (int i = 13; i < 25; i++) {
				if (i == 19) {
					if (playerlocations[25] < 1-j) {
						System.out.print("|O| ");}
					else {System.out.print("| | ");}
				}
				char printer;
				if (playerlocations[i] < -j+1) {
					printer = 'O';
				} else if (playerlocations[i] > j-1) {
					printer = 'X';
				} else {
					printer = ' ';
				}
				System.out.print(" " + printer + " ");
			}
			System.out.println();
		}
		System.out.println("13-14-15-16-17-18-BAR-19-20-21-22-23-24");


		}

	/**
	 * playerHasPieceAtLocation - determines whether the player has at
	 * least one chip at the given space.
	 * @param whichPlayer - this can be 1 or 2.
	 * @param location - the number of the space in question.
	 * @return whether (true/false) the player has a chip of his/her own
	 * color at this space.
	 */
	public boolean playerHasPieceAtLocation(int whichPlayer, int location)
	{
		boolean hasPiece = false;
		if (location >= 0 && location <= 25) {
		if (whichPlayer == 1)
		{
			if (playerlocations[location] > 0)
			{
				hasPiece = true;
			}
		}
		if (whichPlayer == 2)
		{
			if (playerlocations[location] < 0)
			{
				hasPiece = true;
			}
		}}
		return hasPiece;
	}

	/**
	 * isLegal - determines whether a chip at the given space can move
	 * the desired number of spaces
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be
	 * a move up or down, depending on what chip is in the starting space)
	 * @return whether (true/false) the player is allowed to make such a move.
	 * precondition: yes, there's at least one chip in the space.
	 * postcondition: the board is unchanged.
	 */
	public boolean isLegal(int startingSpace, int numSpaces, int player)
	{
		//--------------------
		// TODO: insert your code here.

		if (player == 1) {
			if (playerlocations[0] == 0) { //verifies that there are none on the bar
				if (startingSpace + numSpaces < 25) { //verifies that this a standard game move that does not require the player to be currently bearing off
					if (playerlocations[startingSpace + numSpaces] >= -1) { //verifies the selected piece can legally move to the selected location
						return true;}}
				else if (startingSpace + numSpaces == 25) { //since we know that they are trying to bear off, we must check that they are doing so
					if (bearingOff(1)) {
						return true;}}}
			else { //since we know that there are some on the bar, verifies that they are being moved off the bar currently
				if (startingSpace == 0) {
					if (playerlocations[startingSpace + numSpaces] >= -1) {
						return true;}}}}
		if (player == 2) {
			if (playerlocations[25] == 0) {
				if (startingSpace - numSpaces > 0) {
					if (playerlocations[startingSpace - numSpaces] <= 1) {
						return true;}}
				else if (startingSpace - numSpaces == 0) {
					if (bearingOff(2)) {
						return true;}}}
			else {
				if (startingSpace == 25) {
					if (playerlocations[startingSpace - numSpaces] <= 1) {
						return true;}}}}
		//--------------------
		return false;
	}



	/**
	 * makeMove - moves one chip from the given space by the specified amount;
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be
	 * a move up or down, depending on what chip is in the starting space)
	 * precondition: there is a chip at the starting space
	 * postcondition: the chip may be moved to a different space, or off the board.
	 * If the chip lands on a single enemy piece, that piece is sent to its bar.
	 */
	public void makeMove(int startingSpace, int numSpacesToMove, int player) {
		//--------------------
		// TODO: insert your code here.


		if (player == 1) {
			playerlocations[startingSpace] -= 1;
			if (startingSpace + numSpacesToMove < 25) { //verifies that the move is a standard move, not a bearing off move
				if (playerlocations[startingSpace + numSpacesToMove] == -1) { //bumping piece
					playerlocations[startingSpace + numSpacesToMove] += 2; // add a checker to bar
					playerlocations[25] -= 1;
				} else { //simply moving the piece
					playerlocations[startingSpace + numSpacesToMove] += 1;
				}
			}
			//the lack of else condition means that since makeMove was called yet the first if statement in player==1 was not true, we know that the player is currently bearing off and no further action is required

		} else {
			playerlocations[startingSpace] += 1;
			if (startingSpace - numSpacesToMove > 0) {
				if (playerlocations[startingSpace - numSpacesToMove] == 1) {
					playerlocations[startingSpace - numSpacesToMove] -= 2; // add a checker to bar
					playerlocations[0] += 1;
				} else {
					playerlocations[startingSpace - numSpacesToMove] -= 1;
				}
			}

		}

	}
	/**
	 * gameIsOver - determines whether either player has removed all
	 * his/her pieces from the board.
	 * @return - whether (true/false) the game is over.
	 */

	public boolean bearingOff(int player)
	{
		if (player == 1) {
			for (int i =0; i< 19; i++) {
				if (playerlocations[i] > 0) {
					return false;
				}}}
		else {
			for (int i =7; i < 26; i++) {
				if (playerlocations[i] < 0) {
					return false;
				}}}
		return true;
	}


	public void removechecker(int dicenum)
	{
		if (bearingOff(1))
		{
			if (dicenum > 0 && dicenum < 7)
				playerlocations[dicenum + 1] -= 1;

		}

		else if (bearingOff(2))
		{
			if (dicenum > 18 && dicenum < 25)
				playerlocations[dicenum + 1] += 1;

		}
	}

	public boolean gameIsOver()
	{
		int p1sum = 0;
		int p2sum = 0;
		boolean gameOver = false;
		//--------------------
		// TODO: Insert your code here
		for (int i: playerlocations)
		{
			if (i > 0)
			{
				p1sum += i;
			}

			else if (i < 0)
			{
				p2sum += i;
			}
		}

		if (p1sum == 0)
		{
			gameOver = true;
		}
		if (p2sum == 0)
		{
			gameOver = true;
		}
		//--------------------
		return gameOver;
	}
}
