package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Shooter extends Ally {

	public Shooter(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int damage, int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, spritesGoUp, spritesGoDown, spritesGoLeft, spritesGoRight,
				show, HP, damage, intensity);
		m_step = 8;
		splitSprite();
	}

	public void hit(int dir) {
		Bullet b = new Bullet(m_model, 8F, m_model.m_testSprite, 10, 9, 18, true, dir, damage); // 18 ? num bullet
		b.setPosition(m_origin.x, m_origin.y, 1);
		m_model.components.add(b);
		// b.id_x = 48;
	}
}
