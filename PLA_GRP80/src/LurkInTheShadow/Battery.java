package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;

public class Battery extends Component {
	float m_durability;

	Battery(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			boolean show) {
		super(m, sprite, rows, col, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);

		m_durability = Options.MAX_DURABILITY;
		splitSprite();
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
		if (0 > m_durability + refill) {
			m_durability = Options.MAX_DURABILITY;
		} else {
			m_durability += refill;
		}

		updateSprite(m_durability);
	}

	public void updateSprite(float durability) {
		float percentage = m_durability / Options.MAX_DURABILITY;
		System.out.println(percentage);

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