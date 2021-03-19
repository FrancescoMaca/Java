package sudoku.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridLayout;

import sudoku.logic.SudokuBoard;

public class UIBuilder {

	public JFrame frmSudoku;
	
	private JComboBox<String> comboBoxCellValue = new JComboBox<String>();

	private Color HOVER_COLOR = new Color(230, 230, 230);
	
	private SudokuBoard currentBoard = new SudokuBoard();
	
	private boolean errorFound = false;
	
	/**
	 * Create the application.
	 */
	public UIBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
				
		frmSudoku = new JFrame();
		frmSudoku.setTitle("Sudoku");
		frmSudoku.setBounds(100, 100, 900, 703);
		frmSudoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudoku.getContentPane().setLayout(null);
		
		
		JLabel lbTitle = new JLabel("Welcome to Sudoku Game Completer!");
		lbTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lbTitle.setBounds(10, 10, 578, 55);
		frmSudoku.getContentPane().add(lbTitle);
		
		JLabel lbValue = new JLabel("Cell value:");
		lbValue.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lbValue.setBounds(519, 75, 107, 35);
		frmSudoku.getContentPane().add(lbValue);
		
		comboBoxCellValue.setFont(new Font("SansSerif", Font.PLAIN, 20));
		comboBoxCellValue.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "Empty"}));
		comboBoxCellValue.setMaximumRowCount(4);
		comboBoxCellValue.setBounds(619, 76, 115, 32);
		frmSudoku.getContentPane().add(comboBoxCellValue);
		
		JLabel lbExplanation = new JLabel("<html>Choose a value and then click a<br>button to give the cell that value!</html>");
		lbExplanation.setHorizontalAlignment(SwingConstants.CENTER);
		lbExplanation.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lbExplanation.setVerticalAlignment(SwingConstants.TOP);
		lbExplanation.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lbExplanation.setBounds(28, 534, 354, 62);
		frmSudoku.getContentPane().add(lbExplanation);
		
		/** Creating main panel for the sudoku board **/
		JPanel pnSudokuBoard = new JPanel();
		pnSudokuBoard.setBounds(28, 75, 446, 447);
		pnSudokuBoard.setBorder(null);
		frmSudoku.getContentPane().add(pnSudokuBoard);
		pnSudokuBoard.setLayout(new GridLayout(0, 9, 0, 0));
		
		//Creating UI Cells (Buttons)
		JButton[] sudokuCells = new JButton[81];
		
		//Useful for giving each button its name
		int ctn = 0;
		
		// Set up cells
		for(JButton cell : sudokuCells) {
			// Initializing the button
			cell = new JButton();
			
			// Setting up the button settings
			cell.setFont(new Font("SansSerif", Font.PLAIN, 20));
			cell.setName(String.valueOf(ctn));
			
			// Setting the mouse event
			cell.addMouseListener(new MouseHandler());
			
			// Setting up the button color
			cell.setOpaque(true);
			cell.setBackground(Color.WHITE);
			cell.setFocusPainted(false);
			
			// Adding the button to the panel
			pnSudokuBoard.add(cell);
			
			ctn++;
		}
		
		JButton btComplete = new JButton("Complete Board");
		btComplete.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btComplete.setBounds(519, 487, 205, 35);
		btComplete.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (CheckGrid(currentBoard)) {
					System.out.println("The board is not valid");
					return;
				}
				SudokuBoard oldBoard = currentBoard.clone();
			
				currentBoard.Backtrack(currentBoard);

				System.out.println("oldBoad:");
				System.out.println(oldBoard.toString());
				
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						if (oldBoard.getCell(i, j) == 0) {
							System.out.println("i: " + i);
							System.out.println("j: " + j);
							System.out.println("value: " + ((i * 9) + j));
							sudokuCells[((i * 9) + j)].setForeground(Color.red);
							sudokuCells[((i * 9) + j)].setText(String.valueOf(currentBoard.getCell(i, j)));
						}
					}
				}
				System.out.println("Current board:");				
				System.out.println(currentBoard.toString());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}		
		});
		frmSudoku.getContentPane().add(btComplete);	
	}
	
	private class MouseHandler extends MouseAdapter {

		/*
		 * Checks if the board has errors.
		 */
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (errorFound) {
				System.out.println("There is an error with the board!");
				return;
			}
			
			JButton bt = (JButton)e.getSource();
			
			// Gets the name we set before and retrives the index of the button in the array
			int index = Integer.valueOf(bt.getName());

			// Calculating coordinates of the cell
			int x = index / 9;
			int y = index % 9;
			
			// Sets the cell on the sudokuBoard
			int selectedVal = 0;
			
			try {
				selectedVal = Integer.parseInt(comboBoxCellValue.getSelectedItem().toString());
				currentBoard.setCell(x, y, selectedVal);
				bt.setText(comboBoxCellValue.getSelectedItem().toString());
			}
			catch (NumberFormatException e1) {
				currentBoard.setCell(x, y, 0);
				bt.setText("");
			}
			
			errorFound = CheckGrid(currentBoard);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			
			bt.setBackground(HOVER_COLOR);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			
			bt.setBackground(Color.WHITE);
		}
	}

	private boolean CheckGrid(SudokuBoard board) {
		
		int[] blocksXY = { 1, 4, 7 };
		
		// Iterate throughout each block
		for(int x = 0; x < blocksXY.length; x++) {
			for(int y = 0; y < blocksXY.length; y++) {
				
				/* Checks the block for multiple numbers */
				
				// Calculate the block coordinates
				int r = blocksXY[x] - (blocksXY[x] % 3);
				int c = blocksXY[y] - (blocksXY[y] % 3);
				
				int[] numbers = new int[9];
				int index = 0;
				
				// Iterate throughout each cell
				for (int i = r; i < r + 3; i++) {
					for (int j = c; j < c + 3; j++) {
						
						// Saves the block's cell in a variable
						int val = board.getCell(i,  j);
						
						// Checks if the number just added matches one of the numbers in the block
						for(int k = 0; k < index; k++) {
							
							// If the number is already present in the array, there is an error, so it returns true
							if (numbers[k] == val && val != 0) {
								return true;
							}
						}
						
						// If number isnt found, it adds the number to the array
						if (val != 0) {
							numbers[index] = val;
							index++;
						}
					}
				}
			}
		}
		
		// Checks the row for multiple numbers
		for(int x = 0; x < 9; x++) {
			int[] numbers = new int[9];
			int index = 0;
			
			for(int y = 0; y < 9; y++) {
				
				// Saves the block's cell in a variable
				int val = board.getCell(x,  y);
				
				// Checks if the number just added matches one of the numbers in the row
				for(int k = 0; k < index; k++) {
					
					// If the number is already present in the array, there is an error, so it returns true
					if (numbers[k] == val && val != 0) {
						return true;
					}
					
					// If number isnt found, it adds the number to the array
					if (val != 0) {
						numbers[index] = val;
						index++;
					}
				}
			}
		}
		
		// Checks the column for multiple numbers
		for(int y = 0; y < 9; y++) {
			int[] numbers = new int[9];
			int index = 0;
			
			for(int x = 0; x < 9; x++) {
				
				// Saves the block's cell in a variable
				int val = board.getCell(x,  y);
				
				// Checks if the number just added matches one of the numbers in the column
				for(int k = 0; k < index; k++) {
					
					// If the number is already present in the array, there is an error, so it returns true
					if (numbers[k] == val && val != 0) {
						return true;
					}
					
					// If number isnt found, it adds the number to the array
					if (val != 0) {
						numbers[index] = val;
						index++;
					}
				}
			}
		}
		return false;
	}
}
