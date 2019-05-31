package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Shooter extends Ally {

	public Shooter(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, spritesGoUp, spritesGoDown, spritesGoLeft, spritesGoRight,
				show, HP, intensity);
		m_step = 8;
		splitSprite();
	}

	public void hit(int dir) {
		Bullet b = new Bullet(model, 8F, model.m_testSprite, 10, 9, 18, true, dir); // 18 ? num bullet
		b.setPosition(m_x, m_y, 1);
		model.components.add(b);
		// b.id_x = 48;
	}
}
