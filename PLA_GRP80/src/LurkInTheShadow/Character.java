package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Character extends Component {
	int HP;
	int intensity;
//	int[] m_spritesGoUp;
//	int[] m_spritesGoLeft;
//	int[] m_spritesGoDown;
//	int[] m_spritesGoRight;
	boolean m_goUp;
	boolean m_goLeft;
	boolean m_goDown;
	boolean m_goRight;

	public Character(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col,
			int id_x, boolean show, int HP, int intensity) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		this.HP = HP;
		this.intensity = intensity;
	}

	public void rightOn() {
		m_goRight = true;
	}

	public void leftOn() {
		m_goLeft = true;
	}

	public void upOn() {
		m_goUp = true;
	}

	public void downOn() {
		m_goDown = true;
	}

	public void rightOff() {
		m_goRight = false;
	}

	public void leftOff() {
		m_goLeft = false;
	}

	public void upOff() {
		m_goUp = false;
	}

	public void downOff() {
		m_goDown = false;
	}

	public void hit(int dir) {
	}
}
