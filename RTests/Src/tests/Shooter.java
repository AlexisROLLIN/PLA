package tests;

import java.awt.image.BufferedImage;
import interpreter.*;
import tests.*;

public class Shooter extends Component {
	

	public Shooter(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.PLAYER;
		splitSprite();
	}

	public boolean move(IDirection d) {
		

		if (d == IDirection.NORTH) {
			m_y-=32;
			m_dir = IDirection.NORTH;
			System.out.println("Avance au Nord\n");
		}

		else if (d == IDirection.SOUTH) {
			m_y+=32;
			m_dir = IDirection.SOUTH;
			System.out.println("Avance au Sud \n");
		}

		else if (d == IDirection.WEST) {
			m_x-=32;
			m_dir = IDirection.WEST;
			System.out.println("Avance à l'Ouest \n");
		}

		else {
			m_x+=32;
			m_dir = IDirection.EAST;
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

	public boolean hit(IDirection d) {
		int a =0;
		if(d==IDirection.EAST) {
			a=21;
		}
		if(d==IDirection.NORTH) {
			a=22;
		}
		if(d==IDirection.WEST) {
			a=23;
		}
		if(d==IDirection.SOUTH) {
			a=24;
		}

		Bullet b = new Bullet(model, this.m_x, this.m_y, 32, 32, 1F, this.m_sprite, 10, 9,a ,
				true, this.m_dir,3);
		model.components.add(b);
		b.setAutomate(model.TabAuto[3]);
		
		return true;
	}
}
