package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

public class Component {
	Model model;
	Point m_origin;
	int m_w, m_h;
	float m_scale;
	BufferedImage[] m_sprites;
	BufferedImage m_sprite;
	int m_nrows, m_ncols;
	int id_x;
	int m_step;
	boolean show;
	long m_lastMove;
	
	boolean m_goUp;
	boolean m_goLeft;
	boolean m_goDown;
	boolean m_goRight;

	public Component(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col,
			int id_x, boolean show) {
		this.model = m;
		this.m_origin = new Point(x, y);
		this.m_w = w;
		this.m_h = h;
		this.m_scale = scale;
		this.m_sprite = sprite;
		this.m_nrows = rows;
		this.m_ncols = col;
		this.id_x = id_x;
		this.show = show;
	}

	void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;

		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void step(long now) {
	}

	public void pop() {
	}

	public void wizz() {
	}

	public void egg() {
	}

	public void hit() {
	}
	
	public void rightOn() {
		m_goRight = true;
	}

	public void leftOn() {
		m_goLeft = true;
	}

	public void upOn() {
		m_goUp = true;
	}

	public void downOn() {
		m_goDown = true;
	}

	public void rightOff() {
		m_goRight = false;
	}

	public void leftOff() {
		m_goLeft = false;
	}

	public void upOff() {
		m_goUp = false;
	}

	public void downOff() {
		m_goDown = false;
	}
	
	public Rectangle getBounds(int stepX, int stepY) {
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		return new Rectangle(m_origin.x + stepX, m_origin.y + stepY, w, h);
	}

	public LinkedList<Component> inCollisionWith(int stepX, int stepY) {
		Component comp;
		LinkedList<Component> listComp = new LinkedList<Component>();
		Iterator<Component> iter = model.components.iterator();
		while (iter.hasNext()) {
			comp = iter.next();
			if (this != comp) {
				if (comp.getBounds(0, 0).intersects(getBounds(stepX, stepY))) {
					System.out.println("this: " + this.toString() + " comp: " + comp.toString());
					listComp.add(comp);
				}
			}
		}

		return listComp;
	}
	
	public boolean canMove(int stepX, int stepY) {
		Component comp;
		Iterator<Component> iter = inCollisionWith(stepX, stepY).iterator();
		while (iter.hasNext()) {
			comp = iter.next();
			if (comp instanceof Wall) {
				return false;
			}
		}
		
		return true;
	}
	
	public void tore() {
		
		if (0 > m_origin.y + (int) (m_scale * m_h)) {
			m_origin.y += 768;
		}
		
		if (768 < m_origin.y) {
			m_origin.y -= (768 + (int) (m_scale * m_h));
		}
		
		if (0 > m_origin.x + (int) (m_scale * m_w)) {
			m_origin.x += 1024;
		}
		
		if (1024 < m_origin.x) {
			m_origin.x -= (1024 + (int) (m_scale * m_w));
		}
	}
	
	public void translate() {
		int stepX = 0;
		int stepY = 0;
		
		if (m_goUp) {
			stepY -= m_step;
		}
		
		if (m_goDown) {
			stepY += m_step;
		}
		
		if (m_goLeft) {
			stepX -= m_step;
		}
		
		if (m_goRight) {
			stepX += m_step;
		}
		
		if (canMove(stepX, stepY)) {
			m_origin.translate(stepX, stepY);
			tore();
		}
	}

	public void paint(Graphics g) {
		Image img = m_sprites[id_x];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_origin.x, m_origin.y, w, h, null);
	}
}
