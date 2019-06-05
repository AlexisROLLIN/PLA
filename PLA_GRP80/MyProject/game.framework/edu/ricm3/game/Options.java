package edu.ricm3.game;

public class Options {
	  /*
	   * You want to use double buffering... 
	   * Trust us, but you need to understand what that means.
	   * Look at the class GameView.
	   */
	  public static final boolean USE_DOUBLE_BUFFERING = true;
	  
	  /*
	   * We want to target 24 frame per seconds (fps),
	   * which is the following period in milliseconds
	   *   period = (1000.0 / 24.0)
	   */
	  static final double FPS = 30.0;
	  static final int REPAINT_DELAY = (int) (1000.0 / FPS);

	}
