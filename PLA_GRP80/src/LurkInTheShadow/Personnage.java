package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Personnage extends Component{
	int PV;
	int Power;
	
	public Personnage(Model m, int x, int y, int w, int h, int id_x,boolean show,int PV,int Power,int rows,int col,float scale,BufferedImage sprite) {
		super(m, x, y, w,h, id_x,rows,col,scale,sprite,show);
		this.PV = PV;
		this.Power = Power;
	}

}
