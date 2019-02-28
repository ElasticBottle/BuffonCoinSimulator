/*
 * File Name: BuffonCoinGrid.java
 * --------------------------------
 * Provides method for drawing grid on the GCanvas.
 */

import java.awt.Color;
import java.util.*;
import acm.graphics.*;

public class BuffonCoinGrid extends GCompound implements BuffonCoinConstants{
	
	/* constructor
	 * usage: BuffonCoinGrid grid = new BuffonGrid(int row, int col, int width, int height)
	 * -------------------------------------------
	 * Initializes a BuffonCoin object that calls the drawGrid method to draw the actual grid.
	 */
	public BuffonCoinGrid (int row, int col, int width, int height) {
		drawGrid(row, col, width, height);
	}

	/* Method name: drawGrid
	 * usage: name.drawGrid(double row, double col, double width, double height)
	 * -------------------------------------------
	 * Draws a grid with row number of rows and col number of columns.
	 * 
	 * Width of the grid is determined through division of the width 
	 * with number of columns (col) after subtracting off the margins.
	 * 
	 * The height is determined in similar fashion
	 * 
	 * Double is utilized to prevent rounding down in its and hence 
	 * inconsistent grid height and width.
	 * 
	 * However, there seems to be floating point error and hence
	 * occasional gaps between individual tiles of the grid.
	 */
	private void drawGrid(double row, double col, double width, double height) {
		double x = MARGIN_SIDE;
		double y = MARGIN_TOP;
		xSpace = (width - 2 * MARGIN_SIDE) / col;
		ySpace = (height - MARGIN_TOP - MARGIN_BOTTOM) / row;
		for (int i = 0; i < row; i ++) {
			for (int j = 0; j < col; j++) {
				//Draw a box.
				GRect box = new GRect(xSpace, ySpace);
				box.setFilled(false);
				add(box, x, y);
				x += xSpace;
			}
			x = MARGIN_SIDE;
			y += ySpace;
		}
	}
	
	/* Method name: drawBlackTiles
	 * usage: name.drawBlackTiles(ArrayList<Integer> list)
	 * -------------------------------------------
	 * Array list provides the coordinates based on a 1 index 
	 * where blackTiles are to be drawn in at.
	 * 
	 * Even indexes of the ArrayList correspond to the x coordinates
	 * while the odd indexes of the ArrayList correspond to the y
	 * coordinates
	 * 
	 * Returns the width and height of the individual tiles which can be
	 * used with the BuffonCoinVisual to display the boardStats.
	 */
	public ArrayList<Double> drawBlackTiles(ArrayList<Integer> list ) {
		for (int i = 0; i < list.size(); i += 2) {
			int x = list.get(i);
			int y = list.get(i + 1);
			double xPos = (x - 1) * xSpace + MARGIN_SIDE;
			double yPos = (y - 1) * ySpace + MARGIN_TOP;
			GRect blackTile = new GRect (xSpace, ySpace);
			blackTile.setFilled(true);
			blackTile.setFillColor(Color.GREEN);
			add(blackTile, xPos, yPos);
		}
		
		ArrayList<Double> toReturnStats = new ArrayList<Double>();
		toReturnStats.add(xSpace);
		toReturnStats.add(ySpace);
		return toReturnStats;
	}
	
	/* Instance Variables */
	private double ySpace;
	private double xSpace;
}


