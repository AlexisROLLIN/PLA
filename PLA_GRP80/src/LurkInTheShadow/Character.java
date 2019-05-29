package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Character extends Component {
	int HP;
	int intensity;

	public Character(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col,
			int id_x, boolean show, int HP, int intensity) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		this.HP = HP;
		this.intensity = intensity;
	}
}
