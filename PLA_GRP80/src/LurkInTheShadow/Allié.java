package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Alli� extends Personnage {
	int faction;

	public Alli�(Model m, int x, int y, int w, int h, int id_x, boolean show, int PV, int Power, int rows, int col,
			float scale, BufferedImage sprite) {
		super(m, x, y, w, h, id_x, show, PV, Power, rows, col, scale, sprite);
		this.faction = 0;
	}
}
