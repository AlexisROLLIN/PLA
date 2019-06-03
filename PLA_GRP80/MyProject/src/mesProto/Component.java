package mesProto;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.awt.Graphics;


public class Component {

	BufferedImage m_sprite;
	  int m_w, m_h;
	  int m_x, m_y;
	  int m_nrows, m_ncols;
	  int m_step;
	  int m_nsteps;
	  float m_scale;
	  long m_lastMove, m_lastReverse;
	  BufferedImage[] m_sprites;
	  Model m_model;
	  boolean m_show;
	  
	  Component(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale){
		  m_model = model;
		  m_sprite = sprite;
		  m_ncols = columns;
		  m_nrows = rows;
		  m_x = x;
		  m_y = y;
		  m_scale= scale;
		  m_show = false;
		  model.m_component.add(this);
	  }
	  
	  boolean Collision(Component c) {
			Rectangle r1 = this.getBounds();
			Rectangle r2 = c.getBounds();
			
			if (r1.intersects(r2)) {
				return true;
			}
			return false;
			
		}
	  
	  /*Component Colli() {
			Iterator<Component> iter = m_model.components();
			
			while (iter.hasNext()) {
				Component c = iter.next();
				if (!(c instanceof Floor)) {
					
				}
			}
		}*/
	  
	  public Rectangle getBounds() {
			return new Rectangle(m_x,m_y,m_w,m_h);
		}
	  
	  void splitSprite() {
		    int width = m_sprite.getWidth(null);
		    int height = m_sprite.getHeight(null);
		    m_sprites = new BufferedImage[m_nrows * m_ncols];
		    m_w = width / m_ncols;
		    m_h = height / m_nrows;
		    m_step = 8;
		    for (int i = 0; i < m_nrows; i++) {
		      for (int j = 0; j < m_ncols; j++) {
		        int x = j * m_w;
		        int y = i * m_h;
		        m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
		      }
		    }
	  }
	  
	  void paint(Graphics g) {
	  }
}
