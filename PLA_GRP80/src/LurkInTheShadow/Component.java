package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Component {
	Model model;
	int m_x, m_y;
	int m_w, m_h;
	float m_scale;
	BufferedImage[] m_sprites;
	BufferedImage m_sprite;
	int m_nrows, m_ncols;
	int id_x;
	boolean show;
	long m_lastMove;

	public Component(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col,
			int id_x, boolean show) {
		this.model = m;
		this.m_x = x;
		this.m_y = y;
		this.m_w = w;
		this.m_h = h;
		this.m_scale = scale;
		this.m_sprite = sprite;
		this.m_nrows = rows;
		this.m_ncols = col;
		this.id_x = id_x;
		this.show = show;
	}

	public void pop() {
	}

	public void wizz() {
	}

	public void egg() {
	}

	public void hit() {
	}

	public void paint(Graphics g) {
	}

	public void step(long now) {
	}
}
