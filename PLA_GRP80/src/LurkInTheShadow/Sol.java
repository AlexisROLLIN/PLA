package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sol extends Component {
	

	public Sol(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale,int screen) {
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
		m_idx = 0;
		m_show = true;
		splitSprite();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
