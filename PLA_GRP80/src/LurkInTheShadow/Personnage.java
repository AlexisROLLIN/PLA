package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;

import edu.ricm3.game.Options;

public class Personnage extends Component {
	
	int lampe_x;
	int lampe_y;
	double lampe_width;
	double lampe_height;
	int myAmmo;
	

	public Personnage(Model model, int no, BufferedImage sprite, int rows,
			int columns, int x, int y, float scale, int screen) {
		
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
		m_idx = 25;
		m_show = true;
		lampe_x = 75;
		lampe_y = 75;
		lampe_width = 2.4 * lampe_x;
		lampe_height = 2.4 * lampe_y;
		myAmmo = 10;
	}

	boolean Vision(Component c) {
		Ellipse2D.Double player = new Ellipse2D.Double(this.m_x - lampe_x, this.m_y - lampe_y, lampe_width, lampe_height);
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
			} else {
				c.m_show = false;
			}
		}
	}
	
	public void Pick() {
		ListIterator<Component> iter = m_model.components();
		
		while (iter.hasNext()) {
			Component c = iter.next();
			
			if (c.m_x == this.m_x && c.m_y == this.m_y && c instanceof Items) {
				if (((Items)c).itemType == 1) {
					myAmmo += 5;
					m_model.nbAmmo--;
					m_model.ElementsM1.remove(c);
					return;
				}
				if (((Items)c).itemType == 2) {
					m_model.nbBattery--;
					m_model.ElementsM1.remove(c);
					return;
				}
				if (((Items)c).itemType == 3) {
					m_model.nbCmd--;
					m_model.ElementsM1.remove(c);
					return;
				}
				if (((Items)c).itemType == 4) {
					m_model.nbLife--;
					m_model.ElementsM1.remove(c);
					return;
				}
			}
		}
	}

	public void paint(Graphics g) {

		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);

	}

}
