package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Mur extends Décor {
	public Mur(Model m, int x, int y, int w, int h, int id_x, int rows, int col, float scale, BufferedImage sprite,
			boolean show) {
		super(m, x, y, w, h, id_x, rows, col, scale, sprite, show);
		splitSprite();
	}
	
	public void step(long now) {}
	
	void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
			Image img = m_sprites[id_x];
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, m_x, m_y, w, h, null);
		}
}
