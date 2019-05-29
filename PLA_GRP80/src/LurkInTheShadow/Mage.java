package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Mage extends Allié {
	boolean goRight;
	boolean goLeft;
	boolean m_avancer;
	boolean m_reculer;
	boolean m_monter;
	boolean m_descendre;

	public Mage(Model m, int x, int y, int w, int h, int id_x, boolean show, int PV, int Power, int rows, int col,
			float scale, BufferedImage sprite, int faction) {
		super(m, x, y, w, h, id_x, show, PV, Power, rows, col, scale, sprite);
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

			if (m_avancer) {
				id_x = 34;
				m_x += 8;

				if (1024 < m_x) {
					m_x -= (1024 + (int) (m_scale * m_w));
				}
			}

			if (m_reculer) {
				id_x = 26;
				m_x -= 8;

				if (0 > m_x + (int) (m_scale * m_w)) {
					m_x += 1024;
				}
			}

			if (m_monter) {
				id_x = 46;
				m_y -= 8;

				if (0 > m_y + (int) (m_scale * m_h)) {
					m_y += 768;
				}
			}

			if (m_descendre) {
				id_x = 36;
				m_y += 8;

				if (768 < m_y) {
					m_y -= (768 + (int) (m_scale * m_h));
				}
			}
		}
	}

	public void avanceOn() {
		m_avancer = true;
	}

	public void reculeOn() {
		m_reculer = true;
	}

	public void monterOn() {
		m_monter = true;
	}

	public void descendreOn() {
		m_descendre = true;
	}

	public void avanceOff() {
		m_avancer = false;
	}

	public void reculeOff() {
		m_reculer = false;
	}

	public void monterOff() {
		m_monter = false;
	}

	public void descendreOff() {
		m_descendre = false;
	}

	public void tir(int dir) {
		Feu f = new Feu(model, model.m_testSprite, 7, 7, 18, 8F, true, dir);
		f.setPosition(m_x, m_y, 1);
		model.components.add(f);
		f.id_x = 39;

		if (dir == 1) {
			f.id_x = 19;
		}
		if (dir == 2) {
			f.id_x = 17;
		}
		if (dir == 3) {
			f.id_x = 18;
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
