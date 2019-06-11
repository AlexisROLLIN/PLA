package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import interpreter.*;



public class Shooter extends Ally {

	public Shooter(Model model, BufferedImage sprite, int rows, int columns, int x, int y,
			float scale, int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight()*(int)scale, sprite.getWidth()*(int)scale, scale, id_x, show);
		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.PLAYER;
		splitSprite();
	}


}
