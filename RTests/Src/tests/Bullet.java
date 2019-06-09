package tests;

import java.awt.image.BufferedImage;

import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

public class Bullet extends Component {
	IAutomaton automate;
	IDirection m_dir;
	IType m_type;
	int vie;

	public Bullet(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			boolean show,IDirection dir, int v) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		m_type = IType.MISSILE;
		m_dir = dir;
		vie =v;
	}

	public boolean move(IDirection d) {
		
		if(vie ==0) {
			this.show = false;
		}

		else if (d == IDirection.NORTH) {
			m_y -= 32;
			m_dir = IDirection.NORTH;
			vie--;
	
		}

		else if (d == IDirection.SOUTH) {
			m_y += 32;
			m_dir = IDirection.SOUTH;
			vie--;
		}

		else if (d == IDirection.WEST) {
			m_x -= 32;
			m_dir = IDirection.WEST;
			vie--;
		}

		else {
			m_x += 32;
			m_dir = IDirection.EAST;
			vie--;
		}

		return true; // L'action s'est bien déroulée
	}

	public void step(long now) throws Interpreter_Exception {
		long elapsed = now - m_lastMove;

		if (elapsed > 60L) {
			m_lastMove = now;
			automate.step(this);
		}
	}
	public boolean IWait() {
		model.components.remove(this);
		return true;
	}
}
