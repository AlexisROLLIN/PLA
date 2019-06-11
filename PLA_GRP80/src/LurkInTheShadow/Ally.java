package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public class Ally extends Character {
	int faction;
//	Battery m_battery;

	public Ally(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int damage, int intensity) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, spritesGoUp, spritesGoDown, spritesGoLeft, spritesGoRight,
				show, HP, damage, intensity);
		this.faction = 0;
	}

	@Override
	public void step(long now) {
		long elapsed = now - m_lastMove;

		if (elapsed > 60L) {
			m_lastMove = now;

			if (m_goUp) {
				id_x = m_spritesGoUp[id_xUp];
				id_xUp = (id_xUp + 1) % m_spritesGoUp.length;
			}

			if (m_goDown) {
				id_x = m_spritesGoDown[id_xDown];
				id_xDown = (id_xDown + 1) % m_spritesGoDown.length;
			}

			if (m_goLeft) {
				id_x = m_spritesGoLeft[id_xLeft];
				id_xLeft = (id_xLeft + 1) % m_spritesGoLeft.length;
			}

			if (m_goRight) {
				id_x = m_spritesGoRight[id_xRight];
				id_xRight = (id_xRight + 1) % m_spritesGoRight.length;
			}
			
			translate();
			m_model.m_battery.consumes(m_intensity);
		}

		if (0 >= HP) {
			m_model.components.remove(this);
		}
	}

	public void paint(Graphics g) {
		Image img = m_sprites[id_x];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_origin.x, m_origin.y, w, h, null);
		g.setColor(Color.red);
		g.drawLine(m_origin.x, m_origin.y, (m_origin.x + w) * (int) (HP / maxHP), m_origin.y);
	}
}