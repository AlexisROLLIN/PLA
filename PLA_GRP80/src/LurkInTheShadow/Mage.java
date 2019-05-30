package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Mage extends Ally {
	boolean m_goUp;
	boolean m_goLeft;
	boolean m_goDown;
	boolean m_goRight;

	public Mage(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			boolean show, int HP, int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show, HP, intensity);
		splitSprite();
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

	@Override
	public void step(long now) {

		long elapsed = now - m_lastMove;

		if (elapsed > 60L) {
			m_lastMove = now;
			
			if (m_goUp) {
				id_x = 45; // +46 +47
				m_y -= 8;

				if (0 > m_y + (int) (m_scale * m_h)) {
					m_y += 768;
				}
			}

			if (m_goLeft) {
				id_x = 25; // +26 +31 +32
				m_x -= 8;

				if (0 > m_x + (int) (m_scale * m_w)) {
					m_x += 1024;
				}
			}

			if (m_goDown) {
				id_x = 39; // +43 +44
				m_y += 8;

				if (768 < m_y) {
					m_y -= (768 + (int) (m_scale * m_h));
				}
			}
			
			if (m_goRight) {
				id_x = 37; // +38 <!> Il manque une image
				m_x += 8;

				if (1024 < m_x) {
					m_x -= (1024 + (int) (m_scale * m_w));
				}
			}
		}
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

	public void hit(int dir) {
		Fireball f = new Fireball(model, 8F, model.m_testSprite, 10, 9, 17, true, dir);
		f.setPosition(m_x, m_y, 1);
		model.components.add(f);
		// f.id_x = 39;

		if (dir == 1) {
			f.id_x = 19;
		}

		if (dir == 2) {
			f.id_x = 17;
		}
	}

	@Override
	public void paint(Graphics g) {
		Image img = m_sprites[id_x];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);
	}
}
