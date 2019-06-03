package mesProto;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.util.Iterator;


public class Perso extends Component{

	int m_idx;
	  
	  Perso(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		super(model, no, sprite, rows, columns, x, y, scale);
		m_idx = 0;
		m_show = true;
		splitSprite();
	  }
	  
	  boolean Vision(Component c) {
		  int x = 50;
		  int y = 50;
		  Ellipse2D.Double player = new Ellipse2D.Double(this.m_x - x,this.m_y - y,4*x,4*y);
		  Rectangle objet = c.getBounds();
		  
		  if (player.intersects(objet)) {
			  return true;
		  }
		  return false;
	  }
	  
	  public void Afficher() {
		  Iterator<Component> iter = m_model.components();
		  
		  while (iter.hasNext()) {
			  Component c = iter.next();
			  if (Vision(c)) {
				  c.m_show = true;
			  }
			  else {
				  c.m_show = false;
			  }
		  }
	  }
	 
	  
	  @Override
	  void paint(Graphics g) {
	      Image img = m_sprites[m_idx];
	      int w = (int)(m_scale * m_w);
	      int h = (int)(m_scale * m_h);
	      g.drawImage(img, m_x, m_y, w, h, null);
	  }
}
