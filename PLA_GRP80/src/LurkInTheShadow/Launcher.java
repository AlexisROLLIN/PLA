package LurkInTheShadow;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

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

	String[] automatons = new String[] { "Player", "leader", "spawn1", "spawn2", "obst", "monster", "queen", "fireball",
			"bullet", "floor", "transe", "monstre_desoriente" };

	JComboBox m_automataWarrior = new JComboBox(automatons);
	JComboBox m_automataShooter = new JComboBox(automatons);
	JComboBox m_automataMage = new JComboBox(automatons);
	JComboBox m_automataFireball = new JComboBox(automatons);
	JComboBox m_automataBullet = new JComboBox(automatons);
	JComboBox m_automataMonster = new JComboBox(automatons);
	JComboBox m_automataQueen = new JComboBox(automatons);
	JComboBox m_automataObst = new JComboBox(automatons);
	JComboBox m_automataFloor = new JComboBox(automatons);
	JComboBox m_automataItems = new JComboBox(automatons);

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
//		m_panelMenu.setLayout(new BoxLayout(m_panelMenu, BoxLayout.PAGE_AXIS));
		
		m_panelMenu.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		JLabel label;

		label = new JLabel("Menu");
		label.setHorizontalAlignment(JLabel.CENTER);
//		label.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//		m_panelMenu.add(label);
		m_panelMenu.add(label, BorderLayout.NORTH);

//		m_panelMenu.add(Box.createVerticalGlue());
//
//		m_newGameButton.setHorizontalAlignment(JLabel.CENTER);
//		m_newGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//		m_newGameButton.addActionListener(this);
//		m_panelMenu.add(m_newGameButton);
//
//		m_optionsButton.setHorizontalAlignment(JLabel.CENTER);
//		m_optionsButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//		m_optionsButton.addActionListener(this);
//		m_panelMenu.add(m_optionsButton);
//
//		m_challengeButton.setHorizontalAlignment(JLabel.CENTER);
//		m_challengeButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//		m_challengeButton.addActionListener(this);
//		m_panelMenu.add(m_challengeButton);
		
		m_newGameButton.addActionListener(this);
		c.gridwidth = 1;
	    c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(m_newGameButton, c);

		m_optionsButton.addActionListener(this);
		c.gridwidth = 1;
	    c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(m_optionsButton, c);

		m_challengeButton.addActionListener(this);
		c.gridwidth = 1;
	    c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(m_challengeButton, c);
		
		m_panelMenu.add(panel, BorderLayout.CENTER);

		m_frame.setContentPane(m_panelMenu);
	}

	public void initPanelOptions() {

		// Init

		m_panelOptions.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel label;

		// Title

		label = new JLabel("Options");
		label.setHorizontalAlignment(JLabel.CENTER);
		m_panelOptions.add(label, BorderLayout.NORTH);

		// List

		String[] components = new String[] { "Warrior", "Shooter", "Mage", "Fireball", "Bullet", "Monster", "Queen",
				"Obst", "Floor", "Items" };

		LinkedList<JComboBox> listAutomata = new LinkedList<JComboBox>();
		listAutomata.add(m_automataWarrior);
		listAutomata.add(m_automataShooter);
		listAutomata.add(m_automataMage);
		listAutomata.add(m_automataFireball);
		listAutomata.add(m_automataBullet);
		listAutomata.add(m_automataMonster);
		listAutomata.add(m_automataQueen);
		listAutomata.add(m_automataObst);
		listAutomata.add(m_automataFloor);
		listAutomata.add(m_automataItems);

		for (int i = 0; components.length > i; i++) {

			label = new JLabel();
			label.setText(components[i] + ":   ");
			c.gridx = 0;
			c.gridy = i;
			panel.add(label, c);

			JComboBox comboBox = listAutomata.get(i);
			comboBox.setSelectedIndex(i);
			c.gridx = 1;
			c.gridy = i;
			panel.add(comboBox, c);
		}

//		label = new JLabel("Warrior:");
//		c.gridx = 0;
//		c.gridy = 1;
//		panel.add(label, c);
//
//		m_automataWarrior.setSelectedIndex(1);
//		c.gridx = 1;
//		c.gridy = 1;
//		panel.add(m_automataWarrior, c);
//
//		label = new JLabel("Shooter:");
//		c.gridx = 0;
//		c.gridy = 2;
//		panel.add(label, c);
//
//		m_automataShooter.setSelectedIndex(0);
//		c.gridx = 1;
//		c.gridy = 2;
//		panel.add(m_automataShooter, c);
//
//		label = new JLabel("Mage:");
//		c.gridx = 0;
//		c.gridy = 3;
//		panel.add(label, c);
//
//		m_automataMage.setSelectedIndex(0);
//		c.gridx = 1;
//		c.gridy = 3;
//		panel.add(m_automataMage, c);
//
//		label = new JLabel("Fireball:");
//		c.gridx = 0;
//		c.gridy = 4;
//		panel.add(label, c);
//
//		m_automataFireball.setSelectedIndex(7);
//		c.gridx = 1;
//		c.gridy = 4;
//		panel.add(m_automataFireball, c);
//
//		label = new JLabel("Bullet:");
//		c.gridx = 0;
//		c.gridy = 5;
//		panel.add(label, c);
//
//		m_automataBullet.setSelectedIndex(8);
//		c.gridx = 1;
//		c.gridy = 5;
//		panel.add(m_automataBullet, c);
//
//		label = new JLabel("Monster:");
//		c.gridx = 0;
//		c.gridy = 6;
//		panel.add(label, c);
//
//		m_automataMonster.setSelectedIndex(5);
//		c.gridx = 1;
//		c.gridy = 6;
//		panel.add(m_automataMonster, c);
//
//		label = new JLabel("Queen:");
//		c.gridx = 0;
//		c.gridy = 7;
//		panel.add(label, c);
//
//		m_automataQueen.setSelectedIndex(6);
//		c.gridx = 1;
//		c.gridy = 7;
//		panel.add(m_automataQueen, c);
//
//		label = new JLabel("Obst:");
//		c.gridx = 0;
//		c.gridy = 8;
//		panel.add(label, c);
//
//		m_automataObst.setSelectedIndex(4);
//		c.gridx = 1;
//		c.gridy = 8;
//		panel.add(m_automataObst, c);
//
//		label = new JLabel("Floor:");
//		c.gridx = 0;
//		c.gridy = 9;
//		panel.add(label, c);
//
//		m_automataFloor.setSelectedIndex(9);
//		c.gridx = 1;
//		c.gridy = 9;
//		panel.add(m_automataFloor, c);
//
//		label = new JLabel("Items:");
//		c.gridx = 0;
//		c.gridy = 10;
//		panel.add(label, c);
//
//		m_automataItems.setSelectedIndex(10);
//		c.gridx = 1;
//		c.gridy = 10;
//		panel.add(m_automataItems, c);

		m_panelOptions.add(panel, BorderLayout.CENTER);

		// Button

		m_panelOptions.add(m_saveOptions, BorderLayout.SOUTH);
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
			System.out.println("test");
			Options.AUTOMATA_WARRIOR = (String) m_automataWarrior.getSelectedItem();
			Options.AUTOMATA_SHOOTER = (String) m_automataShooter.getSelectedItem();
			Options.AUTOMATA_MAGE = (String) m_automataMage.getSelectedItem();
			Options.AUTOMATA_FIREBALL = (String) m_automataFireball.getSelectedItem();
			Options.AUTOMATA_BULLET = (String) m_automataBullet.getSelectedItem();
			Options.AUTOMATA_MONSTER = (String) m_automataMonster.getSelectedItem();
			Options.AUTOMATA_QUEEN = (String) m_automataQueen.getSelectedItem();
			Options.AUTOMATA_OBST = (String) m_automataObst.getSelectedItem();
			Options.AUTOMATA_FLOOR = (String) m_automataFloor.getSelectedItem();
			Options.AUTOMATA_ITEMS = (String) m_automataItems.getSelectedItem();
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
