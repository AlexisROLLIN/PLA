package LurkInTheShadow;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameView;
import edu.ricm3.game.Options;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.black;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	BufferedImage background;
	boolean tore;
	
	

	public View(Model m) {
		m_model = m;
	}

	public void step(long now) {
		m_model.step(now);
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, null);

		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();
<<<<<<< HEAD

		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());

		Iterator<Component> iter = this.m_model.components.iterator();
		while (iter.hasNext()) {
			Component c = iter.next();

			
			if (c.m_show) {
				c.paint(g);
=======
		
		
//		g.setColor(m_background);
//		g.fillRect(0, 0, getWidth(), getHeight());

		File imageFile = new File("src/map_creator/testFond.png");
		try {
			background = ImageIO.read(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(background, 0, 0, 1024, 768, null);
		
		
		int i1=m_model.map.iViewport;	//limite i
		int j1=m_model.map.jViewport;	//limite j

		int nbCasei=24;
		int nbCasej=32;

		Component c;
		

		
		for(int i=0;i<nbCasei;i++){
			tore=false;
			if(i1>=48+24){
//				i1=0;
				tore=true;
			}
			if(i1<24){
//				i1=47;
				tore=true;
			}
			j1=m_model.map.jViewport;
			for(int j=0;j<nbCasej;j++){
				if(j1>=64+32){
//					j1=0;
					tore=true;
				}
				if(j1<32){
//					j1=63;
					tore=true;
				}
				
//				
				c=m_model.ElementsTore[i1][j1];
//				c=m_model.ElementsMap[i1][j1];
				if(tore==false){
					c.paint(g);
				}
				else{
//					c.paintToreR(g, nbCasej);
//					c.paintToreS(g, nbCasei);
//					c.paintToreRS(g,0,nbCasei);
					c.paintToreRS(g,nbCasej,nbCasei);
					c.paintToreRS(g,0,nbCasei);
					c.paintToreRS(g,nbCasej,0);
					c.paintToreLN(g,nbCasej,nbCasei);
					c.paintToreLN(g,0,nbCasei);
					c.paintToreL(g,nbCasej,0);
					c.paint(g);
				}
				
				j1++;
>>>>>>> tmpGaetan
			}
			i1++;
		}
		
		
//		Iterator<Component> iter = this.m_model.ElementsViewPort.iterator();
//
//		while (iter.hasNext()) {
//			Component c = iter.next();
//			//if (!(c instanceof Personnage) && c.m_show) {
//				c.paint(g);
//			//}
//		}




//		if (Options.SHOW_M1) {
//			Iterator<Component> iter = this.m_model.ElementsM1.iterator();
//			Component c = iter.next();
//			while (iter.hasNext()) {
//				if (!(c instanceof Personnage) && c.m_show) {
//					c.paint(g);
//				}
//				c=iter.next();
//			}
//		}
//		if (Options.SHOW_M2) {
//			Iterator<Component> iter = this.m_model.ElementsM2.iterator();
//			Component c = iter.next();
//			while (iter.hasNext()) {
//				if (!(c instanceof Personnage) && c.m_show) {
//					c.paint(g);
//				}
//				c=iter.next();
//			}
//		}
//		if (Options.SHOW_M3) {
//			Iterator<Component> iter = this.m_model.ElementsM3.iterator();
//			Component c = iter.next();
//			while (iter.hasNext()) {
//				if (!(c instanceof Personnage) && c.m_show) {
//					c.paint(g);
//				}
//				c=iter.next();
//			}
//		}
//		if (Options.SHOW_M4) {
//			Iterator<Component> iter = this.m_model.ElementsM4.iterator();
//			Component c = iter.next();
//			while (iter.hasNext()) {
//				if (!(c instanceof Personnage) && c.m_show) {
//					c.paint(g);
//				}
//				c=iter.next();
//			}
//		}

	}

}
