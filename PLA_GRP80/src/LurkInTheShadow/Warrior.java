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

	public Warrior(Model model, BufferedImage sprite, int rows, int columns, int x, int y,
			float scale, int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);
		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.TEAM;
		splitSprite();
		defense = false;
		this.life = 200;
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
				//System.out.println("Attaque au Nord\n");
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
				//System.out.println("Attaque au Sud \n");
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
				//System.out.println("Attaque à l'Ouest \n");
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
				//System.out.println("Attaque à l'Est \n");
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
							tmp.life -= 30;
						}

					}

				}

				m_dir = IDirection.NORTH;
				//System.out.println("Pop au Nord\n");

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
							tmp.life -= 30;
						}

					}

				}
				m_dir = IDirection.SOUTH;
				//System.out.println("Pop au Sud \n");
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
							tmp.life -= 30;
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
							tmp.life -= 30;

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
			this.life = 250;
		}

		else
			this.defense = false;

		return true;
	}


}
