/*
 * FileName: BuffonCoin.java
 * -------------------------
 * Provides the functionality of tossing coin,
 * and calculating probability that a coin lands in 
 * the grid of the BuffonCoinGrid
 */

import acm.graphics.*;
import acm.util.*;

import java.util.*;

public class BuffonCoin extends GCompound implements BuffonCoinConstants{
	
	/* constructor
	 * usage: BuffonCoin coin = new BuffonCoin(int circleRadius)
	 * -------------------------------------------
	 * Initializes a BuffonCoin object with the instance variable radius set 
	 * to equal the parameter circleRadius
	 */
	public BuffonCoin (int circleRadius) {
		radius = circleRadius;
	}
	
	
	/* Method name: setRadius
	 * usage: name.setRadius()
	 * -------------------------------------------
	 * Sets the instance variable radius to equal the parameter circleRadius
	 */
	public void setRadius(int circleRadius) {
		radius = circleRadius;
	}
	
	/* Method name: removeCoins
	 * usage: name.removeCoins();
	 * -------------------------------------------
	 * removes all the coins in the BuffonCoin class.
	 */
	public void removeCoins() {
		removeAll();
		coinLoc.clear();
	}
	
	/* Method name: coinToss
	 * usage: name.coinToss(int tosses, int windowWidth, int windowHeigh);
	 * -------------------------------------------
	 * Places tosses number of coin in the GCompound. 
	 * 
	 * Position of the individual coin is randomly generated using the RandomGenerator 
	 * class.
	 * 
	 * Position of individual coins are stored into an ArrayList to be redrawn later 
	 * when the window is resized.
	 */
	public void coinToss (int tosses, int windowWidth, int windowHeight) {
		coinLoc.clear();
		width = windowWidth;
		height = windowHeight;
		numToss = tosses;
		for (int i = 0; i < tosses; i ++) {
			GArc coin = new GArc (2 * radius, 2 * radius, 0, 360);
			double xPos = rgen.nextDouble(MARGIN_SIDE, width - MARGIN_SIDE- (2 * radius));
			double yPos = rgen.nextDouble(MARGIN_TOP, height - MARGIN_BOTTOM - (2 * radius));
			coinLoc.add(xPos);
			coinLoc.add(yPos);
			add(coin, xPos, yPos);
		}
	}
	
	/* Method name: reToss
	 * usage: name.reToss(int windowWidth, int windowHeigh);
	 * -------------------------------------------
	 * Removes all the coin in the GCompound and replaces them, partially scaled 
	 * (Doesn't scale exactly)to the new window
	 * 
	 * Used when the window of the application is resized.
	 */
	public void reToss(int windowWidth, int windowHeight) {
		removeAll();
		int marginY= MARGIN_BOTTOM + MARGIN_TOP;
		for (int i = 0; i < coinLoc.size(); i ++) {
			GArc coin = new GArc (2 * radius, 2 * radius, 0, 360);
			add(coin, coinLoc.get(i) / (width - 2 * MARGIN_SIDE ) * (windowWidth - 2 * MARGIN_SIDE)
						, coinLoc.get(++ i) / (height - marginY) * (windowHeight - marginY));
		}
	}
	
	/* Method name: getProbability
	 * usage: name.getProbability(ArrayList<Integer> blackTileLoc, int windowWidth, int windowHeight
			, int row, int col);
	 * -------------------------------------------
	 * Counts the number of coins that lands within a blackTile and divides that by 
	 * the number of total coin tosses to return the probability that said number 
	 * of tosses will result in a coin completely landing within a tile
	 */
	public double getProbability(ArrayList<Integer> blackTileLoc, int windowWidth, int windowHeight
			, int row, int col) {
		double xSpace = (windowWidth - 2 * MARGIN_SIDE) / col;
		double ySpace = (windowHeight - MARGIN_TOP - MARGIN_BOTTOM) / row;
		double inBox = 0;
		
		for (int i = 0; i < blackTileLoc.size(); i += 2) {
			double xPos = (blackTileLoc.get(i) - 1) * xSpace + MARGIN_SIDE;
			double yPos = (blackTileLoc.get(i + 1) - 1) * ySpace + MARGIN_TOP;
			double maxXDist = xPos + xSpace - 2 * radius;
			double maxYDist = yPos + ySpace - 2 * radius;
			for (int j = 0; j < coinLoc.size(); j += 2) {
				if (coinLoc.get(j) >= xPos && coinLoc.get(j) < maxXDist
						&& coinLoc.get(j + 1) >= yPos && coinLoc.get(j + 1) < maxYDist) {
					inBox++;
				}
			}
		}
		double probability = inBox / numToss;
		return probability;		
	}
	
	/*Instance Variables*/
	private int radius;
	private double numToss;
	private int width;
	private int height;
	private ArrayList<Double> coinLoc = new ArrayList<Double>();
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
