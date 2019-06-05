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

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import edu.ricm3.game.GameController;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {

	Model m_model;
	View m_view;
	Button m_strobesOn;
	Button m_plus, m_minus;

	char buffer = 'a';

	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		m_model.step(now);
		m_view.step(now);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		int i = 0;
		;
		int j = 0;
		if (Options.SHOW_M1) {
			i = 0;
			j = 0;
		}
		if (Options.SHOW_M2) {
			i = 0;
			j = this.m_model.m.width / 2;
		}
		if (Options.SHOW_M3) {
			i = this.m_model.m.length / 2;
			j = 0;
		}
		if (Options.SHOW_M4) {
			i = this.m_model.m.length / 2;
			j = this.m_model.m.width / 2;
		}
		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			if (this.m_model.perso1.m_x < (this.m_model.m.width / 2) * 31
					&& this.m_model.m.tab[(this.m_model.perso1.m_y) / 32 + i][(this.m_model.perso1.m_x) / 32 + 1
							+ j] != 1) {
				this.m_model.perso1.m_x = this.m_model.perso1.m_x + 32;
				this.m_model.perso1.m_idx = 37;
			} else if (this.m_model.perso1.m_x == (this.m_model.m.width / 2) * 31) {
				this.m_model.perso1.m_x = 0;
				if (Options.SHOW_M1) {
					Options.SHOW_M1 = false;
					Options.SHOW_M2 = true;
					this.m_model.perso1.screen = 2;
					this.m_model.ElementsM2.add(this.m_model.perso1);
					this.m_model.ElementsM1.remove(this.m_model.perso1);

				}
				if (Options.SHOW_M3) {
					Options.SHOW_M3 = false;
					Options.SHOW_M4 = true;
					this.m_model.perso1.screen = 4;
					this.m_model.ElementsM4.add(this.m_model.perso1);
					this.m_model.ElementsM3.remove(this.m_model.perso1);
				}
			}
		}

		if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
			if (this.m_model.perso1.m_y > 0) {
				if (this.m_model.m.tab[(this.m_model.perso1.m_y / 32) - 1 + i][(this.m_model.perso1.m_x) / 32
						+ j] != 1) {
					this.m_model.perso1.m_y = this.m_model.perso1.m_y - 32;
				}

			} else {
				this.m_model.perso1.m_y = ((this.m_model.m.length / 2) - 1) * 32;
				if (Options.SHOW_M3) {
					Options.SHOW_M3 = false;
					Options.SHOW_M1 = true;
					this.m_model.perso1.screen = 1;
					this.m_model.ElementsM1.add(this.m_model.perso1);
					this.m_model.ElementsM3.remove(this.m_model.perso1);
				}
				if (Options.SHOW_M4) {
					Options.SHOW_M4 = false;
					Options.SHOW_M2 = true;
					this.m_model.perso1.screen = 2;
					this.m_model.ElementsM2.add(this.m_model.perso1);
					this.m_model.ElementsM4.remove(this.m_model.perso1);
				}
			}

		}
		if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			{
				if (this.m_model.m.tab[(this.m_model.perso1.m_y / 32) + 1 + i][(this.m_model.perso1.m_x) / 32
						+ j] != 1) {
					if (this.m_model.perso1.m_y < (this.m_model.m.length / 2) * 30)
						this.m_model.perso1.m_y = this.m_model.perso1.m_y + 32;
					else {
						this.m_model.perso1.m_y = 0;
						if (Options.SHOW_M1) {
							Options.SHOW_M1 = false;
							Options.SHOW_M3 = true;
							this.m_model.perso1.screen = 3;
							this.m_model.ElementsM3.add(this.m_model.perso1);
							this.m_model.ElementsM1.remove(this.m_model.perso1);
						}
						if (Options.SHOW_M2) {
							Options.SHOW_M2 = false;
							Options.SHOW_M4 = true;
							this.m_model.perso1.screen = 4;
							this.m_model.ElementsM4.add(this.m_model.perso1);
							this.m_model.ElementsM2.remove(this.m_model.perso1);
						}
					}
				}
			}

		}

		if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
			if (this.m_model.perso1.m_x > 0
					&& this.m_model.m.tab[(this.m_model.perso1.m_y) / 32 + i][(this.m_model.perso1.m_x) / 32 - 1
							+ j] != 1) {
				this.m_model.perso1.m_x = this.m_model.perso1.m_x - 32;
			} else if (this.m_model.perso1.m_x == 0) {
				this.m_model.perso1.m_x = (this.m_model.m.width / 2) * 31;
				if (Options.SHOW_M4) {
					Options.SHOW_M4 = false;
					Options.SHOW_M3 = true;
					this.m_model.perso1.screen = 3;
					this.m_model.ElementsM3.add(this.m_model.perso1);
					this.m_model.ElementsM4.remove(this.m_model.perso1);
				}
				if (Options.SHOW_M2) {
					Options.SHOW_M2 = false;
					Options.SHOW_M1 = true;
					this.m_model.perso1.screen = 1;
					this.m_model.ElementsM1.add(this.m_model.perso1);
					this.m_model.ElementsM2.remove(this.m_model.perso1);
				}
			}

		}

		if (e.getKeyChar() == '1') {
			Options.SHOW_M1 = true;
			Options.SHOW_M2 = false;
			Options.SHOW_M3 = false;
			Options.SHOW_M4 = false;
		} else if (e.getKeyChar() == '2') {
			Options.SHOW_M1 = false;
			Options.SHOW_M2 = true;
			Options.SHOW_M3 = false;
			Options.SHOW_M4 = false;
		} else if (e.getKeyChar() == '3') {
			Options.SHOW_M1 = false;
			Options.SHOW_M2 = false;
			Options.SHOW_M3 = true;
			Options.SHOW_M4 = false;
		} else if (e.getKeyChar() == '4') {
			Options.SHOW_M1 = false;
			Options.SHOW_M2 = false;
			Options.SHOW_M3 = false;
			Options.SHOW_M4 = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
	}

	/*
	 * public void notifyVisible() { Container cont = new Container();
	 * cont.setLayout(new FlowLayout());
	 */

	/*
	 * m_cowboysOn = new Button("Cowboys"); m_cowboysOn.addActionListener(this);
	 * cont.add(m_cowboysOn);
	 * 
	 * 
	 * 
	 */

	// file = new File("game.sample/sprites/Runaway-Food-Truck.wav");
	/*
	 * try { m_player = new Music(file); cont.add(m_player.getControls()); } catch
	 * (Exception ex) { } m_game.addSouth(cont); }
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();

	}

	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub

	}
}
