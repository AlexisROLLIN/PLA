package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Wall extends Decor {
	public Wall(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			boolean show) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		splitSprite();
	}

	public void step(long now) {
	}
}
