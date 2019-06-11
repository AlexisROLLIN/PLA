package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Personnage extends Component {

	public Personnage(Model model, int no, BufferedImage sprite, int rows,
			int columns, int x, int y, float scale, int screen) {
		
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
		m_idx = 0;
		m_show = true;
		splitSprite();
	}

	boolean Vision(Component c) {
		int x = 50;
		int y = 50;
		Ellipse2D.Double player = new Ellipse2D.Double(this.m_x - x, this.m_y - y, 2.5 * x, 2.5 * y);
		Rectangle objet = c.getBounds();

		if (player.intersects(objet)) {
			return true;
		}
		return false;
	}

	public void Afficher() {

		for(int i=0; i<m_model.map.length*2;i++){
			for (int j =0 ; j<m_model.map.width*2;j++){
				Component c = m_model.ElementsTore[i][j];
				if (Vision(c)) {
					c.m_show = true;
				} else {
					c.m_show = false;
				}
			}
			
		}
	}
	
	
	//Fonction test temporaire
	public void MoveR(){
		this.m_x+=32;
	}
	public void MoveL(){
		this.m_x-=32;
	}
	public void MoveN(){
		this.m_y-=32;
	}
	public void MoveS(){
		this.m_y+=32;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
