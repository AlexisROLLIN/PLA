package tests;
import java.util.List;

import javax.imageio.ImageIO;

import interpreter.*;
import interpreter.ICondition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import game.GameModel;

public class Model extends GameModel{

	BufferedImage m_testSprite;
	BufferedImage m_background;
	Character m_main;
	Shooter m_shooter;
	Mage m_mage;
	public LinkedList<Component> components;
	public LinkedList<String> touches;
	
    
	public Model() {
		loadSprites();
		this.components = new LinkedList();
		this.touches = new LinkedList();
		
		
		IState etat1=new IState("E1");
		
		ICondition cond1=new ICondition.ICell("N","@");
		ICondition cond2=new ICondition.ICell("S","@");
		ICondition cond3=new ICondition.ICell("E","@");
		ICondition cond4=new ICondition.ICell("W","@");
		ICondition cond5=new ICondition.IKey("N");
		ICondition cond6=new ICondition.IKey("S");
		ICondition cond7=new ICondition.IKey("E");
		ICondition cond8=new ICondition.IKey("W");
		ICondition cond9=new ICondition.IAnd(cond1, cond5);
		ICondition cond10=new ICondition.IAnd(cond2, cond6);
		ICondition cond11=new ICondition.IAnd(cond3, cond7);
		ICondition cond12=new ICondition.IAnd(cond4, cond8);
		
		
		
		IAction act1=new IAction.IMove("N");
		IAction act2=new IAction.IMove("S");
		IAction act3=new IAction.IMove("E");
		IAction act4=new IAction.IMove("W");
		
		ITransition trans1=new ITransition(cond9,act1,etat1);
		ITransition trans2=new ITransition(cond10,act2,etat1);
		ITransition trans3=new ITransition(cond11,act3,etat1);
		ITransition trans4=new ITransition(cond12,act4,etat1);
		
		List<ITransition> transList1=new LinkedList<ITransition>();
		transList1.add(trans1);
		transList1.add(trans2);
		transList1.add(trans3);
		transList1.add(trans4);
		
		
		IBehaviour source1=new IBehaviour(etat1,transList1);
		
		List<IBehaviour> behaviourList=new LinkedList<IBehaviour>();
		behaviourList.add(source1);
		
		
		IAutomaton auto=new IAutomaton(etat1,behaviourList);

		

		// Shooter

		int[] spritesGoUpShooter = { 79, 78, 80 };
		int[] spritesGoDownShooter = { 82, 81, 83 };
		int[] spritesGoLeftShooter = { 76, 73, 77 };
		int[] spritesGoRightShooter = { 72, 68, 71 };

		m_shooter = new Shooter(this, 200, 400, 32, 32, 3F, m_testSprite, 10, 9, 81, spritesGoUpShooter,
				spritesGoDownShooter, spritesGoLeftShooter, spritesGoRightShooter, true, 200, 1, 0);
		m_mage = new Mage(this, 200, 400, 32, 32, 3F, m_testSprite, 10, 9, 46, spritesGoUpShooter,
				spritesGoDownShooter, spritesGoLeftShooter, spritesGoRightShooter, true, 200, 1, 0);
		this.components.add(m_shooter);
		this.components.add(m_mage);
		
		
		m_shooter.setAutomate(auto);

		

		
	}

	@Override
	public void step(long now) {

		Iterator<Component> iter = this.components.iterator();
		while (iter.hasNext()) {
			try {
			iter.next().step(now);
			}catch(Interpreter_Exception e) {}
		}
	}

	private void loadSprites() {
		File imageFile = new File("Src/Sprites/testSprites.png");

		try {
			m_testSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("Src/Sprites/logo.png");

		try {
			m_background = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
	

	@Override
	public void shutdown() {
	}
}
