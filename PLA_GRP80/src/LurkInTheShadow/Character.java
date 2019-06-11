package LurkInTheShadow;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Character extends Component {
	int maxHP;
	int HP;
	int damage;
	int m_intensity;
	int[] m_spritesGoUp;
	int[] m_spritesGoDown;
	int[] m_spritesGoLeft;
	int[] m_spritesGoRight;
	int id_xUp = 0;
	int id_xDown = 0;
	int id_xLeft = 0;
	int id_xRight = 0;

	public Character(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col,
			int id_x, int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show,
			int maxHP, int damage, int intensity) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		this.maxHP = maxHP;
		this.HP = maxHP;
		this.damage = damage;
		this.m_intensity = intensity;
		this.m_spritesGoUp = spritesGoUp;
		this.m_spritesGoDown = spritesGoDown;
		this.m_spritesGoLeft = spritesGoLeft;
		this.m_spritesGoRight = spritesGoRight;
	}
	
	public void vision() {
		Iterator<Component> iter = m_model.components.iterator();

		while (iter.hasNext()) {
			Component c = iter.next();
			if (inFieldOfVision(c)) {
				c.m_show = true;
			} else {
				c.m_show = false;
			}
		}
	}

	boolean inFieldOfVision(Component c) {
		int x = 50;
		int y = 50;
		Ellipse2D.Double playerView = new Ellipse2D.Double(m_origin.x - x, m_origin.y - y, 4 * x, 4 * y);
		Rectangle objet = c.getBounds();

		if (playerView.intersects(objet)) {
			return true;
		}
		
		return false;
	}

	public void hit(int dir) {
	}
}
