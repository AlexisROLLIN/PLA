package edu.ricm3.game;

import java.awt.event.WindowEvent;

public class WindowListener implements java.awt.event.WindowListener {
  GameModel m_model;
  WindowListener(GameModel m) {
    m_model = m;
  }
  @Override
  public void windowOpened(WindowEvent e) {
  }

  @Override
  public void windowClosing(WindowEvent e) {
    m_model.shutdown();
    System.exit(0);
  }

  @Override
  public void windowClosed(WindowEvent e) {
  }

  @Override
  public void windowIconified(WindowEvent e) {
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
  }

  @Override
  public void windowActivated(WindowEvent e) {
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
  }

}
