/*this class is Evaluate.
 * This class implements all the auxiliary methods needed by the algorithm that plays the game
 * AUTHOR : SARA MEHRAVAR
 * STUDENT ID: 251185394
 */
public class Evaluate{
	//MAKING SOME INSANT VARIABLES
	private char[][]gameBoard;
	private int tilestowin;
	//CONSTRUCTOR METHOD
	/*The first parameter specifies the size of the board, the second parameter is the number of adjacent
	*tiles needed to win the game, and the last parameter specifies the playing quality of the program
	*/
	public Evaluate (int size, int tilesToWin, int maxLevels)
	{
		gameBoard = new char[size][size];
		for (int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				gameBoard[i][j] = 'e';
			}
		}
		tilestowin = tilesToWin;
	}
	 //returns an empty Dictionary of the size that we have selected
	public Dictionary createDictionary()
	{
		Dictionary dictionary = new Dictionary (6269);
		return dictionary;
	}
	
	public Record repeatedState(Dictionary dict)
	{
		String strboard = getGameBoardString();
		if(dict.get(strboard) != null) {
			return dict.get(strboard);
		}
		else {
			return null;
		}
			
	}
	/*This method first represents the content of gameBoard as a string as described in Section 2,
	*then it creates an object of the class Record storing this string, score, and level; 
	*finally, this Record object is stored in dict
	*/
	public void insertState(Dictionary dict, int score, int level)
	{
		String strboard = getGameBoardString();
		try
		{
			dict.put(new Record(strboard, score, level));
		}
		catch(DuplicatedKeyException e)
		{
			System.err.println(e.getMessage());
		}
		
	}
	// stores symbol in gameBoard[row][col].

	public void storePlay(int row, int col, char symbol){
		gameBoard[row][col] = symbol;
	}
	
	// returns true if gameBoard[row][col] is 'e'
	public boolean squareIsEmpty(int row, int col){
		try{
		return gameBoard[row][col] == 'e';
		}catch (Exception e){
			//false if anything else rather than 'e'
			return false;
		}
	}
	// returns true is gameBoard[row][col] is 'c';
	public boolean tileOfComputer(int row, int col){
		try{
			
			return gameBoard[row][col] == 'c';
		}catch (Exception e){
			//false if anything rather than 'c'
			return false;
		}
	}
	//returns true if gameBoard[row][col] is 'h'
	public boolean tileOfHuman(int row, int col){
		try{
			return gameBoard[row][col] == 'h';
		}catch (Exception e){
			//false if anything rather than 'h'
			return false;
		}
	}
	//Returns true if there are the required number of adjacent tiles of type symbol in the same row, column, or diagonal of gameBoard 
	public boolean wins (char symbol){
		int c = 0; // Records the number in a row
		// Checks each Row for a win
		for (int i = 0; i < gameBoard.length; i++){
			c = 0; // Resets the counter for the row
			for (int j = 0; j < gameBoard.length; j++){
					if (gameBoard[i][j] == symbol) {
						c = c + 1;// Adds one to count
					}else {
						c = 0;// Resets count
					}if (c == getTile())  // Checks if count is enough to win
						return true;
			}
		}
		// Checks each Column for a win
		for (int k = 0; k < gameBoard.length; k++)
		{
			c = 0;// Resets the counter for the row
			for (int l = 0; l < gameBoard.length; l++)
			{
				if (gameBoard[l][k] == symbol)
					c = c + 1; // Adds one to count
				else
					c = 0; // Resets count
				if (c == getTile())
				// Checks if count is enough to win
					return true;
			}
		}

		// Checks Left Diagonal (top half - Along rows - Descending)
		for (int m = 0; m < gameBoard.length; m++)
		{
			c = 0;
			if((gameBoard.length - m) >= tilestowin)
			{
				for (int n = 0; n < gameBoard.length; n++)
				{
					if ( (m + n) < gameBoard.length)
					{ // Keeps index in array bounds
						if (gameBoard[n][n + m] == symbol)
							c = c + 1; // Adds one to count
						else
							c = 0; // Resets count
						if (c == getTile())  // Checks if count is enough to win
							return true;
		
		} else 
		c = 0;
		
		}
		}
		}

		// Checks Left Diagonal (bottom half - Along Columns - Descending)
		for (int m = 0; m < gameBoard.length; m++) {
		c = 0;
		if((gameBoard.length - m) >= tilestowin){
			for (int n = 0; n < gameBoard.length; n++) {

		if ( (m + n) < gameBoard.length) { // Keeps index in array bounds
		if (gameBoard[n + m][n] == symbol) {
		c = c + 1; // Adds one to count

		} else {
		c = 0; // Resets count
		}

		if (c == getTile()) { // Checks if count is enough to win
		return true;
		}
		} else {
		c = 0;
		}
		}
		}
		}
		// Checks Left Diagonal (top half - Along Rows - Ascending)
		for (int q = 0; q < gameBoard.length; q++) {
		c = 0;
		if((gameBoard.length - q) >= getTile()){
		for (int r = gameBoard.length - 1; r >= 0; r--) {

		if ( (r - q) >= 0) { // Keeps index in array bounds
		if (gameBoard[gameBoard.length-r-1][r - q] == symbol) {
		c = c + 1; // Add one onto count

		} else {
		c = 0; // Resets count
		}

		if (c == getTile()) { // Checks if count is enough to win
		return true;
		}

		}

		}
		}
		}

		// Checks Left Diagonal (top half - Along Columns - Ascending)
		for (int q = 0; q < gameBoard.length; q++) {
		c = 0;
		if((gameBoard.length - q) >= getTile()){
		for (int r = gameBoard.length - 1; r >= 0; r--){

		if ( (r - q) >= 0 && gameBoard.length-r < gameBoard.length) { // Keeps index in array bounds
		if (gameBoard[r-q][gameBoard.length-r] == symbol) {
		c = c + 1; // Adds one onto count

		} else {
		c = 0; // Resets count
		}

		if (c == getTile()) { // Checks if count is enough to win
		return true;
		}

		}

		}
		}
		}

		return false;
		}
	//Returns true if there are no empty positions left in gameBoard
	//otherwise returns false
	public boolean isDraw()
	{
	   for (int i=0; i<gameBoard.length; i++)
	   {
	       for (int j=0; j<gameBoard.length; j++)
	       {
	           if (gameBoard[i][j] == 'e')
	                   return false;
	       }
	   }
	   return true;
	}

	//
	public int evalBoard()
	{
		//3 if the computer has won 
		if (wins('c'))
		return 3;
		//if human wins(us), returns zero
		else if (wins('h'))
		return 0;
		//if the game is a draw
		else if (isDraw())
		return 2;
		else 
		//if game is still undecided
		return 1;
	}
	
	//the method that i have added as a private method cause it was not required in the program
	private String getGameBoardString()
	{
		String board = "";

		// Iterating through board double array
		for (int i = 0; i < gameBoard.length; i++){
			for (int j = 0; j < gameBoard.length; j++){
				// Add to board string
				board = board + gameBoard[i][j];
			}
		}

		return board;
	}
	
	private int getTile()
	{
		return tilestowin;
	}
	
}
