package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Mage extends Ally {

	public Mage(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, spritesGoUp, spritesGoDown, spritesGoLeft, spritesGoRight,
				show, HP, intensity);
		m_step = 8;
		splitSprite();
	}

	public void hit(int dir) {
		Fireball f = new Fireball(model, 8F, model.m_testSprite, 10, 9, 17, true, dir);
		f.setPosition(m_x, m_y, 1);
		model.components.add(f);
		// f.id_x = 39;
	}
}
