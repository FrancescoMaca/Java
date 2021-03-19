package com.application.Logic;

import java.util.ArrayList;

public class SimpleMinMax {
    
	private int maxDepth = 10;      //max recursion depth, default 10
    private static int depth;       //Keeps track of recursion depth
    private CellValue maxPlayer;    //The PLAYER who wants the Maximum solution

    public SimpleMinMax(CellValue myMaxPlayer){
        maxPlayer = myMaxPlayer;    // Store the Max player on instantiation
    }

    /**
     * getMinMax - This is the entry point for the MinMax Algorithm. It it used to determine the AI's next move using
     *             the MinMax Algorithm
     */
    private int MaxValue(GameBoard state) {
        depth++;                            //Increment the recursion depth

        Integer value = Integer.MIN_VALUE; // Current MAX is -Infinity
        
        //If we are in a terminal state, just return the Utility value as the result
        if (state.state() != GameState.inProgress) {
            depth--;                     // And decrement the recursion depth
            return getScore(state.state());
        }

        //Since we are not in a terminal state, we need to find the best choice move
        //assuming our opponent will look for his best move after it
        ArrayList<CellNumber> actions = state.freeCells();
        for (CellNumber a : actions){
            value = Math.max(value, MinValue(Result(state, a)));
        }
        
        depth--;                        // decrement the recursion depth
        return value;                   // this is the value of to us of the current State
    }
    
    private int MinValue(GameBoard state){
        depth++;                            //Increment the recursion depth
        
        Integer value = Integer.MAX_VALUE; // Current MAX is -Infinity

        //If we are in a terminal state, just return the Utility value as the result
        if (state.state() != GameState.inProgress) {
            depth--;                     // And decrement the recursion depth

            return getScore(state.state());
        }

        //Since we are not in a terminal state, we need to find the best choice move
        //assuming our opponent will look for his best move after it
        ArrayList<CellNumber> actions = state.freeCells();
        for (CellNumber a : actions){
            value = Math.min(value, MaxValue(Result(state, a)));
        }

        depth--;                        // decrement the recursion depth
        return value;                   // this is the value of to us of the current State
    }
    
    public CellNumber getMinMax(GameBoard state){
        CellValue player = state.player();               //whose turn is it?
        CellNumber AIMove = null;                        //If there is no available move return null
        ArrayList<CellNumber> choices = state.freeCells();  //These are the possible actions that can be executed
        
        //The choice of looking for a Max or Min value depends on whether the current player
        //is equal to the max player passed during instantiation
        if (player == maxPlayer){
            //Max Player so look for the highest score from the available Actions
            //and select that Action as the return value
            Integer bestScore = Integer.MIN_VALUE;
            for (CellNumber choice : choices){
                Integer result = MinValue(Result(state,choice));

                if (result > bestScore) {
                    bestScore = result;
                    AIMove = choice;
                }
            }
        } else {
            //Min Player so look for the lowest score from the available Actions
            //and select that Action as the return value
            Integer bestScore = Integer.MAX_VALUE;
            for (CellNumber choice : choices){
                Integer result = MaxValue(Result(state,choice));
                if (result < bestScore){
                    bestScore = result;
                    AIMove = choice;
                }
            }
        }

        return AIMove;  //The selected AI Action
    }
    
    public GameBoard Result(GameBoard state, CellNumber action) {
        GameBoard newBoard = state.copy();
        newBoard.setCell(action, state.player());
        return newBoard;
    }
    
    /*
     * 1 = maxPlayer won
     * 0 = Tie
     * -1 = minPlayer won
     */
    private int getScore(GameState g) {
    	
    	if (g == GameState.valueOf(maxPlayer.toString() + "Won")) {
    		return 10 - depth;
    	}
    	if (g == GameState.Tie) {
    		return 0;
    	}
    	else {
    		return -10 + depth;
    	}
    }
    
    private void setDepth(int n) {
    	this.maxDepth = n;
    }
    /**
     * The following routines are just for debugging purposes
     */

    /**
     * reset - Force the recursion depth to 0
     */
    public void reset() {
        depth = 0;
    }

}
