package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import interpreter.*;

public class Shooter extends Ally {
boolean sniper;
int speedProj;
int lastIdx;
	public Shooter(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int id_x,
			boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight() * (int) scale, sprite.getWidth() * (int) scale,
				scale, id_x, show);
		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.PLAYER;
		sniper = false;
		speedProj = 32;
		this.lastIdx = m_idx;
		splitSprite();
	}

	public boolean move(IDirection d) { // Graphiques non geres
		if(sniper == false) {
			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
				tmp_m_y = m_y;
				m_y -= speed;
				this.m_idx = 78;
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
				tmp_m_y = m_y;
				m_y += speed;
				this.m_idx = 81;
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
				tmp_m_x = m_x;
				m_x -= speed;
				this.m_idx = 73;
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
				tmp_m_x = m_x;
				m_x += speed;
				
				this.m_idx = 68;
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

	public boolean hit(IDirection d) {
		int id;
		if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
				|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) id = 22;
		else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
				|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) id = 24;
		else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
				|| (m_dir == IDirection.EAST && d == IDirection.BACK)
				|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
				|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) id = 23;
		else id = 21;
	
		m_model.componentsToAdd
				.add(new Bullet(m_model, m_sprite, m_nrows, m_ncols, m_x, m_y, m_scale, id, m_show, d, power,speedProj));
		return true;
	}
	
	public boolean pop(IDirection d) {
		if(sniper == false) {
			
			int id;
			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) id = 93;
			else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) id = 91;
			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) id = 89;
			else id = 87;
			this.lastIdx = m_idx;
			this.m_idx = id;
			this.speedProj = 64;
			sniper = true;
			
		}
		else {
			this.speedProj = 32;
			m_idx = lastIdx;
			sniper = false;
		}
		
		return true;
		
	}
	
	
	
}
