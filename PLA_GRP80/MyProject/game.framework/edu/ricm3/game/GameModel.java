package edu.ricm3.game;

public abstract class GameModel {

	  protected GameUI m_game;

	  protected GameModel() {
	  }

	  public GameUI getGameUI() {
	    return m_game;
	  }
	  
	  /**
	   * Simulation step.
	   * 
	   * @param now
	   *          is the current time in milliseconds.
	   */
	  public abstract void step(long now);
	  
	  public abstract void shutdown();
	}