package LurkInTheShadow;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;

class GameMenu implements ActionListener {

	JFrame m_frame = new JFrame();

	JPanel m_panelMenu = new JPanel();
	JPanel m_panelOptions = new JPanel();

	// Menu

	JButton m_newGameButton = new JButton("New Game");
	JButton m_optionsButton = new JButton("Options");
	JButton m_challengeButton = new JButton("Challenge");

	// Options

	JButton m_saveOptions = new JButton("Save");

	JComboBox m_automataWarrior;
	JComboBox m_automataShooter;
	JComboBox m_automataMage;
	JComboBox m_automataFireball;
	JComboBox m_automataBullet;
	JComboBox m_automataMonster;
	JComboBox m_automataQueen;
	JComboBox m_automataObst;
	JComboBox m_automataFloor;
	JComboBox m_automataItems;

	GameMenu() {
		prepareGUI();
		initPanelMenu();
		initPanelOptions();

		m_frame.setVisible(true);
	}

	public void prepareGUI() {
		m_frame.setTitle("Launcher: What Lurks In The Shadow ?");
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setLayout(new BorderLayout());
//		m_frame.getContentPane().setLayout(null);
		m_frame.setBounds(200, 200, 400, 400);
	}

	public void initPanelMenu() {
		m_panelMenu.setLayout(new BoxLayout(m_panelMenu, BoxLayout.PAGE_AXIS));

		JLabel label;

		label = new JLabel("Menu");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		m_panelMenu.add(label);

		m_panelMenu.add(Box.createVerticalGlue());

		m_newGameButton.setHorizontalAlignment(JLabel.CENTER);
		m_newGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		m_newGameButton.addActionListener(this);
		m_panelMenu.add(m_newGameButton);

		m_optionsButton.setHorizontalAlignment(JLabel.CENTER);
		m_optionsButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		m_optionsButton.addActionListener(this);
		m_panelMenu.add(m_optionsButton);

		m_challengeButton.setHorizontalAlignment(JLabel.CENTER);
		m_challengeButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		m_challengeButton.addActionListener(this);
		m_panelMenu.add(m_challengeButton);

		m_frame.setContentPane(m_panelMenu);
	}

	public void initPanelOptions() {

		m_panelOptions.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		JLabel label;

//		label = new JLabel("Options");
//		label.setHorizontalAlignment(JLabel.CENTER);
//		m_panelOptions.add(label, BorderLayout.NORTH);

		Object[] elements = new Object[] { "Player", "leader", "spawn1", "spawn2", "obst", "monster", "queen",
				"fireball", "bullet", "floor", "transe", "monstre_desoriente" };

		label = new JLabel("Warrior:");
		c.gridx = 0;
		c.gridy = 1;
		m_panelOptions.add(label, c);

		m_automataWarrior = new JComboBox(elements);
		m_automataWarrior.setSelectedIndex(1);
		c.gridx = 1;
		c.gridy = 1;
		m_panelOptions.add(m_automataWarrior, c);

		label = new JLabel("Shooter:");
		c.gridx = 0;
		c.gridy = 2;
		m_panelOptions.add(label, c);

		m_automataShooter = new JComboBox(elements);
		m_automataShooter.setSelectedIndex(0);
		c.gridx = 1;
		c.gridy = 2;
		m_panelOptions.add(m_automataShooter, c);

		label = new JLabel("Mage:");
		c.gridx = 0;
		c.gridy = 3;
		m_panelOptions.add(label, c);

		m_automataMage = new JComboBox(elements);
		m_automataMage.setSelectedIndex(0);
		c.gridx = 1;
		c.gridy = 3;
		m_panelOptions.add(m_automataMage, c);

		label = new JLabel("Fireball:");
		c.gridx = 0;
		c.gridy = 4;
		m_panelOptions.add(label, c);

		m_automataFireball = new JComboBox(elements);
		m_automataFireball.setSelectedIndex(7);
		c.gridx = 1;
		c.gridy = 4;
		m_panelOptions.add(m_automataFireball, c);

		label = new JLabel("Bullet:");
		c.gridx = 0;
		c.gridy = 5;
		m_panelOptions.add(label, c);

		m_automataBullet = new JComboBox(elements);
		m_automataBullet.setSelectedIndex(8);
		c.gridx = 1;
		c.gridy = 5;
		m_panelOptions.add(m_automataBullet, c);

		label = new JLabel("Monster:");
		c.gridx = 0;
		c.gridy = 6;
		m_panelOptions.add(label, c);

		m_automataMonster = new JComboBox(elements);
		m_automataMonster.setSelectedIndex(5);
		c.gridx = 1;
		c.gridy = 6;
		m_panelOptions.add(m_automataMonster, c);

		label = new JLabel("Queen:");
		c.gridx = 0;
		c.gridy = 7;
		m_panelOptions.add(label, c);

		m_automataQueen = new JComboBox(elements);
		m_automataQueen.setSelectedIndex(6);
		c.gridx = 1;
		c.gridy = 7;
		m_panelOptions.add(m_automataQueen, c);

		label = new JLabel("Obst:");
		c.gridx = 0;
		c.gridy = 8;
		m_panelOptions.add(label, c);

		m_automataObst = new JComboBox(elements);
		m_automataObst.setSelectedIndex(4);
		c.gridx = 1;
		c.gridy = 8;
		m_panelOptions.add(m_automataObst, c);

		label = new JLabel("Floor:");
		c.gridx = 0;
		c.gridy = 9;
		m_panelOptions.add(label, c);

		m_automataFloor = new JComboBox(elements);
		m_automataFloor.setSelectedIndex(9);
		c.gridx = 1;
		c.gridy = 9;
		m_panelOptions.add(m_automataFloor, c);

		label = new JLabel("Items:");
		c.gridx = 0;
		c.gridy = 10;
		m_panelOptions.add(label, c);

		m_automataItems = new JComboBox(elements);
		m_automataItems.setSelectedIndex(10);
		c.gridx = 1;
		c.gridy = 10;
		m_panelOptions.add(m_automataItems, c);

//		m_frame.add(m_panelOptions, BorderLayout.CENTER);

//		m_panelOptions.add(m_saveOptions, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m_newGameButton) {
			GameMain.main(null);
			m_frame.dispose();
		} else if (s == m_optionsButton) {
			m_frame.setContentPane(m_panelOptions);
			m_frame.revalidate();
		} else if (s == m_saveOptions) {
			Options.AUTOMATA_WARRIOR = (Spring) m_automataWarrior.getSelectedItem();
			Options.AUTOMATA_SHOOTER = (Spring) m_automataShooter.getSelectedItem();
			Options.AUTOMATA_MAGE = (Spring) m_automataMage.getSelectedItem();
			Options.AUTOMATA_FIREBALL = (Spring) m_automataFireball.getSelectedItem();
			Options.AUTOMATA_BULLET = (Spring) m_automataBullet.getSelectedItem();
			Options.AUTOMATA_MONSTER = (Spring) m_automataMonster.getSelectedItem();
			Options.AUTOMATA_QUEEN = (Spring) m_automataQueen.getSelectedItem();
			Options.AUTOMATA_OBST = (Spring) m_automataObst.getSelectedItem();
			Options.AUTOMATA_FLOOR = (Spring) m_automataFloor.getSelectedItem();
			Options.AUTOMATA_ITEMS = (Spring) m_automataItems.getSelectedItem();
			m_frame.setContentPane(m_panelMenu);
			m_frame.revalidate();
		}
	}
}

public class Launcher {
	public static void main(String[] args) {
		new GameMenu();
	}
}
