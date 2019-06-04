package tests;

import java.awt.image.BufferedImage;
import interpreter.*;
import tests.Type;

public class Shooter extends Component {
	IAutomaton automate;
	Direction m_dir; 
	Type m_type;

	public Shooter(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		m_step = 8;
		m_dir = Direction.EAST;
		m_type = Type.PLAYER;
		splitSprite();
	}

	public void setAutomate(IAutomaton aut) {
		automate = aut;
	}

	public void setType(Type type) {
		m_type = type;
	}

	public boolean move(Direction d) {
		

		if (d == Direction.NORTH) {
			m_y--;
			m_dir = Direction.NORTH;
			System.out.println("Avance au Nord\n");
		}

		else if (d == Direction.SOUTH) {
			m_y++;
			m_dir = Direction.SOUTH;
			System.out.println("Avance au Sud \n");
		}

		else if (d == Direction.WEST) {
			m_x--;
			m_dir = Direction.WEST;
			System.out.println("Avance à l'Ouest \n");
		}

		else {
			m_x++;
			m_dir = Direction.EAST;
			System.out.println("Avance à l'Est \n");
		}

		return true; // L'action s'est bien déroulée
	}

	public boolean move() {

		switch (m_dir) {
		case NORTH:
			m_y--;
			break;
		case SOUTH:
			m_y++;
			break;
		case EAST:
			m_x++;
			break;
		default:
			m_x--;
			break;
		}

		return true; // L'action s'est bien déroulée
	}

	@Override
	public void step(long now) throws Interpreter_Exception {
		long elapsed = now - m_lastMove;

		if (elapsed > 60L) {
			m_lastMove = now;
			automate.step(this);
		}
	}

	public boolean hit(Direction d) {

		// Pas le vrai hit, juste une version pour tester.
		System.out.println("Frappe\n");
		return true;// L'action s'est bien déroulée
	}
}