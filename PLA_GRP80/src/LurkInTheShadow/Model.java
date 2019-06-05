package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import edu.ricm3.game.GameModel;
import LurkInTheShadow.Mage;
import LurkInTheShadow.Wall;
import java.awt.Color;
import java.awt.Font;

public class Model extends GameModel {
	BufferedImage m_testSprite;
	BufferedImage m_background;
	Character m_main;
	Warrior m_warrior;
	Shooter m_shooter;
	Mage m_mage;
	Fireball m_fireball;
	Bullet m_bullet;
	Wall m_wall;
	IHM m_IHM;
	Score m_score;
	LinkedList<Component> components;

	public Model() {
		loadSprites();
		this.components = new LinkedList<Component>();

		// Warrior

		int[] spritesGoUpWarrior = { 49, 50 };
		int[] spritesGoDownWarrior = { 51, 48, 52 };
		int[] spritesGoLeftWarrior = { 60, 56, 61 };
		int[] spritesGoRightWarrior = { 66, 62, 67 };

		m_warrior = new Warrior(this, 300, 200, 32, 32, 3F, m_testSprite, 10, 9, 48, spritesGoUpWarrior,
				spritesGoDownWarrior, spritesGoLeftWarrior, spritesGoRightWarrior, true, 200, 10, 1, 0);
		this.components.add(m_warrior);

		// Shooter

		int[] spritesGoUpShooter = { 79, 78, 80 };
		int[] spritesGoDownShooter = { 82, 81, 83 };
		int[] spritesGoLeftShooter = { 76, 73, 77 };
		int[] spritesGoRightShooter = { 72, 68, 71 };

		m_shooter = new Shooter(this, 200, 400, 32, 32, 3F, m_testSprite, 10, 9, 81, spritesGoUpShooter,
				spritesGoDownShooter, spritesGoLeftShooter, spritesGoRightShooter, true, 150, 15, 1, 0);
		this.components.add(m_shooter);

		// Mage

		int[] spritesGoUpMage = { 46, 45, 47 };
		int[] spritesGoDownMage = { 43, 39, 44 };
		int[] spritesGoLeftMage = { 31, 25, 32 };
		int[] spritesGoRightMage = { 37, 38 };

		m_mage = new Mage(this, 300, 400, 32, 32, 3F, m_testSprite, 10, 9, 39, spritesGoUpMage, spritesGoDownMage,
				spritesGoLeftMage, spritesGoRightMage, true, 100, 20, 1, 0);
		this.components.add(m_mage);

		// Decor

		m_wall = new Wall(this, 400, 400, 32, 32, 3F, m_testSprite, 10, 9, 4, true);
		this.components.add(m_wall);
		m_main = m_shooter;
		
		// IHM
		
		m_IHM = new IHM(this, Options.PW_WIDTH, 0, Options.IHM_WIDTH, Options.W_HEIGHT, Color.gray);
		
		Font font = new Font("TimesRoman", Font.BOLD, 32);
		m_score = new Score(this, Options.PW_WIDTH, Options.W_HEIGHT / Options.NB_I, font);
		
		
	}

	public Character main() {
		return m_main;
	}

	public Warrior Warrior() {
		return m_warrior;
	}

	public Shooter shooter() {
		return m_shooter;
	}

	public Mage mage() {
		return m_mage;
	}

	public Bullet bullet() {
		return m_bullet;
	}

	public Fireball fireball() {
		return m_fireball;
	}

	public Wall Wall() {
		return m_wall;
	}

	@Override
	public void step(long now) {

		Iterator<Component> iter = this.components.iterator();
		while (iter.hasNext()) {
			iter.next().step(now);
		}
	}

	private void loadSprites() {
		File imageFile = new File("src/Sprites/testSprites.png");

		try {
			m_testSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("src/Sprites/logo.png");

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
