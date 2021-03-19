package com.application.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Handles the game logic
 */
public class GameBoard {
		
	private TreeMap<CellNumber, CellValue> cells = new TreeMap<CellNumber, CellValue>();
	
	private List<CellNumber[]> winCombos = new ArrayList<CellNumber[]>();
	
	public GameBoard() {        
		
		//Initializing cells array
		cells.put(CellNumber.one, CellValue.Blank);
		cells.put(CellNumber.two, CellValue.Blank);
		cells.put(CellNumber.three, CellValue.Blank);
		cells.put(CellNumber.four, CellValue.Blank);
		cells.put(CellNumber.five, CellValue.Blank);
		cells.put(CellNumber.six, CellValue.Blank);
		cells.put(CellNumber.seven, CellValue.Blank);
		cells.put(CellNumber.eight, CellValue.Blank);
		cells.put(CellNumber.nine, CellValue.Blank);
		
		//initializing winCombos list
		winCombos.add(new CellNumber[] { CellNumber.one, CellNumber.two, CellNumber.three });
		winCombos.add(new CellNumber[] { CellNumber.four, CellNumber.five, CellNumber.six });
		winCombos.add(new CellNumber[] { CellNumber.seven, CellNumber.eight, CellNumber.nine });
		winCombos.add(new CellNumber[] { CellNumber.one, CellNumber.four, CellNumber.seven });
		winCombos.add(new CellNumber[] { CellNumber.two, CellNumber.five, CellNumber.eight });
		winCombos.add(new CellNumber[] { CellNumber.three, CellNumber.six, CellNumber.nine });
		winCombos.add(new CellNumber[] { CellNumber.one, CellNumber.five, CellNumber.nine });
		winCombos.add(new CellNumber[] { CellNumber.three, CellNumber.five, CellNumber.seven });
	}
	
	public GameBoard copy() {
		GameBoard newBoard = new GameBoard();
		
		for(Map.Entry<CellNumber, CellValue> entry : this.cells.entrySet()) {
			newBoard.setCell(entry.getKey(), this.getCell(entry.getKey()));
		}
		
		return newBoard;
	}
	
	public void clear() {
		for(Map.Entry<CellNumber, CellValue> entry : cells.entrySet()) {
			entry.setValue(CellValue.Blank);
		}
	}
	
	public CellValue getCell(CellNumber cellNumber) {
		return cells.get(cellNumber);
	}
	
	public void setCell(CellNumber index, CellValue value) {
		//if the index exist it will overwrite its value
		if (cells.containsKey(index)) {
			cells.put(index, value);
		}
	}
	
	public CellValue player() {
		//X always starts
		return freeCells().size() % 2 == 0 ? CellValue.O : CellValue.X;
	}
	
	public GameState state() {
		for(CellNumber[] combo : winCombos) {
			
			String comboValue = "";
			
			for(CellNumber cellNumber : combo) {
				comboValue += cells.get(cellNumber).toString();
			}
			
			if (comboValue.equals("XXX")) {
				return GameState.XWon;
			}
			else if (comboValue.equals("OOO")) {
				return GameState.OWon;
			}
		}
		
		if (freeCells().size() == 0) {
			return GameState.Tie;
		}
		
		return GameState.inProgress;
	}
	
	public ArrayList<CellNumber> freeCells() {
		List<CellNumber> freeCells = new ArrayList<CellNumber>();
		
		for(Map.Entry<CellNumber, CellValue> entry : cells.entrySet()) {
			if (entry.getValue() == CellValue.Blank) {
				freeCells.add(entry.getKey());
			}
		}
		
		return (ArrayList<CellNumber>) freeCells;
	}
	
	@Override
	public String toString() {
		String values = "";
		
		for(Map.Entry<CellNumber, CellValue> entry : cells.entrySet()) {
			values += entry.getValue().toString() + ", ";
		}
		
		return values;
	}
}
