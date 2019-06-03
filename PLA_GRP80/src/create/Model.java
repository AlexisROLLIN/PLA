/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package create;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {

	BufferedImage Sprite;
	map m;
	Personnage perso1;

	int nbElements;
	LinkedList<Element> ElementsM1;
	LinkedList<Element> ElementsM2;
	LinkedList<Element> ElementsM3;
	LinkedList<Element> ElementsM4;

	public Model() {

		loadSprites();
		nbElements = 0;
		ElementsM1 = new LinkedList<Element>();
		ElementsM2 = new LinkedList<Element>();
		ElementsM3 = new LinkedList<Element>();
		ElementsM4 = new LinkedList<Element>();
		Options.SHOW_M1 = true;
		m = new map(44, 64, this);
		perso1 = new Personnage(this, 100, Sprite, 10, 9, 32, 32, 1F, 1);
		perso1.m_idx = 25;

	}

	@Override
	public void shutdown() {

	}

	/**
	 * Simulation step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {

	}

	private void loadSprites() {
		/*
		 * Cowboy with rifle, western style; png; 48x48 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */

		/*
		 * File imageFile = new File("game.sample/sprites/winchester.png"); try { Sprite
		 * = ImageIO.read(imageFile); } catch (IOException ex) { ex.printStackTrace();
		 * System.exit(-1); } /* Long explosion set; png file; 64x64 px sprite size
		 * Krasi Wasilev ( http://freegameassets.blogspot.com)
		 */
		File imageFile = new File("src/create/testSprites.png");
		try {
			Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

}
