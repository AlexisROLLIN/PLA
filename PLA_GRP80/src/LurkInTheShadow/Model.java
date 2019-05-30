package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import LurkInTheShadow.Mage;
import LurkInTheShadow.Wall;
import java.awt.Color;

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
	LinkedList<Component> components;

	public Model() {
		loadSprites();
		this.components = new LinkedList();
		m_warrior = new Warrior(this, 300, 200, 32, 32, 3F, m_testSprite, 10, 9, 48, true, 200, 1, 0);
		this.components.add(m_warrior);
		m_shooter = new Shooter(this, 200, 400, 32, 32, 3F, m_testSprite, 10, 9, 81, true, 200, 1, 0);
		this.components.add(m_shooter);
		m_mage = new Mage(this, 300, 400, 32, 32, 3F, m_testSprite, 10, 9, 39, true, 100, 1, 0);
		this.components.add(m_mage);
		m_bullet = new Bullet(this, 1F, m_testSprite, 10, 9, 21, true, 1);
		this.components.add(m_bullet);
		m_fireball = new Fireball(this, 1F, m_testSprite, 10, 9, 17, true, 1);
		this.components.add(m_fireball);
		m_wall = new Wall(this, 400, 400, 32, 32, 3F, m_testSprite, 10, 9, 4, true);
		this.components.add(m_wall);
		m_main = m_shooter;
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
