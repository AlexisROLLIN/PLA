package tests;

import java.util.List;

import javax.imageio.ImageIO;

import interpreter.*;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import game.GameModel;

public class Model extends GameModel {

	BufferedImage m_testSprite;
	BufferedImage m_background;
	Character m_main;
	public  Shooter m_shooter;
	public Mage m_mage;
	public Warrior m_warrior;
	public LinkedList<Component> components;
	public LinkedList<String> touches;
	public IAutomaton[] TabAuto = new IAutomaton[4];
	public Component Main;
	
	public char Cgmt;

	public Model() throws Interpreter_Exception, Exception {
		loadSprites();
		this.components = new LinkedList();
		this.touches = new LinkedList();
		IAutomaton spawn;
		IAutomaton spawn1;
		IAutomaton spawn2;
		IAutomaton spawn3;
		IAutomaton spawn4;
		IAutomaton spawn5;
		IAutomaton spawn6;
		IAutomaton spawn7;
		IAutomaton spawn8;
		IAutomaton spawn9;

		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("Src/Automate/Automates"));
		IAI_Definitions iai_def = ai_def.make();
		spawn = iai_def.automatas.get(0);
		spawn1 = iai_def.automatas.get(1);
		spawn2 = iai_def.automatas.get(2);
		spawn3 = iai_def.automatas.get(3);
		spawn4 = iai_def.automatas.get(4);
		spawn5 = iai_def.automatas.get(5);
		spawn6 = iai_def.automatas.get(6);
		spawn7 = iai_def.automatas.get(7);
		spawn8 = iai_def.automatas.get(8);
		spawn9 = iai_def.automatas.get(9);

		// Shooter

		int[] spritesGoUpShooter = { 79, 78, 80 };
		int[] spritesGoDownShooter = { 82, 81, 83 };
		int[] spritesGoLeftShooter = { 76, 73, 77 };
		int[] spritesGoRightShooter = { 72, 68, 71 };

		m_shooter = new Shooter(this, 200, 400, 32, 32, 3F, m_testSprite, 10, 9, 81, spritesGoUpShooter,
				spritesGoDownShooter, spritesGoLeftShooter, spritesGoRightShooter, true, 200, 1, 0);
		m_mage = new Mage(this, 104, 304, 32, 32, 3F, m_testSprite, 10, 9, 45, spritesGoUpShooter, spritesGoDownShooter,
				spritesGoLeftShooter, spritesGoRightShooter, true, 200, 1, 0);
		m_warrior = new Warrior(this, 8, 208, 32, 32, 3F, m_testSprite, 10, 9, 49, spritesGoUpShooter, spritesGoDownShooter,
				spritesGoLeftShooter, spritesGoRightShooter, true, 200, 1, 0);
		this.components.add(m_shooter);
		this.components.add(m_mage);
		this.components.add(m_warrior);
		TabAuto[0]= spawn;
		TabAuto[1]= spawn1;
		TabAuto[2]= spawn2;
		TabAuto[3]= spawn3;

		m_shooter.setAutomate(spawn);
		m_mage.setAutomate(spawn8);
		m_warrior.setAutomate(spawn9);
		Main = m_shooter;
		

	}

	@Override
	public void step(long now) {

		Iterator<Component> iter = this.components.iterator();

		while (iter.hasNext()) {
			try {
				iter.next().step(now);

			} catch (Interpreter_Exception e) {
			}
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
