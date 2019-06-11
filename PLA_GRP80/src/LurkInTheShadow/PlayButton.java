package LurkInTheShadow;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class PlayButton extends AbstractAction {
	GameMenu m_fenetre;

	public PlayButton(GameMenu fenetre, String texte) {
		super(texte);
		
		m_fenetre = fenetre;
	}

	public void actionPerformed(ActionEvent e) {
		GameMain.main(null);
	}

}
