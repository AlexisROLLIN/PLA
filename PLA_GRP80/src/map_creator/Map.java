package map_creator;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import LurkInTheShadow.Component;
import LurkInTheShadow.Model;
import LurkInTheShadow.Obstacle;
import LurkInTheShadow.Sol;

public class Map {

	public int[][] tab;
	int[][] m1, m2, m3, m4;
	public int width;
	public int length;
	Model model;
	int realLen = this.length / 2;
	int realWid = this.width / 2;
	int[][] tabDecor;
	public int iViewport;
	public int jViewport;

	public Map(int length, int width, Model model) {
		this.model = model;
		this.length = length;
		this.width = width;
		tab = new int[length][width];
		tabDecor = new int[length / 2][width / 2];
		if (this.length % 2 == 1) {
			realLen++;
		}
		if (this.width % 2 == 1) {
			realWid++;
		}
		initialisation();
		creuser();
		repartition();
		firstCase();
	}

	void initialisation() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				tab[i][j] = 1;
			}
		}

	}

	void creuser() {
		int nbCasesCreuse = (length - 2) * (width - 2);
		int i = 0;
		int m_x, m_y;
		int nbAl;

		// m_x = (int) (Math.random() * 1000 % length);
		// m_y = (int) (Math.random() * 1000 % width);

		m_x = 1;
		m_y = 1;
		for (i = 0; i < length; i++) {
			for (int j = width / 2 - 2; j < width / 2 + 2; j++) {
				tab[i][j] = 0;
			}
		}
		for (i = 0; i < width; i++) {
			for (int j = length / 2 - 2; j < length / 2 + 2; j++) {
				tab[j][i] = 0;
			}
		}

		i = 0;
		while (i < nbCasesCreuse) {
			int nbSteps = (int) (Math.random() * 1000) % 5;

			nbAl = (int) (Math.random() * 1000) % 4;
			if (nbAl == 0) {
				if (m_x + nbSteps < length - 1) {
					for (int j = m_x; j < m_x + nbSteps + 1; j++) {
						tab[j][m_y] = 0;
					}
					m_x += nbSteps;
					i++;
				}

				else {
					nbAl = (int) (Math.random() % 4);
				}
			} else if (nbAl == 1)
				if (m_y + nbSteps < width - 1) {
					for (int j = m_y + 1; j < m_y + nbSteps; j++) {
						tab[m_x][j] = 0;
					}
					m_y += nbSteps;
					i++;
				} else {
					nbAl = (int) (Math.random() % 4);
				}

			else if (nbAl == 2) {
				if (m_y - nbSteps > 0) {
					for (int j = m_y; j > m_y - nbSteps; j--) {
						tab[m_x][j] = 0;
					}
					m_y -= nbSteps;
					i++;
				} else {
					nbAl = (int) (Math.random() % 4);
				}
			}

			else if (nbAl == 3) {
				if (m_x - nbSteps > 0) {
					for (int j = m_x; j >= m_x - nbSteps; j--) {
						tab[j][m_y] = 0;
					}
					m_x -= nbSteps;
					i++;
				} else {
					nbAl = (int) (Math.random() % 4);
				}

			}

		}

	}

	void afficherTab() {
		System.out.println("GRAND TABLEAU");
		System.out.println();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(" " + tab[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("1ER TABLEAU");
		System.out.println();
		for (int i = 0; i < length / 2; i++) {
			for (int j = 0; j < width / 2; j++) {
				System.out.print(m1[i][j]);
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("2EME TABLEAU");
		System.out.println();
		for (int i = 0; i < length / 2; i++) {
			for (int j = 0; j < width / 2; j++) {
				System.out.print(m2[i][j]);
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("3EME TABLEAU");
		System.out.println();
		for (int i = 0; i < length / 2; i++) {
			for (int j = 0; j < width / 2; j++) {
				System.out.print(m3[i][j]);
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("4EME TABLEAU");
		System.out.println();
		for (int i = 0; i < length / 2; i++) {
			for (int j = 0; j < width / 2; j++) {
				System.out.print(m4[i][j]);
			}
			System.out.println();
		}
	}

	public void firstCase() {
		int x = model.perso1.m_x;
		int y = model.perso1.m_y;

		// Cherche la premiere case correct
		boolean test = false;
		int i = 0;
		int j = 0;
		for (i = 0; i < length; i++) {
			for (j = 0; j < width; j++) {
				if ((j * 32 == x - 512) && (i * 32 == y - 384)) {
					test = true;
					break;
				}
			}
			if (test == true) {
				break;
			}
		}
		iViewport = i+24;//Coordonnée sur Tore
		jViewport = j+32;//Coordonnée sur Tore
	}

	public void step(long now) {

	}

	public void repartition() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				if (tab[i][j] == 1) {
					Obstacle m = new Obstacle(this.model, 100,
							this.model.Sprite, 12, 11, 32 * j, 32 * i, 0.40F, 1);
					m.m_idx = 4;
					model.ElementsMap[i][j] = m;

					model.ElementsTore[24 + i][32 + j] = m;
					if (j < 32) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j + 64 * 32,
								32 * i, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i + 24][j + 96] = mTore;

					}
					if (j >= 32) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j - 64 * 32,
								32 * i, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i + 24][j - 32] = mTore;
					}

					if (i < 24) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j,
								32 * i + 48 * 32, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i + 72][j + 32] = mTore;
					}
					if (i >= 24) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j,
								32 * i - 48 * 32, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i - 24][j + 32] = mTore;
					}
					if (j < 32 && i < 24) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j + 64 * 32,
								32 * i + 48 * 32, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i + 72][j + 96] = mTore;
					}
					if (j >= 32 && i < 24) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j - 64 * 32,
								32 * i + 48 * 32, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i + 72][j - 32] = mTore;
					}
					if (j < 32 && i >= 24) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j + 64 * 32,
								32 * i - 48 * 32, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i - 24][j + 96] = mTore;
					}
					if (j >= 32 && i >= 24) {
						Obstacle mTore = new Obstacle(this.model, 100,
								this.model.Sprite, 12, 11, 32 * j - 64 * 32,
								32 * i - 48 * 32, 0.40F, 1);
						mTore.m_idx = 4;
						model.ElementsTore[i - 24][j - 32] = mTore;
					}

				}
				if (tab[i][j] == 0) {
					Sol m = new Sol(this.model, 100, this.model.Sprite, 12, 11,
							32 * j, 32 * i, 0.40F, 2);
					m.m_idx = 86;
					model.ElementsMap[i][j] = m;

					model.ElementsTore[24 + i][32 + j] = m;
					if (j < 32) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j + 64 * 32, 32 * i, 0.40F, 2);
						mTore.m_idx = 86;
						model.ElementsTore[i + 24][j + 96] = mTore;

					}
					if (j >= 32) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j - 64 * 32, 32 * i, 0.40F, 2);
						mTore.m_idx = 86;
						model.ElementsTore[i + 24][j - 32] = mTore;
					}

					if (i < 24) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j, 32 * i + 48 * 32, 0.40F, 2);
						mTore.m_idx = 86;
						model.ElementsTore[i + 72][j + 32] = mTore;
					}
					if (i >= 24) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j, 32 * i - 48 * 32, 0.40F, 2);
						mTore.m_idx = 86;
						model.ElementsTore[i - 24][j + 32] = mTore;
					}
					if (j < 32 && i < 24) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j + 64 * 32, 32 * i + 48 * 32, 0.40F,
								2);
						mTore.m_idx = 86;
						model.ElementsTore[i + 72][j + 96] = mTore;
					}
					if (j >= 32 && i < 24) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j - 64 * 32, 32 * i + 48 * 32, 0.40F,
								2);
						mTore.m_idx = 86;
						model.ElementsTore[i + 72][j - 32] = mTore;
					}
					if (j < 32 && i >= 24) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j + 64 * 32, 32 * i - 48 * 32, 0.40F,
								2);
						mTore.m_idx = 86;
						model.ElementsTore[i - 24][j + 96] = mTore;
					}
					if (j >= 32 && i >= 24) {
						Sol mTore = new Sol(this.model, 100, this.model.Sprite,
								12, 11, 32 * j - 64 * 32, 32 * i - 48 * 32, 0.40F,
								2);
						mTore.m_idx = 86;
						model.ElementsTore[i - 24][j - 32] = mTore;
					}

				}

			}
		}

	}

}