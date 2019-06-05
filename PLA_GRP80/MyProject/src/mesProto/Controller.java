package mesProto;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import edu.ricm3.game.GameController;

public class Controller extends GameController implements ActionListener {

	Model m_model;
	View m_view;
	Button m_cowboysOn;
	Button m_explosionsOn;
	Button m_strobesOn;
	Button m_plus, m_minus;

	public Controller(Model model, View view) {
	  m_model = model;
	  m_view = view; 
	}
	
	@Override
	public void step(long now) {
	  m_model.step(now);
	  m_view.step(now);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'z') {
			m_model.m_perso.m_y -= 24;
		}
		if (e.getKeyChar() == 's') {
			m_model.m_perso.m_y += 24;
		}
		if (e.getKeyChar() == 'q') {
			m_model.m_perso.m_x -= 32;
		}
		if (e.getKeyChar() == 'd') {
			m_model.m_perso.m_x += 32;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	public void notifyVisible() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
