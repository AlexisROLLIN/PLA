package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Fireball extends Weapon {
	int dir;
	long m_lastChange;

	public Fireball(Model m, float scale, BufferedImage sprite, int rows, int col, int id_x, boolean show, int dir) {
		super(m, m.m_mage.m_x, m.m_mage.m_y, m.m_mage.m_w, m.m_mage.m_h, scale, sprite, rows, col, id_x, show);
		this.dir = dir;
		m_sprites = new BufferedImage[rows * col];
		m_step = 2;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < col; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * col) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void setPosition(int x, int y, float scale) {
		m_x = (int) (x - scale * m_w / 2f);
		m_y = (int) (y - scale * m_h / 2f);
		m_scale = scale;
		id_x = 0;
	}

	public void step(long now) {
		long elapsed = now - m_lastChange;

		if (elapsed > 10L) {
			m_lastChange = now;

			if (dir == 1) {
				if (collision(-m_step, 0)) {
					model.components.remove(this);
				} else {
					id_x = 19;
					m_x -= m_step;
				}
			}

			if (dir == 2) {
				if (collision(m_step, 0)) {
					model.components.remove(this);
				} else {
					id_x = 17;
					m_x += m_step;
				}
			}
		}
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */

	@Override
	public void paint(Graphics g) {
		Image img;
		img = m_sprites[id_x];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);
	}
}
