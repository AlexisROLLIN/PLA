package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Warrior extends Ally {

	public Warrior(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show, int HP,
			int damage, int intensity, int faction) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, spritesGoUp, spritesGoDown, spritesGoLeft, spritesGoRight,
				show, HP, damage, intensity);
		m_step = 8;
		splitSprite();
	}

	public void hit(int dir) {
	}
}
