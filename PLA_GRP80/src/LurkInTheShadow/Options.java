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
package LurkInTheShadow;

import javax.swing.JComboBox;
import javax.swing.Spring;

public class Options {
	public static final int W_WIDTH = 1024;
	public static final int W_HEIGHT = 768;

	public static final int NB_I = 3;

	public static final int I_WIDTH = W_WIDTH / NB_I;
	public static final int IHM_WIDTH = 64;

	public static final int PW_HEIGHT = W_HEIGHT - IHM_WIDTH;
	
	public static final float MAX_DURABILITY = 9000; // 3 min
	
	public static final int MAX_SCORE = Integer.MAX_VALUE;
	
	public static Spring AUTOMATA_WARRIOR;
	public static Spring AUTOMATA_SHOOTER;
	public static Spring AUTOMATA_MAGE;
	public static Spring AUTOMATA_FIREBALL;
	public static Spring AUTOMATA_BULLET;
	public static Spring AUTOMATA_MONSTER;
	public static Spring AUTOMATA_QUEEN;
	public static Spring AUTOMATA_OBST;
	public static Spring AUTOMATA_FLOOR;
	public static Spring AUTOMATA_ITEMS;
}
