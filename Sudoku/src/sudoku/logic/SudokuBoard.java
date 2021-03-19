package sudoku.logic;

import sudoku.AI.SudokuAI;

public class SudokuBoard extends SudokuAI implements Cloneable {
	
	private int[][] cells = new int[9][9];
	
	/**
	 * Constructor
	 */
	public SudokuBoard() {
		// Initializing the cells
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells.length; y++) {
				this.setCell(x,  y, 0);
			}
		}		
	}
	
	/**
	 * Returns the current board.
	 * @return Returns the board.
	 */
	public int[][] getBoard() {
		return this.cells;
	}
	
	/**
	 * Sets the value of a cell at a given coordinate.
	 * @param x Column number
	 * @param y Row number
	 * @param n The value of the cell you want to insert
	 */
	public void setCell(int x, int y, int n) {
		this.cells[x][y] = n;
	}
	
	/**
	 * Returns the value of a cell at a given coordinate.
	 * @param x Column number
	 * @param y Row number
	 * @return The value of the cell(x, y)
	 */
	public int getCell(int x, int y) {
		return this.cells[x][y];
	}
	
	/**
	 * Returns the given block number.
	 * @param n Block number
	 * @return Returns the block number. If the block is not included betweeen 0 and 8, returns null.
	 */
	public boolean isInRow(int row, int number) {
		for(int i = 0; i < 9; i++)
			if (cells[row][i] == number)
				return true;

		return false;
	}
	
	
	/**
	 * Checks if the number is in the given row, column.
	 * @param row The row you want to search the number in.
	 * @param column The column you want to search the number in.
	 * @param number The number you want to search for.
	 * @return Returns true if the number is contained at the given location.
	 */	
	public boolean isInBlock(int row, int column, int number) {
		int r = row - row % 3;
		int c = column - column % 3;
		
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (cells[i][j] == number)
					return true;
		
		return false;
	}
	
	/**
	 * Checks if the nuber is in the given column.
	 * @param column The column you want to search the number in.
	 * @param number The number you want search for.
	 * @return Returns true if the number is contained in the row.
	 */
	public boolean isInColumn(int column, int number) {
		for(int i = 0; i < 9; i++)
			if (cells[i][column] == number)
				return true;
		
		return false;
	}
	
	
	/**
	 * Check if the move is valid.
	 * @param row The row you want to make the move in.
	 * @param column The column you want to make the move in.
	 * @param value The value you want to verify.
	 * @return
	 */
	public boolean isValid(int row, int column, int value) {
		return !isInRow(row, value) && !isInColumn(column, value) && !isInBlock(row, column, value);
	}
	
	public SudokuBoard clone() {
		SudokuBoard copy = new SudokuBoard();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++)  {
				copy.setCell(i, j, this.getCell(i, j));
			}
		}
		
		return copy;
	}
	@Override
	public String toString() {
		
		String val = "";
		
		for(int x = 0; x < this.cells.length; x++) {
			for(int y = 0; y < this.cells[x].length; y++)
				val += cells[x][y] + ", ";
			
			val += "\n";
		}
		
		return val;
	}
}
