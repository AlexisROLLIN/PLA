package create;

public class map {
	int length;
	int width;
	int[][] tab;
	int[][] m1, m2, m3, m4;
	int realLen = this.length / 2;
	int realWid = this.width / 2;
	Model model;

	map(int length, int width, Model model) {
		this.model = model;
		this.length = length;
		this.width = width;
		tab = new int[length][width];
		if (this.length % 2 == 1) {
			realLen++;
		}
		if (this.width % 2 == 1) {
			realWid++;
		}
		initialisation();
		creuser();
		division();
		repartition();
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

		//m_x = (int) (Math.random() * 1000 % length);
		//m_y = (int) (Math.random() * 1000 % width);

		m_x = 1;
		m_y = 1;
		for(i = 1 ; i < length -1 ;i++ ) {
			for(int j = width/2 -2 ; j < width/2 +2 ;j++ ) {
				tab[i][j] = 0;
			}
		}
		for(i = 1 ; i < width -1 ;i++ ) {
			for(int j = length/2 -2 ; j < length/2 +2 ;j++ ) {
				tab[j][i] = 0;
			}
		}
		
		i=0;
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

	void division() {
		int realLen = this.length / 2;
		int realWid = this.width / 2;
		if (this.length % 2 == 1) {
			realLen++;
		}
		if (this.width % 2 == 1) {
			realWid++;
		}
		m1 = new int[this.length / 2][this.width / 2];
		m2 = new int[this.length / 2][realWid];
		m3 = new int[realLen][this.width / 2];
		m4 = new int[realLen][realWid];

		for (int i = 0; i < this.length / 2; i++) {
			for (int j = 0; j < this.width / 2; j++) {
				m1[i][j] = tab[i][j];
			}
		}
		int k = 0;
		int p = 0;
		for (int i = realLen; i < this.length; i++) {
			for (int j = realWid; j < this.width; j++) {
				m4[k][p] = tab[i][j];
				p++;
			}
			k++;
			p = 0;
		}

		k = 0;
		p = 0;

		for (int i = 0; i < this.length / 2; i++) {
			for (int j = realWid; j < this.width; j++) {
				m2[k][p] = tab[i][j];
				p++;
			}
			k++;
			p = 0;
		}
		k = 0;
		p = 0;

		for (int i = realLen; i < this.length; i++) {
			for (int j = 0; j < this.width / 2; j++) {
				m3[k][p] = tab[i][j];
				p++;
			}
			k++;
			p = 0;
		}

	}

	void repartition() {
		for (int i = 0; i < length / 2; i++) {
			for (int j = 0; j < width / 2; j++) {
				if (m1[i][j] == 1) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 1);
					m.m_idx = 4;

				}
				if (m2[i][j] == 1) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 2);
					m.m_idx = 4;

				}
				if (m3[i][j] == 1) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 3);
					m.m_idx = 4;

				}
				if (m4[i][j] == 1) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 4);
					m.m_idx = 4;

				}
				if (m1[i][j] == 0) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 1);
					m.m_idx = 86;

				}
				if (m2[i][j] == 0) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 2);
					m.m_idx = 86;

				}
				if (m3[i][j] == 0) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 3);
					m.m_idx = 86;

				}
				if (m4[i][j] == 0) {
					obstacle m = new obstacle(this.model, 100, this.model.Sprite, 10, 9, 32 * j, 32 * i, 1F, 4);
					m.m_idx = 86;

				}

			}
		}

	}
	

}