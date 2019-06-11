package map_creator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import LurkInTheShadow.Component;
import LurkInTheShadow.Model;

public class Fleche extends Component{
	public Fleche(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int screen) {
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
	}
	
	public void Coordonnees(){
		if(m_model.perso1.m_x<1024 && m_model.perso1.m_y<768){
			this.m_x=50;
			this.m_y=40;
		}
		if(m_model.perso1.m_x>=1024 && m_model.perso1.m_y<768){
			this.m_x=125;
			this.m_y=30;
		}
		if(m_model.perso1.m_x<1024 && m_model.perso1.m_y>=768){
			this.m_x=100;
			this.m_y=110;
		}
		if(m_model.perso1.m_x>=1024 && m_model.perso1.m_y>=768){
			this.m_x=160;
			this.m_y=90;
		}

	}
	
	public void step (long now){
		if (this.m_idx==123){
			this.m_idx=124;
		}
		else{
			this.m_idx=123;
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paintMiniMap(g);
	}
}
