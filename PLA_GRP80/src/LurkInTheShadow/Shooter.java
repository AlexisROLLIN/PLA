package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import interpreter.*;



public class Shooter extends Ally {

	public Shooter(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x, boolean show,int screen) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show,screen);
		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.PLAYER;
		m_show = true;
		splitSprite();
	}


	boolean Vision(Component c) {
		int x = 50;
		int y = 50;
		Ellipse2D.Double player = new Ellipse2D.Double(this.m_x - x, this.m_y - y, 2.5 * x, 2.5 * y);
		Rectangle objet = c.getBounds();

		if (player.intersects(objet)) {
			return true;
		}
		return false;
	}

	public void Afficher() {
		Iterator<Component> iter = m_model.components();

		while (iter.hasNext()) {
			Component c = iter.next();
			if (Vision(c)) {
				c.m_show = true;
			} else {
				c.m_show = false;
			}
		}
	}
/*@Override	
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
	}*/

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

	public void paint(Graphics g) {

		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);

	}

}
