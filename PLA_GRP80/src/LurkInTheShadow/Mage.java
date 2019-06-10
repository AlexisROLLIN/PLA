package LurkInTheShadow;

import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Mage extends Ally {

	public Mage(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);

		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.TEAM;
		splitSprite();
	}

	@Override
	public boolean move(IDirection d) { //Graphiques non geres

			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
				m_y -= 32;
				m_dir = IDirection.NORTH;
				System.out.println("Avance au Nord\n");
				
				if(m_type==IType.PLAYER) {
					m_model.map.iViewport--;
					if(m_model.perso1.m_y<0){
						m_model.perso1.m_y=1536;
						m_model.map.iViewport=60;
					}
				}
				
			}

			else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
				m_y += 32;
				m_dir = IDirection.SOUTH;
				System.out.println("Avance au Sud \n");
				
				if(m_type==IType.PLAYER) {
					m_model.map.iViewport++;
					if(m_model.perso1.m_y>1536){
						m_model.perso1.m_y=0;
						m_model.map.iViewport=12;
					}
				}
			}

			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
				m_x -= 32;
				m_dir = IDirection.WEST;
				System.out.println("Avance à l'Ouest \n");
				
				if(m_type==IType.PLAYER) {
					m_model.map.jViewport--;
					if(m_model.perso1.m_x<0){
						m_model.perso1.m_x=2048;
						m_model.map.jViewport=80;
					}
				}
			}

			else {
				m_x += 32;
				m_dir = IDirection.EAST;
				System.out.println("Avance à l'Est \n");
				
				if(m_type==IType.PLAYER) {
					m_model.map.jViewport++;
					if(m_model.perso1.m_x>2048){
						m_model.perso1.m_x=0;
						m_model.map.jViewport=16;
					}
				}

			}
		return true; // L'action s'est bien déroulée
	}

}
