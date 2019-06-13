package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import edu.ricm3.game.Options;

public class Items extends Component{
	
	int itemType;
	
	public Items(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int screen, int item) {
		super(model, no, sprite, rows, columns, x, y, sprite.getWidth(), sprite.getHeight(), scale, screen);
		itemType = item;
		if (item == 1) {
			m_model.nbAmmo++;
			m_idx = 6;
		}
		if (item == 2) {
			m_model.nbBattery++;
			m_idx = 16;
		}
		if (item == 3) {
			m_model.nbCmd++;
			m_idx = 13;
		}
		if (item == 4) {
			m_model.nbLife++;
			m_idx = 15;
		}
	}
	
	@Override
	public void paint(Graphics g) {

		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		if (Options.SHOW_M1 && screen == 1) {
			g.drawImage(img, m_x, m_y, w, h, null);
		}
		if (Options.SHOW_M2 && screen == 2) {
			g.drawImage(img, m_x, m_y, w, h, null);
		}
		if (Options.SHOW_M3 && screen == 3) {
			g.drawImage(img, m_x, m_y, w, h, null);
		}
		if (Options.SHOW_M4 && screen == 4) {
			g.drawImage(img, m_x, m_y, w, h, null);
		}
	}
}
