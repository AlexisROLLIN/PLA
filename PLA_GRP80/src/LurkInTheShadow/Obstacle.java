package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Obstacle extends Component {
	
	
	
	public Obstacle(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int screen) {
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
		boolean m_show; //Explication
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}
