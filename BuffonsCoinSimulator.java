/*
 * File: BuffonsCoinSimulator.java
 * -----------------------------------
 * This file runs a BUffon Coin Simulator to verify the formal proof
 */

import acm.graphics.*;
import acm.gui.IntField;
import acm.program.*;
import acm.util.*;

import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BuffonsCoinSimulator extends Program implements BuffonCoinConstants{
	
	public void init () {
		drawTextFieldAndButton();
		add(board);
		addActionListeners();
	}


	private void drawTextFieldAndButton() {
		//North interactors
		add(new JLabel("No. of Rows: "), NORTH);
		numRow = new IntField(INIT_ROW);
		add(numRow, NORTH);
		
		add(new JLabel(SPACER), NORTH);
		
		add(new JLabel("No. of Col: "), NORTH);
		numCol = new IntField(INIT_COL);
		add(numCol, NORTH);
		
		add(new JLabel(SPACER), NORTH);
				
		add(new JLabel("blackTiles loc: "), NORTH);
		blackTiles = new JTextField(INPUT_LENGTH);
		add(blackTiles, NORTH);
		
		add(new JLabel(SPACER), NORTH);
		
		JButton updateBoard = new JButton ("Update Board");
		add(updateBoard, NORTH);
		
		//EAST interactors
		add(new JLabel("No. of Throws"), EAST);
		numThrow = new IntField(INIT_THROWS);		
		add(numThrow, EAST);
		
		add(new JLabel(SPACER), EAST);
		
		add(new JLabel("circle Radius"), EAST);
		circleRadius = new IntField(INIT_RADIUS);
		add(circleRadius, EAST);
		
		add(new JLabel(SPACER), EAST);
		
		add(new JLabel("Coin Toss Delay"), EAST);
		coinTossDelay = new JSlider (MIN_DELAY, MAX_DELAY, INIT_DELAY);
		add(coinTossDelay, EAST);
		
		add(new JLabel(SPACER), EAST);
		
		JButton simulate = new JButton("simulate");
		add(simulate, EAST);
		
		add(new JLabel(SPACER), EAST);
		
		JButton reset = new JButton("Reset");
		add(reset, EAST);
	}

	
	public void actionPerformed (ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("simulate")) {
			//Ensures that there is a board (where the row and column are larger than or equal to 1)
			//to perform simulation on
			if (!board.isValid()) {
				updateBoard();
			}
			board.throwCoins(numThrow.getValue(), circleRadius.getValue());
			board.showProbability();
		} 
		
		else if (cmd.equals("Update Board")) {
			updateBoard();
		} 
		
		else if (cmd.equals("Reset")) {
			board.clearVisuals();
		}
	}
	
	/*
	 * Method Name: UpdateBoard()
	 * ---------------------------
	 * Calls the updateBoard and blackTiles method from BuffonCoinVisual.
	 */
	private void updateBoard() {
		board.updateBoard(numRow.getValue(), numCol.getValue());
		board.blackTiles(blackTiles.getText());
	}
	
	/* Instance Variables */
	private IntField numRow;
	private IntField numCol;
	private JTextField blackTiles;
	private IntField numThrow;
	private IntField circleRadius;
	private JSlider coinTossDelay;
	
	private BuffonCoinVisual board = new BuffonCoinVisual(INIT_BOARD_SIZE, INIT_BOARD_SIZE);
}
