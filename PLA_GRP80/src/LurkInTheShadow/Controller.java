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

		if (e.getKeyCode() == 90) {
			m_model.m_main.upOn();
		}

		if (e.getKeyCode() == 81) {
			m_model.m_main.leftOn();
		}

		if (e.getKeyCode() == 83) {
			m_model.m_main.downOn();
		}

		if (e.getKeyCode() == 68) {
			m_model.m_main.rightOn();
		}

		// shoot (to work)

		if (e.getKeyCode() == 65) {
			m_model.m_main.hit(1);
		}

		if (e.getKeyCode() == 69) {
			m_model.m_main.hit(2);
		}
		
		// change main character
		
		if (e.getKeyCode() == 49) {
			m_model.m_main = m_model.m_warrior;
		}
		
		if (e.getKeyCode() == 50) {
			m_model.m_main = m_model.m_shooter;
		}
		
		if (e.getKeyCode() == 51) {
			m_model.m_main = m_model.m_mage;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());

		if (e.getKeyCode() == 90) {
			m_model.m_main.upOff();
		}

		if (e.getKeyCode() == 81) {
			m_model.m_main.leftOff();
		}

		if (e.getKeyCode() == 83) {
			m_model.m_main.downOff();
		}

		if (e.getKeyCode() == 68) {
			m_model.m_main.rightOff();
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
