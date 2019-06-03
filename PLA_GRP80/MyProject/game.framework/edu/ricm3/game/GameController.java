package edu.ricm3.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class GameController implements MouseListener, MouseMotionListener, KeyListener {

  protected GameUI m_game;

  protected GameController() {
  }
  
  public GameUI getGameUI() {
    return m_game;
  }

  public abstract void notifyVisible();

  /**
   * Simulation step. Warning: the model has already executed its step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  public abstract void step(long now);

  @Override
  public abstract void keyTyped(KeyEvent e);

  @Override
  public abstract void keyPressed(KeyEvent e);

  @Override
  public abstract void keyReleased(KeyEvent e);

  @Override
  public abstract void mouseClicked(MouseEvent e);

  @Override
  public abstract void mousePressed(MouseEvent e);

  @Override
  public abstract void mouseReleased(MouseEvent e);

  @Override
  public abstract void mouseEntered(MouseEvent e);

  @Override
  public abstract void mouseExited(MouseEvent e);

  @Override
  public abstract void mouseDragged(MouseEvent e);

  @Override
  public abstract void mouseMoved(MouseEvent e);
}
