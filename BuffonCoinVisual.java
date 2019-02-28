/*
 * File Name: BuffonCoinVisual.java
 * ---------------------------------
 * Implements the graphics of the Buffon coin simulator that resizes as the user changes the window
 * by implementing the component listner interface.
 * 
 */

import acm.graphics.*;
import acm.util.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

public class BuffonCoinVisual extends GCanvas 
implements BuffonCoinConstants, ComponentListener {
	
	/*
	 * Constructor
	 * Usage: BuffonCoinVisual name = new BuffonCoinVisual(int row, int col);
	 * -----------------------------------------------------------------------
	 * Row and col Parameters correspond to the number of row and col you want on the display.
	 * 
	 * Initializes the instance variables row and col to match those of the row and col parameters
	 * Initializes a the instance variable of type BuffonCoinGrid with the said number of col and Row
	 * The BuffonCoinGrid is then added to the GCanvas.
	 */
	public BuffonCoinVisual (int row, int col) {
		this.row = row;
		this.col = col;
		addComponentListener(this);
		grid = new BuffonCoinGrid(row, col, getWidth(), getHeight());
		add(grid);
	}
	
	/*
	 * Method name: showMessage
	 * Usage: showMessage(string message)
	 * -------------------------------------
	 * Displays the message that you want at near the bottom of the GCanvas with a GLabel
	 * Sets the instance variable message as well to equal msg so that the text to 
	 * displayed is not lost when resizing the board.
	 */
	private void showMessage(String msg) {
		message = msg;
		msgLabel.setLabel(msg);
		msgLabel.setFont(LABEL_PROPERTIES);
		msgLabel.sendToFront();
		msgLabel.setColor(Color.MAGENTA);
		add(msgLabel, (getWidth() - msgLabel.getWidth()) / 2
				, getHeight() - msgLabel.getAscent() - boardStatsWidth.getAscent() - boardStatsHeight.getAscent() );
	}
	
	/*
	 * Method name: boardStats
	 * usage: boardStats(ArrayList<Double> stats)
	 * -------------------------------------------
	 * ArrayList<Double> stats contains two elements, the one at the 0 index correspond to the width 
	 * of the individual box. The element at the 1 index correspond to the height of the individual box.
	 * 
	 * Displays two GLabel. One above the other. Both are displayed right below the showMessage GLabel.
	 * The GLabel at the top shows the width of each board tile
	 * The GLabel at the bottom shows the height of each board tile
	 */
	private void boardStats(ArrayList<Double> stats) {
		if (stats.get(0) < 0) {
			stats.set(0, 0.0);
			stats.set(1, 0.0);
		}
		
		boardStatsWidth.setLabel("Board Width: " + stats.get(0));
		boardStatsWidth.setFont(LABEL_PROPERTIES);
		boardStatsWidth.sendToFront();
		boardStatsWidth.setColor(Color.MAGENTA);
		add(boardStatsWidth, (getWidth() - boardStatsWidth.getWidth()) / 2
				, getHeight() - boardStatsWidth.getAscent() - boardStatsHeight.getAscent() );
		
		boardStatsHeight.setLabel("Board Height: " + stats.get(1));
		boardStatsHeight.setFont(LABEL_PROPERTIES);
		boardStatsHeight.sendToFront();
		boardStatsHeight.setColor(Color.MAGENTA);
		add(boardStatsHeight, (getWidth() - boardStatsHeight.getWidth()) / 2, getHeight() - boardStatsHeight.getAscent() );
	}
	
	/*
	 * Method name: clearVisuals
	 * usage: name.clearVisuals();
	 * -------------------------------------------
	 * Removes everything from the GCanvas and resets the instance variables to their default states.
	 */
	public void clearVisuals() {
		removeAll();
		blackTileLoc.clear();
		row = INIT_BOARD_SIZE;
		col = INIT_BOARD_SIZE;
		coin.removeCoins();
		init = INIT_BOARD_SIZE;
		message = "Board Cleared";
		update();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Component#isValid()
	 *
	 * Method name: isValid
	 * usage: name.isValid();
	 * -------------------------------------------
	 * checks to see if the board in the visuals is properly initialized.
	 * Board is valid if the row and column are greater than 0
	 */
	public boolean isValid() {
		return row > 0 && col > 0;
	}
	
	/* Method name: updateBoard
	 * usage: name.updateBoard(int row, int col)
	 * -------------------------------------------
	 * Create a new board with the provided row and col in the parameters as the number of rows
	 * and the number of columns respectively. the new board is then displayed on the GCanvas.
	 * An error message is displayed on the GCanvas if the provided row or col parameter
	 * is less than  or equal to 0;
	 */
	public void updateBoard(int row, int col) {
		this.row = row;
		this.col = col;
		message = "Board Updated";
		if (row <= 0 || col <= 0) {
			message = "row and column have to be larger than 0";
		}
		update();
	}
	
	/* Method name: blackTiles
	 * usage: name.blackTiles(string tileLoc);
	 * -------------------------------------------
	 * Tokenizes a string into individual words.
	 * Does two checks: 
	 * The first is to ensure that there are an even number of words 
	 * (Each coordinates require two words)
	 * The second is to ensure that only digits are entered.
	 * 
	 * If either checks failed, an appropriate message is displayed informing the user fo the error.
	 * 
	 * Otherwise, black(green)Tiles at the said coordinates are displayed
	 */
	public void blackTiles(String tileLoc) {
		blackTileLoc.clear(); 
		StringTokenizer token = new StringTokenizer(tileLoc, " ,");
		if (token.countTokens() % 2 != 0) {
			showMessage("Coordinates for black tile loc is in improper format!");
			return;
		}
		while(token.hasMoreTokens()) {
			String coordString = token.nextToken();
			for (int i = 0; i < coordString.length(); i++) {
				char ch = coordString.charAt(i);
				if (!Character.isDigit(ch)) {
					showMessage("Coordinates should only contain numbers!");
					return;
				}
			}
			int coord= Integer.parseInt(coordString);
			blackTileLoc.add(coord);
		}
		update();
	}	
	
	/* Method name: throwCoins
	 * usage: name.throwCoins(int tosses, int circleRadius)
	 * -------------------------------------------
	 * Sets the coin object radius to be equal to circleRadius.
	 * Throws tosses number of coins through the coinToss method in the coin object class.
	 */
	public void throwCoins(int tosses, int circleRadius) {
		coin.setRadius(circleRadius);
		coin.coinToss(tosses, getWidth(), getHeight());
		init = APP_STATE_SIMUL;
		update();
	}
	
	/* Method name: showProbability
	 * usage: name.showProbability()
	 * -------------------------------------------
	 * gets the probability of coins that lands within a blackTile by 
	 * calling the getProbability() method in the coin object class.
	 */
	public void showProbability() {
		probability = coin.getProbability(blackTileLoc, getWidth(), getHeight(), row, col);
		showMessage("The probability of a coin landing in a coloured tile is " + probability);
	}
	
	/*
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Update is called whenever objects are added 
	* or removed.Update is also called whenever the size of the canvas 
	* changes.
	*/
	public void update() {
		removeAll();
		BuffonCoinGrid grid = new  BuffonCoinGrid(row, col, getWidth(), getHeight());
		boardStats(grid.drawBlackTiles(blackTileLoc));
		add(grid);
		coin.reToss(getWidth(), getHeight());
		add(coin);
		showMessage(message);
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { 
		update(); 
		if (init > 0) {
			showMessage("Calculated probability is now outdated, please re-simulate to get new value");	
		} else if (init == 0){
			showMessage("Application started");
			init --;
		}
	}
	public void componentShown(ComponentEvent e) { }	
	
	/* Instance Variables*/
	private int row;
	private int col;
	private GLabel msgLabel = new GLabel("");
	GLabel boardStatsWidth = new GLabel("");
	GLabel boardStatsHeight = new GLabel("");
	private String message = "";
	private BuffonCoinGrid grid;
	private ArrayList<Integer> blackTileLoc = new ArrayList<Integer>();
	private BuffonCoin coin = new BuffonCoin(INIT_RADIUS);
	private double probability;
	private int init = INIT_APP_STATE;
}

