package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class Feu extends Weapon{
	int dir;
	long m_lastChange;
	public Feu(Model model, BufferedImage sprite, int rows, int columns, int id_x,float scale,boolean show,int dir) {
		super(model, model.m_Mage.m_x, model.m_Mage.m_y, model.m_Mage.m_w, model.m_Mage.m_h, id_x, rows, columns, scale, sprite, show);
		this.dir = dir;
		m_sprites = new BufferedImage[rows * columns];
		

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * columns) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void setPosition(int x, int y, float scale) {
		m_x = (int) (x - scale * m_w / 2f);
		m_y = (int) (y - scale * m_h / 2f);
		m_scale = scale;
		id_x = 0;
	}

	public void step(long now) {
		long elapsed = now - m_lastChange;
		if (elapsed > 10L) {
			m_lastChange = now;
			

			if (dir == 1) {
				m_x -= 2;
			}  if (dir == 2) {
				m_x += 2;
			} if (dir == 3) {
				m_y -= 2;
			}
		}
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		Image img;
		img = m_sprites[id_x];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);
	}

}
