package LurkInTheShadow;

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
import java.util.Map;

import edu.ricm3.game.GameController;
import edu.ricm3.game.Options;

public class Controller extends GameController implements ActionListener {

	Model m_model;
	View m_view;
	Button m_strobesOn;
	Button m_plus, m_minus;

	char buffer = 'a';

	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
	}

	@Override
	public void step(long now) {
		m_view.step(now);
	}

	@Override
	public void keyTyped(KeyEvent e) {

		/*
		 * if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
		 * m_model.map.jViewport++; if(m_model.perso1.m_x>2048){ m_model.perso1.m_x=0;
		 * m_model.map.jViewport=16; } }
		 * 
		 * if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
		 * m_model.map.iViewport--; if(m_model.perso1.m_y<0){ m_model.perso1.m_y=1536;
		 * m_model.map.iViewport=60; }
		 * 
		 * } if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
		 * m_model.map.iViewport++; if(m_model.perso1.m_y>1536){ m_model.perso1.m_y=0;
		 * m_model.map.iViewport=12; } }
		 * 
		 * if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
		 * m_model.map.jViewport--; if(m_model.perso1.m_x<0){ m_model.perso1.m_x=2048;
		 * m_model.map.jViewport=80; } }
		 */

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

		if (e.getKeyChar() == 'l') {
			if (!m_model.touches.contains("l")) {
				m_model.touches.add("l");
				m_model.Cgmt = 'l';
			}

		}

		if (e.getKeyCode() == 32) {
			if (!m_model.touches.contains("SPACE")) {
				m_model.touches.add("SPACE");
			}

		}

		if (e.getKeyChar() == 'a') {
			if (!m_model.touches.contains("a")) {
				m_model.touches.add("a");
				m_model.Cgmt = 'a';
			}

		}

		if (e.getKeyChar() == 'e') {
			if (!m_model.touches.contains("e")) {
				m_model.touches.add("e");
				m_model.Cgmt = 'e';
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

		if (e.getKeyChar() == 'l') {
			m_model.touches.remove("l");
		}

		if (e.getKeyCode() == 32) {
			m_model.touches.remove("SPACE");
		}

		if (e.getKeyChar() == 'a') {
			m_model.touches.remove("a");
		}

		if (e.getKeyChar() == 'e') {
			m_model.touches.remove("w");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();

	}

	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub

	}

}