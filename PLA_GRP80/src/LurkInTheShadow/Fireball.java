package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Fireball extends Projectile {

	public Fireball (Model model, BufferedImage sprite, int rows, int columns, int x, int y,float scale, int id_x, boolean show, IDirection dir, int power) {
		
		super(model, sprite, rows, columns, x, y, scale, id_x, show, dir, power);
		automate=m_model.fireball;

	}
}