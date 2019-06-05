package create;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import LurkInTheShadow.Component;
import LurkInTheShadow.Model;

public class obstacle extends Component {
	
	
	
	public obstacle(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int screen) {
		super(model, no, sprite, rows, columns, x, y, scale,screen);
		boolean m_show;
	}
	

	public void paint(Graphics g) {

		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);
	}
}
