package map_creator;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import LurkInTheShadow.Component;
import LurkInTheShadow.IDirection;
import LurkInTheShadow.Model;

public class Fleche {
	BufferedImage m_sprite;
	public int m_x;
	public int m_y;
	public int m_w,m_h;
	int m_nrows, m_ncols;
	public int m_idx;
	public BufferedImage[] m_sprites;
	public Model m_model;
	public float m_scale;
	public boolean m_show;
	public Fleche(Model model, BufferedImage sprite, int rows, int columns, int x, int y,
			float scale, int id_x, boolean show) {
		m_model = model;
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		m_x = x;
		m_y = y;
		m_idx=id_x;
		m_scale = scale;
		m_show = show;
		splitSprite();
		
	}
	
	public void Coordonnees(){
		if(m_model.mainPlayed.m_x<1024 && m_model.mainPlayed.m_y<768){
			this.m_x=50;
			this.m_y=40;
		}
		if(m_model.mainPlayed.m_x>=1024 && m_model.mainPlayed.m_y<768){
			this.m_x=125;
			this.m_y=30;
		}
		if(m_model.mainPlayed.m_x<1024 && m_model.mainPlayed.m_y>=768){
			this.m_x=100;
			this.m_y=110;
		}
		if(m_model.mainPlayed.m_x>=1024 && m_model.mainPlayed.m_y>=768){
			this.m_x=160;
			this.m_y=90;
		}

	}
	
	protected void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
//		m_step = 1;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w,
						m_h);
			}
		}
	}
	
	public void step (long now){
		if (this.m_idx==123){
			this.m_idx=124;
		}
		else{
			this.m_idx=123;
		}
	}
	
	
	public void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, (m_x)%1024,(m_y)%768, w, h, null);
	}
}
