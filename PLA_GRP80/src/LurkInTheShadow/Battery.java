package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;

public class Battery {
	float m_durability; // Temps de la battery;
	BufferedImage m_sprite;
	public int m_x;
	public int m_y;
	public int m_w, m_h;
	int m_nrows, m_ncols;
	public int m_idx;
	public BufferedImage[] m_sprites;
	public Model m_model;
	public float m_scale;
	public boolean m_show;

	Battery(Model model, BufferedImage sprite, int rows, int columns, int x, int y,
			float scale, int id_x, boolean show) {

		m_durability = Options.MAX_DURABILITY;
		m_model = model;
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		m_x = x;
		m_y = y;
		m_idx = id_x;
		m_scale = scale;
		m_show = show;
		splitSprite();
	}

	protected void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w,
						m_h);
			}
		}
	}

	public void consumes(int intensity) {
		float consumes = (1 * intensity);

		if (0 > m_durability - consumes) {
			m_durability = 0;
		} else {
			m_durability -= consumes;
		}

		updateSprite(m_durability);
	}

	public void refill(float refill) {
		if (0 > m_durability + refill || Options.MAX_DURABILITY < m_durability) {
			m_durability = Options.MAX_DURABILITY;
		} else {
			m_durability += refill;
		}

		updateSprite(m_durability);
	}

	public void updateSprite(float durability) {
		float percentage = m_durability / Options.MAX_DURABILITY;
//		System.out.println(percentage);
		System.out.println(m_durability);

		if (0.75 <= percentage) { // 75% - 100%
			m_idx = 8;
		} else if (0.5 <= percentage) { // 50% - 74%
			m_idx = 9;
		} else if (0.25 <= percentage) { // 25% - 49%
			m_idx = 10;
		} else if (0.0 < percentage) { // 1% - 24%
			m_idx = 11;
		} else {
			m_idx = 12;
		}
	}

	/**
	 * paint the score on the IHM.
	 * 
	 * @param g
	 **/

	public void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);
	}
}