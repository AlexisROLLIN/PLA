package map_creator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import LurkInTheShadow.Component;
import LurkInTheShadow.Model;

public class MiniMap extends Component {

	public MiniMap(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int screen) {
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintMiniMap(g);
	}
}
