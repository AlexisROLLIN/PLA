package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

public class Warrior extends Ally {

	boolean defense;

	public Warrior(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int id_x,
			boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);
		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.TEAM;
		splitSprite();
		defense = false;
		this.life = 100;
		this.power = 50;
	}

	public boolean hit(IDirection d) {
		if (this.defense == false) {
			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
				Iterator<Monster> iter = this.m_model.monstres.iterator();
				Component tmp;
				while (iter.hasNext()) {
					tmp = iter.next();
					if (tmp.is_in_case(this.m_x, this.m_y - 32)) {
						tmp.life -= this.power;
						return true;
					}
				}
				m_dir = IDirection.NORTH;
				System.out.println("Attaque au Nord\n");
			}

			else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
				Iterator<Monster> iter = this.m_model.monstres.iterator();
				Component tmp;
				while (iter.hasNext()) {
					tmp = iter.next();
					if (tmp.is_in_case(this.m_x, this.m_y + 32)) {
						tmp.life -= this.power;
						return true;
					}

				}
				m_dir = IDirection.SOUTH;
				System.out.println("Attaque au Sud \n");
			}

			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
				Iterator<Monster> iter = this.m_model.monstres.iterator();
				Component tmp;
				while (iter.hasNext()) {
					tmp = iter.next();
					if (tmp.is_in_case(this.m_x + 32, this.m_y)) {
						tmp.life -= this.power;

						return true;
					}

				}
				m_dir = IDirection.WEST;
				System.out.println("Attaque à l'Ouest \n");
			}

			else {
				Iterator<Monster> iter = this.m_model.monstres.iterator();
				Component tmp;
				while (iter.hasNext()) {
					tmp = iter.next();
					if (tmp.is_in_case(this.m_x, this.m_y - 32)) {
						tmp.life -= this.power;
						return true;
					}

				}
				m_dir = IDirection.EAST;
				System.out.println("Attaque à l'Est \n");
			}
		}

		return false;
	}

	@Override
	public boolean pop(IDirection d) {

		if (this.defense == false) {
			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {

				int i;
				int possibilite = 0;
				while (/* 1 != this.m_model.map.tab[this.m_y / 32 - possibilite][(this.m_x / 32)] && */ possibilite < 6) {
					possibilite++;
				}

				for (i = 0; i < possibilite; i++) {
					this.move(d);
					Iterator<Monster> iter = this.m_model.monstres.iterator();
					Monster tmp;
					while (iter.hasNext()) {
						tmp = iter.next();
						if (this.Collision(tmp)) {

							this.m_model.monstres.remove(tmp);
							this.m_model.components.remove(tmp);
						}

					}

				}

				m_dir = IDirection.NORTH;
				System.out.println("Pop au Nord\n");

			} else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
				int i;
				int possibilite = 0;
				while (/* 1 != this.m_model.map.tab[this.m_y / 32 + possibilite][(this.m_x / 32)] && */ possibilite < 6) {
					possibilite++;
				}

				for (i = 0; i < possibilite; i++) {
					this.move(d);
					Iterator<Monster> iter = this.m_model.monstres.iterator();
					Monster tmp;
					while (iter.hasNext()) {
						tmp = iter.next();
						if (this.Collision(tmp)) {
							this.m_model.monstres.remove(tmp);
							this.m_model.components.remove(tmp);
						}

					}

				}
				m_dir = IDirection.SOUTH;
				System.out.println("Pop au Sud \n");
			}

			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
				int i;
				int possibilite = 0;
				while (/* 1 != this.m_model.map.tab[this.m_y / 32][(this.m_x / 32 - possibilite)] && */ possibilite < 6) {
					possibilite++;
				}

				for (i = 0; i < possibilite; i++) {
					this.move(d);
					Iterator<Monster> iter = this.m_model.monstres.iterator();
					Monster tmp;
					while (iter.hasNext()) {
						tmp = iter.next();
						if (this.Collision(tmp)) {
							this.m_model.monstres.remove(tmp);
							this.m_model.components.remove(tmp);
						}

					}

				}
				m_dir = IDirection.WEST;
				System.out.println("Pop à l'Ouest \n");

			}

			else {
				int i;
				int possibilite = 0;
				while (/* 1 != this.m_model.map.tab[this.m_y / 32][(this.m_x / 32 + possibilite)] && */ possibilite < 6) {
					possibilite++;
				}

				for (i = 0; i < possibilite; i++) {
					this.move(d);
					Iterator<Monster> iter = this.m_model.monstres.iterator();
					Monster tmp;
					while (iter.hasNext()) {
						tmp = iter.next();
						if (this.Collision(tmp)) {
							this.m_model.monstres.remove(tmp);
							this.m_model.components.remove(tmp);

						}

					}

				}
				m_dir = IDirection.EAST;
				System.out.println("Pop à l'Est \n");
			}
		}
		return false; //

	}

	public boolean wizz(IDirection d) {
		if (this.defense == false) {
			this.defense = true;
			this.life = 150;
		}

		else {
			this.defense = false;
			this.life = 100;
		}

		return true;
	}

	public boolean move(IDirection d) { // Graphiques non geres
		if (defense == false) {
			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
				m_y -= speed;
				this.m_idx = 50;
				m_dir = IDirection.NORTH;
				System.out.println("Avance au Nord\n");

				if (m_type == IType.PLAYER) {
					m_model.map.iViewport--;
					if (m_model.mainPlayed.m_y < 0) {
						m_model.mainPlayed.m_y = 1504;
						m_model.map.iViewport = 59;
					}
				}
			}

			else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
				m_y += speed;
				this.m_idx = 47;
				m_dir = IDirection.SOUTH;
				System.out.println("Avance au Sud \n");

				if (m_type == IType.PLAYER) {
					m_model.map.iViewport++;
					if (m_model.mainPlayed.m_y > 1504) {
						m_model.mainPlayed.m_y = 0;
						m_model.map.iViewport = 12;
					}
				}
			}

			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
				m_x -= speed;
				this.m_idx = 56;
				m_dir = IDirection.WEST;
				System.out.println("Avance à l'Ouest \n");

				if (m_type == IType.PLAYER) {
					m_model.map.jViewport--;
					if (m_model.mainPlayed.m_x < 0) {
						m_model.mainPlayed.m_x = 2016;
						m_model.map.jViewport = 79;
					}
				}
			}

			else {
				m_x += speed;
				this.m_idx = 62;
				m_dir = IDirection.EAST;
				System.out.println("Avance à l'Est \n");

				if (m_type == IType.PLAYER) {
					m_model.map.jViewport++;
					if (m_model.mainPlayed.m_x > 2016) {
						m_model.mainPlayed.m_x = 0;
						m_model.map.jViewport = 16;
					}
				}
			}
			return true; // L'action s'est bien déroulée
		}
		return false;
	}
	
	

}
