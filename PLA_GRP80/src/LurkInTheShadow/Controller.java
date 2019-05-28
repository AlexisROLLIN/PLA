package LurkInTheShadow;

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
import LurkInTheShadow.Model;
import LurkInTheShadow.View;
public class Controller extends GameController implements ActionListener{
	Model m_model;
	View m_view;
	
	
	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
	} 
	
	@Override
	public void step(long now) {
		m_model.step(now);
		m_view.step(now);

	}
	
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
			System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		if (e.getKeyChar() == 'd') {

			this.m_model.m_Mage.avanceOn();
		}
		if (e.getKeyChar() == 'z') {

			this.m_model.m_Mage.monterOn();
			this.m_model.m_Mage.tir(2);
		}
		if (e.getKeyChar() == 'q') {

			this.m_model.m_Mage.reculeOn();
		}
		if (e.getKeyChar() == 's') {

			this.m_model.m_Mage.descendreOn();
		}
		if (e.getKeyChar() == 'a') {
			this.m_model.m_Mage.tir(1);
		}
		if (e.getKeyChar() == 'e') {
			this.m_model.m_Mage.tir(2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());

		if (e.getKeyChar() == 'd') {
			this.m_model.m_Mage.avanceOff();
		}
		if (e.getKeyChar() == 'q') {

			this.m_model.m_Mage.reculeOff();
		}
		if (e.getKeyChar() == 'z') {

			this.m_model.m_Mage.monterOff();
		}
		if (e.getKeyChar() == 's') {

			this.m_model.m_Mage.descendreOff();
		}
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

