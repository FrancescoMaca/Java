package com.application.Logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameBoardTest {

    @Test
    public void test1() {
        GameBoard b01 = new GameBoard();
        String s;
        System.out.println("Initial Game Board should be all blanks. Test using toString");
        s = b01.toString();
        System.out.println(s);
        assertEquals(s,"Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank, ");
    }
    
    @Test
    public void test2(){
        String s;
        System.out.println("use getCell and set cell, then clear");
        GameBoard b02 = new GameBoard();
        b02.setCell(CellNumber.one, CellValue.X);
        b02.setCell(CellNumber.two, CellValue.O);
        b02.setCell(CellNumber.three, CellValue.X);
        b02.setCell(CellNumber.four, CellValue.O);
        b02.setCell(CellNumber.five, CellValue.X);
        b02.setCell(CellNumber.six, CellValue.O);
        b02.setCell(CellNumber.seven, CellValue.X);
        b02.setCell(CellNumber.eight, CellValue.O);
        b02.setCell(CellNumber.nine, CellValue.X);
        assertEquals(b02.getCell(CellNumber.two), CellValue.O);
        s = b02.toString();
        System.out.println(s);
        assertEquals(s,"X, O, X, O, X, O, X, O, X, ");   
        assertEquals(b02.getCell(CellNumber.one), CellValue.X);
        assertEquals(b02.getCell(CellNumber.two), CellValue.O);
        assertEquals(b02.getCell(CellNumber.three), CellValue.X);
        assertEquals(b02.getCell(CellNumber.four), CellValue.O);
        assertEquals(b02.getCell(CellNumber.five), CellValue.X);
        assertEquals(b02.getCell(CellNumber.six), CellValue.O);
        assertEquals(b02.getCell(CellNumber.seven), CellValue.X);
        assertEquals(b02.getCell(CellNumber.eight), CellValue.O);
        assertEquals(b02.getCell(CellNumber.nine), CellValue.X);
        b02.clear();
        s = b02.toString();
        System.out.println(s);
        assertEquals(s, "Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank, ");
    }
    
    @Test
    public void test3(){
        System.out.println("Test state() for winners and tie and inProgress");
        GameBoard b03 = new GameBoard();
        b03.setCell(CellNumber.one, CellValue.X);
        b03.setCell(CellNumber.two, CellValue.X);
        b03.setCell(CellNumber.three, CellValue.X);
        assertEquals(b03.state(), GameState.XWon);
        b03.clear();
        
        b03.setCell(CellNumber.four, CellValue.O);
        b03.setCell(CellNumber.five, CellValue.O);        
        b03.setCell(CellNumber.six, CellValue.O);
        assertEquals(b03.state(), GameState.OWon);
        b03.clear();
        
        b03.setCell(CellNumber.seven, CellValue.X);
        b03.setCell(CellNumber.eight, CellValue.X);
        b03.setCell(CellNumber.nine, CellValue.X);
        assertEquals(b03.state(), GameState.XWon);
        b03.clear();
        
        b03.setCell(CellNumber.one, CellValue.X);
        b03.setCell(CellNumber.four, CellValue.X);
        b03.setCell(CellNumber.seven, CellValue.X);
        assertEquals(b03.state(), GameState.XWon);
        b03.clear();
        
        b03.setCell(CellNumber.two, CellValue.O);
        b03.setCell(CellNumber.five, CellValue.O);        
        b03.setCell(CellNumber.eight, CellValue.O);
        assertEquals(b03.state(), GameState.OWon);
        b03.clear();
        
        b03.setCell(CellNumber.three, CellValue.X);
        b03.setCell(CellNumber.six, CellValue.X);
        b03.setCell(CellNumber.nine, CellValue.X);
        assertEquals(b03.state(), GameState.XWon);
        b03.clear();
        
        b03.setCell(CellNumber.one, CellValue.O);
        b03.setCell(CellNumber.five, CellValue.O);        
        b03.setCell(CellNumber.nine, CellValue.O);
        assertEquals(b03.state(), GameState.OWon);
        b03.clear();
        
        b03.setCell(CellNumber.three, CellValue.O);
        b03.setCell(CellNumber.five, CellValue.O);        
        b03.setCell(CellNumber.seven, CellValue.O);
        assertEquals(b03.state(), GameState.OWon);
        b03.clear();
        
        assertEquals(b03.state(), GameState.inProgress);
        
        b03.setCell(CellNumber.one, CellValue.X);
        b03.setCell(CellNumber.two, CellValue.X);
        b03.setCell(CellNumber.three, CellValue.O);
        b03.setCell(CellNumber.four, CellValue.O);
        b03.setCell(CellNumber.five, CellValue.O);        
        b03.setCell(CellNumber.six, CellValue.X);
        b03.setCell(CellNumber.seven, CellValue.X);
        b03.setCell(CellNumber.eight, CellValue.O);
        b03.setCell(CellNumber.nine, CellValue.X);
        System.out.println(b03.toString());
        assertEquals(b03.state(), GameState.Tie);
    }
    
    @Test
    public void test4(){
        System.out.println("Test freeCells");
        GameBoard b04 = new GameBoard();
        ArrayList<CellNumber> free = new ArrayList<CellNumber>();
        free = b04.freeCells();
        assertEquals(free.size(), 9);
        assertTrue(free.contains(CellNumber.one));
        assertTrue(free.contains(CellNumber.two));
        assertTrue(free.contains(CellNumber.three));
        assertTrue(free.contains(CellNumber.four));
        assertTrue(free.contains(CellNumber.five));
        assertTrue(free.contains(CellNumber.six));
        assertTrue(free.contains(CellNumber.seven));
        assertTrue(free.contains(CellNumber.eight));
        assertTrue(free.contains(CellNumber.nine));
        b04.clear();
        
        b04.setCell(CellNumber.one, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.one));
        b04.setCell(CellNumber.two, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.two));
        b04.setCell(CellNumber.three, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.three));
        b04.setCell(CellNumber.four, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.four));
        b04.setCell(CellNumber.five, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.five));
        b04.setCell(CellNumber.six, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.six));
        b04.setCell(CellNumber.seven, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.seven));
        b04.setCell(CellNumber.eight, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.eight));
        b04.setCell(CellNumber.nine, CellValue.X);
        free = b04.freeCells();
        assertFalse(free.contains(CellNumber.nine));
        assertEquals(free.size(), 0);
        
    }
    
    @Test
    public void test5(){
        System.out.println("Test Copy");
        GameBoard b05 = new GameBoard();
        GameBoard b06 = null;
        b05.setCell(CellNumber.one, CellValue.X);
        b05.setCell(CellNumber.two, CellValue.O);
        b06 = b05.copy();
        System.out.println("before B05 cell1 is: " + b05.getCell(CellNumber.one)); 
        System.out.println("before B05 cell2 is: " + b05.getCell(CellNumber.two)); 
        System.out.println("before B06 cell1 is: " + b06.getCell(CellNumber.one)); 
        System.out.println("before B06 cell2 is: " + b06.getCell(CellNumber.two)); 
        b05.setCell(CellNumber.one, CellValue.O);
        b05.setCell(CellNumber.two, CellValue.X);
        System.out.println("after B05 cell1 is: " + b05.getCell(CellNumber.one)); 
        System.out.println("after B05 cell2 is: " + b05.getCell(CellNumber.two));
        System.out.println("after B06 cell1 is: " + b06.getCell(CellNumber.one)); 
        System.out.println("after B06 cell2 is: " + b06.getCell(CellNumber.two)); 
        assertEquals(b06.getCell(CellNumber.one), CellValue.X);
        assertEquals(b06.getCell(CellNumber.two), CellValue.O);
        assertEquals(b05.getCell(CellNumber.one), CellValue.O);
        assertEquals(b05.getCell(CellNumber.two), CellValue.X);
        
    }
    
    @Test
    public void test6(){
        System.out.println("Test Player");
        GameBoard b07 = new GameBoard();
        assertEquals(b07.player(), CellValue.X);
        b07.setCell(CellNumber.one, CellValue.X);
        assertEquals(b07.player(), CellValue.O);
        b07.setCell(CellNumber.two, CellValue.O);
        assertEquals(b07.player(), CellValue.X);
    }
}
