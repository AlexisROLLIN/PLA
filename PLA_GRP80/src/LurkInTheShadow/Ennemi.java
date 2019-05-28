package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Ennemi extends Personnage{
	public Ennemi(Model m, int x, int y, int w, int h, int id_x,boolean show,int PV,int Power,int rows,int col,float scale,BufferedImage sprite) {
		super(m,  x, y, w, h, id_x,show,PV,Power,rows,col,scale,sprite);
	}
}
