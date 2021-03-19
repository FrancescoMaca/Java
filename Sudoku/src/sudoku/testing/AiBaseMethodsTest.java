package sudoku.testing;

import org.junit.jupiter.api.Test;

import sudoku.logic.SudokuBoard;

class AiBaseMethodsTest {
	
	@Test
	public void test1() {
		SudokuBoard board1 = new SudokuBoard();
		board1.setCell(0, 0, 5);
		board1.setCell(1, 0, 6);
		board1.setCell(0, 1, 3);
		board1.setCell(2, 1, 9);
		board1.setCell(2, 2, 8);
		
		//second block
		board1.setCell(0, 4, 7);
		board1.setCell(1, 3, 1);
		board1.setCell(1, 4, 9);
		board1.setCell(1, 5, 5);
				
		//third block
		board1.setCell(2, 7, 6);
		
		//fourth block
		board1.setCell(3, 0, 8);
		board1.setCell(4, 0, 4);
		board1.setCell(5, 0, 7);
		
		//fifth block
		board1.setCell(3, 4, 6);
		board1.setCell(4, 3, 8);
		board1.setCell(4, 5, 3);
		board1.setCell(5, 4, 2);
		
		//sixth block
		board1.setCell(3, 8, 3);
		board1.setCell(4, 8, 1);
		board1.setCell(5, 8, 6);
		
		//seventh block
		board1.setCell(6, 1, 6);
		
		//eight block
		board1.setCell(7, 3, 4);
		board1.setCell(7, 4, 1);
		board1.setCell(7, 5, 9);
		board1.setCell(8, 4, 8);
		
		//ninth block
		board1.setCell(6, 6, 2);
		board1.setCell(6, 7, 8);
		board1.setCell(7, 8, 5);
		board1.setCell(8, 7, 7);
		board1.setCell(8, 8, 9);
		
		System.out.println("Current board:\n" + board1.toString());
		if (board1.Backtrack(board1)) {
			
			System.out.println("Completed board: \n" + board1.toString());
		}
		else {
			System.out.println("The board cannot be resolved");
		}
	}
	
	@Test
	public void test2() {
		System.out.println("Testing setCell coords:");
		
		SudokuBoard board2 = new SudokuBoard();
		
		board2.setCell(3, 2, 8);
		
		System.out.println(board2.toString());
	}
}