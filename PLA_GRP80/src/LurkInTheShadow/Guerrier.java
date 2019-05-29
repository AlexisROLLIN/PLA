package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Guerrier extends Allié {
	boolean goRight;
	boolean goLeft;
	boolean m_avancer;
	boolean m_reculer;
	boolean m_monter;
	boolean m_descendre;
	
	public Guerrier (Model m, int x, int y, int w, int h, int id_x,boolean show,int PV,int Power,int rows,int col,float scale,BufferedImage sprite,int faction) {
		super(m,  x, y, w, h, id_x,show,PV,Power,rows,col,scale,sprite);
		splitSprite();
	}
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
	public void step(long now) {

		long elapsed = now - m_lastMove;
		if (elapsed > 60L) {
			m_lastMove = now;
			

			if (m_avancer) {
				id_x = 34;
				m_x += 50;
			}
			if (m_reculer) {
				id_x = 26;
				m_x -= 50;
			}
			if(m_monter) {
				id_x = 46;
				m_y -= 50;
			}
			if(m_descendre) {
				m_y += 50;
				id_x = 36;
			}
		}

	}

	public void avanceOn() {
		m_avancer = true;
	}

	public void reculeOn() {
		m_reculer = true;
	}

	public void monterOn() {
		m_monter = true;
	}

	public void descendreOn() {
		m_descendre = true;
	}

	public void avanceOff() {
		m_avancer = false;
	}

	public void reculeOff() {
		m_reculer = false;
	}
	
	public void monterOff() {
		m_monter = false;
	}

	public void descendreOff() {
		m_descendre = false;
	}

	public void Hit(int dir) {
		 if(dir==1) {
			 id_x=19;
			 
		 }
		 if(dir==2) {
			 f.id_x=17;
		 }
		 if(dir==3) {
			 f.id_x=18;
		 }

	}
}
