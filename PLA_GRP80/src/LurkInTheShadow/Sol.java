package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import interpreter.IAutomaton;

public class Sol extends Component {
	
	public Sol(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight()*(int)scale, sprite.getWidth()*(int)scale, scale, id_x, show);
		m_show = true;
		automate=model.floor;
		m_type=IType.VOID;
	}



}