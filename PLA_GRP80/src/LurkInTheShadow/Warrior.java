package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Warrior extends Ally {
	int[] m_spritesGoUp = { 49, 50 };
	int[] m_spritesGoDown = { 51, 48, 52 };
	int[] m_spritesGoLeft = { 60, 56, 61 };
	int[] m_spritesGoRight = { 66, 62, 67 };
	int id_xUp = 0;
	int id_xDown = 0;
	int id_xLeft = 0;
	int id_xRight = 0;

	public Warrior(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			boolean show, int HP, int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show, HP, intensity);
		m_step = 8;
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

				if (!collision(0, -m_step)) {
					id_x = m_spritesGoUp[id_xUp];
					id_xUp = (id_xUp + 1) % m_spritesGoUp.length;
					m_y -= m_step;
				}

				if (0 > m_y + (int) (m_scale * m_h)) {
					m_y += 768;
				}
			}

			if (m_goDown) {

				if (!collision(0, m_step)) {
					id_x = m_spritesGoDown[id_xDown];
					id_xDown = (id_xDown + 1) % m_spritesGoDown.length;
					m_y += m_step;
				}

				if (768 < m_y) {
					m_y -= (768 + (int) (m_scale * m_h));
				}
			}

			if (m_goLeft) {

				if (!collision(-m_step, 0)) {
					id_x = m_spritesGoLeft[id_xLeft];
					id_xLeft = (id_xLeft + 1) % m_spritesGoLeft.length;
					m_x -= m_step;
				}

				if (0 > m_x + (int) (m_scale * m_w)) {
					m_x += 1024;
				}
			}

			if (m_goRight) {

				if (!collision(m_step, 0)) {
					id_x = m_spritesGoRight[id_xRight];
					id_xRight = (id_xRight + 1) % m_spritesGoRight.length;
					m_x += m_step;
				}

				if (1024 < m_x) {
					m_x -= (1024 + (int) (m_scale * m_w));
				}
			}
		}
	}

	public void hit(int dir) {
	}

	@Override
	public void paint(Graphics g) {
		Image img = m_sprites[id_x];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);
	}
}
