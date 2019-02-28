/*
 * File Name: BUffonCoinConstants.java
 * -------------------------------------
 * Contains the constants for the Buffon's Coin simulator
 */

public interface BuffonCoinConstants {
	/** Width and height of application window in pixels.  IMPORTANT NOTE:
	  * ON SOME PLATFORMS THESE CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS
	  * OF THE GRAPHICS CANVAS.  Use getWidth() and getHeight() to get the 
	  * dimensions of the graphics canvas. */
		public static final int APPLICATION_WIDTH = 1200;
		public static final int APPLICATION_HEIGHT = 800;

		/** Initial size of the empty Buffon Coin board*/
		public static final int INIT_BOARD_SIZE = -1;
		
		/** Provides spacing between elements in the interactor sidebar*/
		public static final String SPACER= "     ";
		
		/** Maximum input length that can be displayed in the textfield*/
		public static final int INPUT_LENGTH = 20;
		
		/** Spacing from the edge of the left and right side of the window*/
		public static final int MARGIN_SIDE = 50;
		
		/** Spacing from the edge to the grid from the top of the window*/
		public static final int MARGIN_TOP = 50;
		
		/** Spacing from the edge to the grid from the bottom of the window*/
		public static final int MARGIN_BOTTOM = 100;
		
		/** Initial value of for the number of rows*/
		public static final int INIT_ROW = 5;
		
		/** Initial value of for the number of columns*/
		public static final int INIT_COL = 5;

		/** Initial value of for the number of throws*/
		public static final int INIT_THROWS = 50;
		
		/** Initial value of for the circle radius*/
		public static final int INIT_RADIUS = 50;
		
		/** Minimum value for the delay of the coin tossing*/
		public static final int MIN_DELAY = 0;
		
		/** Maximum value for the delay of the coin tossing*/
		public static final int MAX_DELAY = 100;
		
		/** Initial value of delay for the coin tossing*/
		public static final int INIT_DELAY = 5;
		
		/** Initial value of application state for controlling appropriate display message*/
		public static final int INIT_APP_STATE = 0;
		
		/** App state after simulating */
		public static final int APP_STATE_SIMUL = 1;
		
		/** Message label properties */
		public static final String LABEL_PROPERTIES = "SansSerif-Bold-15";
}
