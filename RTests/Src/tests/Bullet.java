package tests;

import java.awt.image.BufferedImage;

import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

public class Bullet extends Component {
	IAutomaton automate;
	IDirection m_dir;
	IType m_type;

	public Bullet(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			boolean show) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		m_type = IType.MISSILE;
	}

	public boolean move(IDirection d) {

		if (d == IDirection.NORTH) {
			m_y -= 32;
			m_dir = IDirection.NORTH;
			System.out.println("Avance au Nord\n");
		}

		else if (d == IDirection.SOUTH) {
			m_y += 32;
			m_dir = IDirection.SOUTH;
			System.out.println("Avance au Sud \n");
		}

		else if (d == IDirection.WEST) {
			m_x -= 32;
			m_dir = IDirection.WEST;
			System.out.println("Avance à l'Ouest \n");
		}

		else {
			m_x += 32;
			m_dir = IDirection.EAST;
			System.out.println("Avance à l'Est \n");
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
}
