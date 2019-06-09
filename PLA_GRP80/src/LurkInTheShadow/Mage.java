package LurkInTheShadow;

import interpreter.Interpreter_Exception;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Mage extends Ally {

	public Mage(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			int id_x, boolean show, int screen) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show, screen);

		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.TEAM;
		splitSprite();
	}

	/*public boolean move(IDirection d) {

		if (d == IDirection.NORTH) {
			m_y -= 32;
			m_dir = IDirection.NORTH;
			System.out.println("Avance au Nord\n");
		}

		else if (d == IDirection.SOUTH) {
			m_y += 32;
			m_dir = IDirection.SOUTH;
			System.out.println("Avance au Sud \n");
		}

		else if (d == IDirection.WEST) {
			m_x -= 32;
			m_dir = IDirection.WEST;
			System.out.println("Avance à l'Ouest \n");
		}

		else {
			m_x += 32;
			m_dir = IDirection.EAST;
			System.out.println("Avance à l'Est \n");
		}

		return true; // L'action s'est bien déroulée
	}*/

}
