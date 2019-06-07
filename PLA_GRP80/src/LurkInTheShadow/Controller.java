
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
		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			this.m_model.perso1.MoveR();
			m_model.map.jViewport++;
		}

		if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
			this.m_model.perso1.MoveN();
			m_model.map.iViewport--;

		}
		if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			this.m_model.perso1.MoveS();
			m_model.map.iViewport++;
		}

		if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
			this.m_model.perso1.MoveL();
			m_model.map.jViewport--;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
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
