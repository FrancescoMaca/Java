package com.application.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.application.Logic.CellNumber;
import com.application.Logic.CellValue;
import com.application.Logic.GameBoard;
import com.application.Logic.SimpleMinMax;

/*
 * Main part of the UI
 */
public class MainFrame {
	
    public JFrame frmTicTacToe;

    private CellValue player = null;
    
    private GameBoard currentGame = new GameBoard();
    private SimpleMinMax minMax;
    
    private JDialog startGameDialog = new JDialog(frmTicTacToe, Dialog.ModalityType.APPLICATION_MODAL);
    private JDialog winningDialog = new JDialog(frmTicTacToe, Dialog.ModalityType.APPLICATION_MODAL);
    private JPanel pnl_Cells = new JPanel();
    
    private TreeMap<CellNumber, JButton> buttons = new TreeMap<CellNumber, JButton>();
    
    /**
     * Create the application.
     */
    public MainFrame() {
    	initialize();
    	initializeStartGameDialog();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        /**
         * Frame
         */
    	
        frmTicTacToe = new JFrame();
        frmTicTacToe.getContentPane().setBackground(UIManager.getColor("Button.light"));
        frmTicTacToe.setTitle("Tic Tac Toe");
        frmTicTacToe.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Eclipse Projects\\GUI\\Res\\icon.png"));
        frmTicTacToe.setResizable(false);
        frmTicTacToe.setBackground(new Color(240, 240, 240));
        frmTicTacToe.setBounds(450, 100, 600, 600);
        frmTicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmTicTacToe.getContentPane().setLayout(null);

        /**
         * Label
         */
        JLabel lbTitle = new JLabel("Welcome to Tic Tac Toe");
        lbTitle.setLabelFor(frmTicTacToe);
        lbTitle.setBounds(0, 10, 594, 50);
        lbTitle.setToolTipText("");
        lbTitle.setVerticalAlignment(SwingConstants.TOP);
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("MV Boli", Font.BOLD, 30));
        frmTicTacToe.getContentPane().add(lbTitle);

        /**
         * Cells
         */
        pnl_Cells.setBounds(136, 99, 330, 330);
        frmTicTacToe.getContentPane().add(pnl_Cells);
        pnl_Cells.setLayout(null);
		
        JButton bt_Cell1 = new JButton(" ");
        bt_Cell1.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell1.setFocusPainted(false);
        bt_Cell1.setContentAreaFilled(false);
        bt_Cell1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.one, currentGame);
            }
        });
        bt_Cell1.setBounds(0, 0, 110, 110);
        pnl_Cells.add(bt_Cell1);

        JButton bt_Cell2 = new JButton(" ");
        bt_Cell2.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell2.setFocusPainted(false);
        bt_Cell2.setContentAreaFilled(false);
        bt_Cell2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.two, currentGame);
            }
        });
        bt_Cell2.setBounds(110, 0, 110, 110);
        pnl_Cells.add(bt_Cell2);

        JButton bt_Cell3 = new JButton(" ");
        bt_Cell3.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell3.setFocusPainted(false);
        bt_Cell3.setContentAreaFilled(false);
        bt_Cell3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.three, currentGame);
            }
        });
        bt_Cell3.setBounds(220, 0, 110, 110);
        pnl_Cells.add(bt_Cell3);

        JButton bt_Cell4 = new JButton(" ");
        bt_Cell4.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell4.setFocusPainted(false);
        bt_Cell4.setContentAreaFilled(false);
        bt_Cell4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.four, currentGame);
            }
        });
        bt_Cell4.setBounds(0, 110, 110, 110);
        pnl_Cells.add(bt_Cell4);

        JButton bt_Cell5 = new JButton(" ");
        bt_Cell5.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell5.setFocusPainted(false);
        bt_Cell5.setContentAreaFilled(false);
        bt_Cell5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.five, currentGame);
            }
        });
        bt_Cell5.setBounds(110, 110, 110, 110);
        pnl_Cells.add(bt_Cell5);

        JButton bt_Cell6 = new JButton(" ");
        bt_Cell6.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell6.setFocusPainted(false);
        bt_Cell6.setContentAreaFilled(false);
        bt_Cell6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.six, currentGame);
            }
        });
        bt_Cell6.setBounds(220, 110, 110, 110);
        pnl_Cells.add(bt_Cell6);

        JButton bt_Cell7 = new JButton(" ");
        bt_Cell7.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell7.setFocusPainted(false);
        bt_Cell7.setContentAreaFilled(false);
        bt_Cell7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.seven, currentGame);
            }
        });
        bt_Cell7.setBounds(0, 220, 110, 110);
        pnl_Cells.add(bt_Cell7);

        JButton bt_Cell8 = new JButton(" ");
        bt_Cell8.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell8.setFocusPainted(false);
        bt_Cell8.setContentAreaFilled(false);
        bt_Cell8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.eight, currentGame);
            }
        });
        bt_Cell8.setBounds(110, 220, 110, 110);
        pnl_Cells.add(bt_Cell8);

        JButton bt_Cell9 = new JButton(" ");
        bt_Cell9.setFont(new Font("MV Boli", Font.PLAIN, 50));
        bt_Cell9.setFocusPainted(false);
        bt_Cell9.setContentAreaFilled(false);
        bt_Cell9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CellClick(CellNumber.nine, currentGame);
            }
        });
        bt_Cell9.setBounds(220, 220, 110, 110);
        pnl_Cells.add(bt_Cell9);

        /**
         * Exit Button
         */
        JButton bt_Exit = new JButton("Exit");
        bt_Exit.setFocusPainted(false);
        bt_Exit.setContentAreaFilled(false);
        bt_Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bt_Exit.setFont(new Font("MV Boli", Font.PLAIN, 19));
        bt_Exit.setBounds(390, 500, 180, 50);
        frmTicTacToe.getContentPane().add(bt_Exit);

        /**
         * Reset Button
         */
        JButton bt_StartGame = new JButton("Start Game"); 
        bt_StartGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

                //resetting buttons
                for(Component c : pnl_Cells.getComponents()) {
                    JButton b = (JButton) c;
                    b.setText(" ");
                }
                currentGame.clear();
                
                /*
                 * settign up dialog
                 */
                //Player must choice X or O
                JDialog startGameDialog = new JDialog(frmTicTacToe, Dialog.ModalityType.DOCUMENT_MODAL);

                JLabel lbMessage = new JLabel("Select X or O");
                lbMessage.setFont(new Font("MV Boli", Font.PLAIN, 19));
                lbMessage.setBounds(10, 0, 200, 40);

                JButton btX = new JButton("X");
                btX.setFont(new Font("MV Boli", Font.PLAIN, 15));
                btX.setBounds(50, 50, 90, 40);
                btX.setContentAreaFilled(false);
                btX.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	minMax = new SimpleMinMax(CellValue.O);
                    	player = CellValue.X;
                    	
                        startGameDialog.setEnabled(false);
                        startGameDialog.dispose();
                        
                        for(Component c : pnl_Cells.getComponents()) {
                        	if (c instanceof JButton) {
                        		c.setEnabled(true);
                        	}
                        }
                        
                    }
                });

                JButton btO = new JButton("O");
                btO.setFont(new Font("MV Boli", Font.PLAIN, 15));
                btO.setBounds(160, 50, 90, 40);
                btO.setContentAreaFilled(false);
                btO.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        minMax = new SimpleMinMax(CellValue.X);
                        player = CellValue.O;
                        startGameDialog.setEnabled(false);
                        startGameDialog.dispose();
                        
                        //X starts first
                        CellNumber aiCell = minMax.getMinMax(currentGame);
                        currentGame.setCell(aiCell, currentGame.player());
                    	buttons.get(aiCell).setText(currentGame.getCell(aiCell).toString());
                    	
                    	for(Component c : pnl_Cells.getComponents()) {
                        	if (c instanceof JButton) {
                        		c.setEnabled(true);
                        	}
                        }
                    }
                });

                startGameDialog.setBounds(frmTicTacToe.getLocationOnScreen().x + (frmTicTacToe.getWidth() / 2) - (320 / 2), frmTicTacToe.getLocationOnScreen().y + (frmTicTacToe.getHeight() / 2)  - (135 / 2), 320, 135);
                startGameDialog.setLayout(null);
                startGameDialog.add(lbMessage);
                startGameDialog.add(btX);
                startGameDialog.add(btO);
                startGameDialog.setTitle("Please Pick X or O");
                startGameDialog.setVisible(true);
            }
        });

        /*
         * Initialize the TreeMap
         */
        buttons.put(CellNumber.one, bt_Cell1);
        buttons.put(CellNumber.two, bt_Cell2);
        buttons.put(CellNumber.three, bt_Cell3);
        buttons.put(CellNumber.four, bt_Cell4);
        buttons.put(CellNumber.five, bt_Cell5);
        buttons.put(CellNumber.six, bt_Cell6);
        buttons.put(CellNumber.seven, bt_Cell7);
        buttons.put(CellNumber.eight, bt_Cell8);
        buttons.put(CellNumber.nine, bt_Cell9);
        
        bt_StartGame.setBounds(30, 500, 180, 50);
        bt_StartGame.setFont(new Font("MV Boli", Font.PLAIN, 19));
        bt_StartGame.setFocusPainted(false);
        bt_StartGame.setContentAreaFilled(false);
        frmTicTacToe.getContentPane().add(bt_StartGame);
    }

    private void CellClick(CellNumber n, GameBoard g) {
    	//checks if the user has started the game
    	if (player == null) {
    		startGameDialog.setVisible(true);
    		return;
    	}
    	//checks if the cell is free
    	if (!currentGame.getCell(n).equals(CellValue.Blank)) {
    		System.out.println("cell occupated!\n" + g.toString());
    		return;
    	}
    	
    	//were modifying "cells" TreeMap
    	g.setCell(n, g.player());
    	
    	buttons.get(n).setText(g.getCell(n).toString());
    	
    	//checking if someone has won
    	if (CheckForWinners(g)) {
    		return;
    	}
    	
    	/*
    	 * Ai move
    	 */    	
    	CellNumber aiCell = minMax.getMinMax(g);
    	g.setCell(aiCell, g.player());
    	buttons.get(aiCell).setText(g.getCell(aiCell).toString());
    	
    	//checking if someone has won
    	CheckForWinners(g);
    }
    
    /*
	 * Initialization of startGameDialog
	 */
    private void initializeStartGameDialog() {
    	JLabel label = new JLabel("You'll need to start a game first!");
		label.setFont(new Font("MV Boli", Font.PLAIN, 20));
		label.setBounds(20, 20, 500, 30);
		startGameDialog.add(label);
		
		JButton button = new JButton();
		button.setFont(new Font("MV Boli", Font.PLAIN, 30));
		button.setText("Ok");
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBounds(20, 85, 70, 50);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGameDialog.setVisible(false);
			}
		});
		startGameDialog.add(button);
		
		startGameDialog.setTitle("Game not started");
		startGameDialog.setLayout(null);

		//cant use frmTicTacToe.getLocationOnScreen(), it throws a IllegalComponentStateException: "component must be showing on the screen to determine its location",
		//even though the frame is already visible so I adjusted the position based on a 1920x1080 display.
		startGameDialog.setBounds(960 - 400, 540 - 200, 400, 200);
		
    }
    
    /*
	 * Initialization of endGameDialog
	 */
    private boolean CheckForWinners(GameBoard g) {
    	JLabel label = new JLabel();
    	label.setFont(new Font("MV Boli", Font.PLAIN, 20));
		label.setBounds(20, 20, 500, 30);
		switch(g.state()) {
    		case XWon:
    			label.setText("X has won the game!");
    			break;
    		case OWon:
    			label.setText("O has won the game!");
    			break;
    		case Tie:
    			label.setText("It's a tie!");
    			break;
			default:
				return false;
    	}
		
		winningDialog.add(label);
		
		JButton button = new JButton();

		button.setBounds(20, 60, 60, 50);
		button.setText("Ok");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				winningDialog.setVisible(false);
			}
		});
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setFont(new Font("MV Boli", Font.PLAIN, 18));	
		winningDialog.add(button);
		
		winningDialog.setTitle("Game ended");
		winningDialog.setLayout(null);
    	winningDialog.setBounds(960 - 400, 540 - 200, 300, 150);
    	winningDialog.setVisible(true);
    	
    	return true;
    }
}
