package LurkInTheShadow;

import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Mage extends Ally {

	public Mage(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);

		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.TEAM;
		splitSprite();
	}


}
