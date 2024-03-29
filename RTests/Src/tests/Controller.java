package tests;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import game.GameController;
import tests.Model;
import tests.View;

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

		if (e.getKeyChar() == 'z') {
			if (!m_model.touches.contains("z")) {
				m_model.touches.add("z");
			}
		}

		if (e.getKeyChar() == 'd') {
			if (!m_model.touches.contains("d")) {
				m_model.touches.add("d");
			}
		}

		if (e.getKeyChar() == 'q') {
			if (!m_model.touches.contains("q")) {
				m_model.touches.add("q");
			}
		}

		if (e.getKeyChar() == 's') {
			if (!m_model.touches.contains("s")) {
				m_model.touches.add("s");
			}
		}
		if (e.getKeyChar() == 'p') {
			if (!m_model.touches.contains("p")) {
				m_model.touches.add("p");
				m_model.Cgmt = 'p';
			}
			
		}
		if (e.getKeyChar() == 'm') {
			if (!m_model.touches.contains("m")) {
				m_model.touches.add("m");
				m_model.Cgmt = 'm';
			}
			
		}
		if (e.getKeyChar() == 'o') {
			if (!m_model.touches.contains("o")) {
				m_model.touches.add("o");
				m_model.Cgmt = 'o';
			}
			
		}
		
		if (e.getKeyCode() == 32) {
			if (!m_model.touches.contains("SPACE")) {
				m_model.touches.add("SPACE");
			}
			
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());

		if (e.getKeyChar() == 'z') {
			m_model.touches.remove("z");
		}
		if (e.getKeyChar() == 'd') {
			m_model.touches.remove("d");
		}
		if (e.getKeyChar() == 'q') {
			m_model.touches.remove("q");
		}
		if (e.getKeyChar() == 's') {
			m_model.touches.remove("s");
		}
		if (e.getKeyChar() == 'p') {
			m_model.touches.remove("p");
		}
		if (e.getKeyChar() == 'm') {
			m_model.touches.remove("m");
		}
		if (e.getKeyChar() == 'o') {
			m_model.touches.remove("o");
		}
		if (e.getKeyCode() == 32) {
			m_model.touches.remove("SPACE");
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
