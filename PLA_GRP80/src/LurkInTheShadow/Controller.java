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
		
		if (e.getKeyChar() == 'b') {
			if (!m_model.touches.contains("b")) {
				m_model.touches.add("b");
				m_model.Cgmt = 'b';
			}
		

		}
		
		if (e.getKeyChar() == 'n') {
			if (!m_model.touches.contains("n")) {
				m_model.touches.add("n");
				m_model.Cgmt = 'n';
			}
		}
		
		if (e.getKeyChar() == 'r') {
			if (!m_model.touches.contains("r")) {
				m_model.touches.add("r");
				m_model.Cgmt = 'r';
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
			m_model.touches.remove("e");
		}
		if (e.getKeyChar() == 'b') {
			m_model.touches.remove("b");
		}
		if (e.getKeyChar() == 'n') {
			m_model.touches.remove("n");
		}
		if (e.getKeyChar() == 'r') {
			m_model.touches.remove("r");
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