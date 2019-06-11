package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import interpreter.IAutomaton;

public class Sol extends Component {
	

<<<<<<< HEAD
	public Sol(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight()*(int)scale, sprite.getWidth()*(int)scale, scale, id_x, show);
		m_show = true;
		automate=model.floor;
		m_type=IType.VOID;
=======
	public Sol(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale,int screen) {
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
		m_idx = 0;
		splitSprite();
>>>>>>> tmpGaetan
	}



}