package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Ally extends Character {
	int faction;

	public Ally(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int intensity) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, spritesGoUp, spritesGoDown, spritesGoLeft, spritesGoRight,
				show, HP, intensity);
		this.faction = 0;
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
}