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
	Shooter m_shooter;
	Mage m_mage;
	public LinkedList<Component> components;
	public LinkedList<String> touches;

	public Model() throws Interpreter_Exception, Exception {
		loadSprites();
		this.components = new LinkedList();
		this.touches = new LinkedList();
		IAutomaton spawn;
		IAutomaton spawn1;

		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("Src/Automate/Automates"));
		IAI_Definitions iai_def = ai_def.make();
		spawn = iai_def.automatas.get(0);
		spawn1 = iai_def.automatas.get(1);

		// Shooter

		int[] spritesGoUpShooter = { 79, 78, 80 };
		int[] spritesGoDownShooter = { 82, 81, 83 };
		int[] spritesGoLeftShooter = { 76, 73, 77 };
		int[] spritesGoRightShooter = { 72, 68, 71 };

		m_shooter = new Shooter(this, 200, 400, 32, 32, 3F, m_testSprite, 10, 9, 81, spritesGoUpShooter,
				spritesGoDownShooter, spritesGoLeftShooter, spritesGoRightShooter, true, 200, 1, 0);
		m_mage = new Mage(this, 200, 400, 32, 32, 3F, m_testSprite, 10, 9, 46, spritesGoUpShooter, spritesGoDownShooter,
				spritesGoLeftShooter, spritesGoRightShooter, true, 200, 1, 0);
		this.components.add(m_shooter);
		this.components.add(m_mage);

		m_shooter.setAutomate(spawn);
		m_mage.setAutomate(spawn1);

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
