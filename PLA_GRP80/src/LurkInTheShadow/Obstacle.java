package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Obstacle extends Component {

	public Obstacle(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int id_x, boolean show, int screen) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight()*(int)scale, sprite.getWidth()*(int)scale, scale, id_x, show, screen);
		m_idx = 0;
		m_show = true;
		splitSprite();
	}
	
}
