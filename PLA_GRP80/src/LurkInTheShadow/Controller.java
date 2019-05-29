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

public class Controller extends GameController implements ActionListener {
	Model m_model;
	View m_view;

	public Controller(Model model, View view) {
		this.m_model = model;
		this.m_view = view;
	}

	@Override
	public void step(long now) {
		m_model.step(now);
		m_view.step(now);
	}

	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());

		// move

		if (e.getKeyChar() == 'z') {
			m_model.m_mage.upOn();
			m_model.m_warrior.upOn();
		}

		if (e.getKeyChar() == 'q') {
			m_model.m_mage.leftOn();
			m_model.m_warrior.leftOn();
		}

		if (e.getKeyChar() == 's') {
			m_model.m_mage.downOn();
			m_model.m_warrior.downOn();
		}

		if (e.getKeyChar() == 'd') {
			m_model.m_mage.rightOn();
			m_model.m_warrior.rightOn();
		}

		// shoot

		if (e.getKeyChar() == 'a') {
			m_model.m_mage.hit(1);
			m_model.m_warrior.hit(1);
		}

		if (e.getKeyChar() == 'e') {
			m_model.m_mage.hit(2);
			m_model.m_warrior.hit(2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());

		if (e.getKeyChar() == 'z') {
			m_model.m_mage.upOff();
			m_model.m_warrior.upOff();
		}

		if (e.getKeyChar() == 'q') {
			m_model.m_mage.leftOff();
			m_model.m_warrior.leftOff();
		}

		if (e.getKeyChar() == 's') {
			m_model.m_mage.downOff();
			m_model.m_warrior.downOff();
		}

		if (e.getKeyChar() == 'd') {
			m_model.m_mage.rightOff();
			m_model.m_warrior.rightOff();
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
