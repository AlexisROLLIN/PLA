package create;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Image;

import edu.ricm3.game.GameView;

public class View extends GameView {

  private static final long serialVersionUID = 1L;

  Color m_background = Color.gray;
  long m_last;
  int m_npaints;
  int m_fps;
  Model m_model;
  // Controller m_ctr;
  
  public View(Model m) {
    m_model = m;
    // m_ctr = c;
  }
  
  public void step(long now) {
	  
  }
  
  private void computeFPS() {
    long now = System.currentTimeMillis();
    if (now - m_last > 1000L) {
      m_fps = m_npaints;
      m_last = now;
      m_npaints = 0;
    }
    m_game.setFPS(m_fps, null);
    // m_game.setFPS(m_fps, "npaints=" + m_npaints);
    m_npaints++;
  }

  @Override
  protected void _paint(Graphics g) {
    computeFPS();
  if(Options.SHOW_M1) {
	  Iterator<Element> iter = this.m_model.ElementsM1.iterator();
	    Element tmp;
	    while(iter.hasNext()) {
	    	tmp = iter.next();
	    	tmp.paint(g);
	    	
	    }
  }
  if(Options.SHOW_M2) {
	  Iterator<Element> iter = this.m_model.ElementsM2.iterator();
	    Element tmp;
	    while(iter.hasNext()) {
	    	tmp = iter.next();
	    	tmp.paint(g);
	    	
	    }
  }
  if(Options.SHOW_M3) {
	  Iterator<Element> iter = this.m_model.ElementsM3.iterator();
	    Element tmp;
	    while(iter.hasNext()) {
	    	tmp = iter.next();
	    	tmp.paint(g);
	    	
	    }
  }
  if(Options.SHOW_M4) {
	  Iterator<Element> iter = this.m_model.ElementsM4.iterator();
	    Element tmp;
	    while(iter.hasNext()) {
	    	tmp = iter.next();
	    	tmp.paint(g);
	    	
	    }
  }
 
      Personnage perso1 = m_model.perso1;
        perso1.paint(g);
    
    
    

   /*Image img = m_model.m_BG;
    g.drawImage(img,0,0,1024,764,null); 
 
    if (Options.SHOW_COWBOYS) {
      Cowboy cowboys = m_model.main_cowboy;
        cowboys.paint(g);
    }
    Image img1 = m_model.m_obstacle;
    g.drawImage(img1,200,300,90,30,null);
    g.drawImage(img1,500,400,90,30,null);
    g.drawImage(img1,800,500,90,30,null);
   
  /*  	Image img1 = m_model.m_obstacle1;
    g.drawImage(img1,245,550,50,50,null);
  */
    
    
    
    	
    	
    
 /*   
  if(this.m_model.tir.tirer == true  ) {

  	Projectile ballon = m_model.tir;
      ballon.paint(g);
  }
  if(Options.END_GAME && Options.LOST) {
	  	Image img4 = m_model.gameOver;
	    g.drawImage(img4,0,0,1024,764,null); 
  }
  
  if(Options.END_GAME && !Options.LOST) {
	  	Image img4 = m_model.youWin;
	    g.drawImage(img4,0,0,1024,764,null); 
}
    
    /*Obstacle carre = m_model.Sol;
    carre.paint(g);*/
   /* Image img2 = m_model.vert;
    g.drawImage(img2,0,600,1024,40,null);*/
    
  }
  
}
