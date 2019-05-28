package LurkInTheShadow;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Component {
	int m_w, m_h;
	int m_x, m_y;
	Model model;
	int id_x;
	boolean show;
	BufferedImage[] m_sprites;
	float m_scale;
	int m_nrows, m_ncols;
	
	public Component(Model m, int x, int y, int w, int h, int id_x,int rows,int col,float scale,BufferedImage sprite,boolean show) {
		this.m_x =x;
		this.m_y = y;
		this.m_w = w;
		this.m_h = h;
		this.model=m;
		this.id_x = id_x;
		this.show = show;
	}
	
	public void pop() {}
	
	public void wizz() {}
	
	public void egg() {}
	
	public void hit() {}
	
	public void paint() {}
	

}
