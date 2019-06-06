package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import create.obstacle;
import edu.ricm3.game.Options;

public class Component {
	BufferedImage m_sprite;
	public int m_w, m_h;
	public int m_x;
	public int m_y;
	int m_nrows, m_ncols;
	int m_step;
	int m_nsteps;
	public int m_idx;
	public float m_scale;
	long m_lastMove, m_lastReverse;
	public BufferedImage[] m_sprites;
	public Model m_model;
	public int screen;
	public boolean m_show;

	public Component(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			int screen) {
		m_model = model;
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		m_x = x;
		m_y = y;
		m_scale = scale;
		m_show = false;
		if (screen == 1) {
			model.ElementsM1.add(this);
			this.screen = 1;
		}
		if (screen == 2) {
			model.ElementsM2.add(this);
			this.screen=2;
		}
			
		if (screen == 3) {
			model.ElementsM3.add(this);
			this.screen =3;
		}
	
		if (screen == 4) {
			model.ElementsM4.add(this);
			this.screen =4;
		}
			
		model.nbElements++;
		splitSprite();
	}
	
	public Component(){
		
	}

	public Rectangle getBounds() {
		return new Rectangle(m_x, m_y, m_w, m_h);
	}

	protected void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		m_step = 1;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	boolean Collision(Component c) {
		Rectangle r1 = this.getBounds();
		Rectangle r2 = c.getBounds();

		if (r1.intersects(r2)) {
			return true;
		} else
			return false;

	}

	boolean CollisionTotale() {
		if (Options.SHOW_M1) {
			Iterator<Component> iter = this.m_model.ElementsM1.iterator();
			Component tmp = iter.next();
			while (iter.hasNext()) {
				if (tmp instanceof obstacle) {
					if (this.Collision(tmp)) {
						return true;
					}
				}
				tmp = iter.next();
			}
		}
		if (Options.SHOW_M2) {
			Iterator<Component> iter = m_model.ElementsM2.iterator();
			Component tmp = iter.next();
			while (iter.hasNext()) {
				if (tmp instanceof obstacle) {
					if (this.Collision(tmp)) {
						return true;
					}
				}
				tmp = iter.next();
			}
		}
		if (Options.SHOW_M3) {
			Iterator<Component> iter = m_model.ElementsM3.iterator();
			Component tmp = iter.next();
			while (iter.hasNext()) {
				if (tmp instanceof obstacle) {
					if (this.Collision(tmp)) {
						return true;
					}
				}
				tmp = iter.next();
			}
		}
		if (Options.SHOW_M4) {
			Iterator<Component> iter = m_model.ElementsM4.iterator();
			Component tmp = iter.next();
			while (iter.hasNext()) {
				if (tmp instanceof obstacle) {
					if (this.Collision(tmp)) {
						return true;
					}
				}
				tmp = iter.next();
			}
		}

		return false;
	}
	
	public void paint(Graphics g) {

	}

}

