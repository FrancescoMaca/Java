package sudoku.AI;

import sudoku.logic.SudokuBoard;

public abstract class SudokuAI {

	/**
	 * Returns a copy of the board completed following sudoku's rules
	 * @param board The board you want to complete.
	 * @return Returns true if the board has been completed. If the board cannot be resolved it returns false
	 */
	public boolean Backtrack(SudokuBoard b) {
        for (int row = 0; row < 9; row++) {
        	for (int col = 0; col < 9; col++) {
        		// we search an empty cell
        		if (b.getBoard()[row][col] == 0) {
	        		// we try possible numbers
	        		for (int val = 1; val <= 9; val++) {
	        			if (b.isValid(row, col, val)) {
	        				// number ok. it respects sudoku constraints
							b.getBoard()[row][col] = val;
							
							if (Backtrack(b)) {
			    				// we start backtracking recursively
			    				return true;
			    			}
			    			else {
			    				// if not a solution, we empty the cell and we continue
			    				b.getBoard()[row][col] = 0;
			    			}
	        			}
            		}
            		return false; // we return false
                }
            }
        }
        return true; // sudoku solved
	}
}
