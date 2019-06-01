package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Character extends Component {
	int maxHP;
	int HP;
	int damage;
	int intensity;
	int[] m_spritesGoUp;
	int[] m_spritesGoDown;
	int[] m_spritesGoLeft;
	int[] m_spritesGoRight;
	int id_xUp = 0;
	int id_xDown = 0;
	int id_xLeft = 0;
	int id_xRight = 0;

	public Character(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col,
			int id_x, int[] spritesGoUp, int[] spritesGoDown, int[] spritesGoLeft, int[] spritesGoRight, boolean show,
			int maxHP, int damage, int intensity) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
		this.maxHP = maxHP;
		this.HP = maxHP;
		this.damage = damage;
		this.intensity = intensity;
		this.m_spritesGoUp = spritesGoUp;
		this.m_spritesGoDown = spritesGoDown;
		this.m_spritesGoLeft = spritesGoLeft;
		this.m_spritesGoRight = spritesGoRight;
	}

	public void hit(int dir) {
	}
}
