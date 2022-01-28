package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game,
 * specifically.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class ClearCellGameModel extends GameModel {

	private Random randomNum;
	private int score;

	/**
	 * Defines a board with empty cells.  It relies on the
	 * super class constructor to define the board.
	 * 
	 * @param rows number of rows in board
	 * @param cols number of columns in board
	 * @param random random number generator to be used during game when
	 * rows are randomly created
	 */
	public ClearCellGameModel(int rows, int cols, Random random) {
		super(rows, cols);
		randomNum = random;
		score = 0;
	}

	/**
	 * The game is over when the last row (the one with index equal
	 * to board.length-1) contains at least one cell that is not empty.
	 */

	public boolean isGameOver() {

		if(!empty(board.length-1)) {
			return true;
		}
		return false;
	} 

	/**
	 * Returns the player's score.  The player should be awarded one point
	 * for each cell that is cleared.
	 * 
	 * @return player's score
	 */
	public int getScore() {
		return score;
	}


	/**
	 * This method must do nothing in the case where the game is over.
	 * 
	 * As long as the game is not over yet, this method will do 
	 * the following:
	 * 
	 * 1. Shift the existing rows down by one position.
	 * 2. Insert a row of random BoardCell objects at the top
	 * of the board. The row will be filled from left to right with cells 
	 * obtained by calling BoardCell.getNonEmptyRandomBoardCell().  (The Random
	 * number generator passed to the constructor of this class should be
	 * passed as the argument to this method call.)
	 */

	public void nextAnimationStep() {
		if(!(isGameOver())) {
			for(int row = getRows() - 2; row >= 0; row--) {
				for(int col = 0; col < getCols(); col++) {
					if(getBoardCell(row, col) != BoardCell.EMPTY) {
						setBoardCell(row + 1, col, this.getBoardCell(row, col));
					}
					if(row == 0) {
						setBoardCell(row, col, BoardCell.getNonEmptyRandomBoardCell(randomNum));
					}
				}
			}
		}
	} 

	/**
	 * This method is called when the user clicks a cell on the board.
	 * If the selected cell is not empty, it will be set to BoardCell.EMPTY, 
	 * along with any adjacent cells that are the same color as this one.  
	 * (This includes the cells above, below, to the left, to the right, and 
	 * all in all four diagonal directions.)
	 * 
	 * If any rows on the board become empty as a result of the removal of 
	 * cells then those rows will "collapse", meaning that all non-empty 
	 * rows beneath the collapsing row will shift upward. 
	 * 
	 * @throws IllegalArgumentException with message "Invalid row index" for 
	 * invalid row or "Invalid column index" for invalid column.  We check 
	 * for row validity first.
	 */


	public void processCell(int rowIndex, int colIndex) {
		//the cell above the current cell
		int above = rowIndex-1;
		//the cell below the current cell
		int below = rowIndex+1;
		//the cell right of the current cell
		int right = colIndex+1;
		//the cell left of the current cell
		int left = colIndex-1;


		if(rowIndex > getRows() || rowIndex < 0){ 
			throw new IllegalArgumentException("Invalid row index");
		}
		if(colIndex > getCols() || colIndex < 0){
			throw new IllegalArgumentException("Invalid column index");
		}

		if(board[rowIndex][colIndex] != BoardCell.EMPTY){
			/* sets the cell ABOVE the parameter to BoardCell.EMPTY
			  if their colors match */
			if(above >= 0){
				if(board[above][colIndex] == board[rowIndex][colIndex]){ 
					board[above][colIndex] = BoardCell.EMPTY;
					score++;
				}
			}

			if (right < getCols()) {
				/*sets the cell to the RIGHT of the parameter to BoardCell.EMPTY
				  if their colors match */
				if(board[rowIndex][right] == board[rowIndex][colIndex]){ 
					board[rowIndex][right] = BoardCell.EMPTY;
					score++;
				}

				if(above >= 0) {
					/*sets the cell to the UPPER RIGHT diagonal of the parameter to BoardCell.EMPTY
					  if their colors match */
					if(board[rowIndex][colIndex] == board[above][right]) {
						board[above][right] = BoardCell.EMPTY;
						score++;
					}
				}

				if(below <= getRows() - 1) {
					/*sets the cell to the LOWER RIGHT diagonal of the parameter to BoardCell.EMPTY
					  if their colors match */
					if(board[below][right] == board[rowIndex][colIndex]) {
						board[below][right] = BoardCell.EMPTY;
						score++;
					}

				}
			}

			if(below < getRows() - 1) {
				/*sets the cell BELOW the parameter to BoardCell.EMPTY
				  if their colors match */
				if(board[below][colIndex] == board[rowIndex][colIndex]) {
					board[below][colIndex] = BoardCell.EMPTY;
					score++;
				}
			}

			if(left >= 0) {
				/*sets the cell to the LEFT of the parameter to BoardCell.EMPTY
				  if their colors match */
				if(board[rowIndex][left] == board[rowIndex][colIndex]) {
					board[rowIndex][left] = BoardCell.EMPTY;
					score++;
				}

				if(above >= 0) {
					/*sets the cell to the UPPER LEFT diagonal of the parameter to BoardCell.EMPTY
				  if their colors match */
					if(board[above][left] == board[rowIndex][colIndex]) {
						board[above][left] = BoardCell.EMPTY;
						score++;
					}
				}

				if(below <= getRows() - 1) {
					/*sets the cell to the LOWER LEFT diagonal of the parameter to BoardCell.EMPTY
				  if their colors match */
					if(board[below][left] == board[rowIndex][colIndex]) {
						board[below][left] = BoardCell.EMPTY;
						score++;
					}
				}
			}
			
			if(board[rowIndex][colIndex] == board[rowIndex][colIndex]) {
				board[rowIndex][colIndex] = BoardCell.EMPTY;
				score++;
			}
			
			/* Iterates through all rows of the game checking if any are empty. If a row is empty then
			 * this loop shifts all cells down one row.
			 */
			
			for(int row = 0; row < board.length; row++){
				if(empty(row)){
					for(int x = row; x < board.length - 1; x++){
							board[x] = board[x +1];
							board[x+1] = newRow();
					}
				}
			}
		}
	}
	
	//Creates an empty row to be used in the processCell method
	private BoardCell[] newRow() {
		BoardCell[] newRow = new BoardCell[getCols()];
		for(int col = 0; col < getCols(); col++) {
			newRow[col] = BoardCell.EMPTY;
		}
		
		return newRow;
	}
	

	/**
	 * User created method, checks if the row number passed as a parameter is empty. Returns true if 
	 * all columns of row are empty. 
	 * 
	 * @param row the row being checked for emptiness.
	 */
	private boolean empty(int row) {
		int count = 0; //tracks the number of empty columns

		for(int col = 0; col < getCols(); col++) {
			if(getBoardCell(row, col) == BoardCell.EMPTY) {
				count++;
			}
		}

		/* if the number of empty columns equals the total columns
		 then the row is empty */
		if(count == getCols()) {
			return true;
		}
		return false;
	}


}


