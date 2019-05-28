package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class Mage extends Allié{
	boolean goRight;
	boolean goLeft;
	boolean m_avancer;
	boolean m_reculer;
	boolean m_monter;
	boolean m_descendre;
	
	public Mage (Model m, int x, int y, int w, int h, int id_x,boolean show,int PV,int Power,int rows,int col,float scale,BufferedImage sprite,int faction) {
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
				id_x = 17;
				m_x += 50;
			}
			if (m_reculer) {
				id_x = 3;
				m_x -= 50;
			}
			if(m_monter) {
				m_y -= 50;
			}
			if(m_descendre) {
				m_y += 50;
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

	public void tir(int dir) {
		 Feu f = new Feu(model,model.m_testSprite,7,7,18,1F,true,1);
		 f.setPosition(m_x, m_y, 1);
		 model.components.add(f);
		 f.id_x=9;
		 if(dir==1) {
			 id_x=17;
			 
		 }
		 else {
			 id_x=18;
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
