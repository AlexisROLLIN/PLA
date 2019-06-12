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
import javax.swing.JRadioButton;
import javax.swing.Spring;

class GameMenu implements ActionListener {

	JFrame m_frame = new JFrame();

	JPanel m_panelMenu = new JPanel();
	JPanel m_panelOptions = new JPanel();
	JPanel m_panelChallenge = new JPanel();

	// Menu

	JButton m_newGameButton = new JButton("New Game");
	JButton m_optionsButton = new JButton("Options");
	JButton m_challengeButton = new JButton("Challenge");

	// Options

	JButton m_saveOptionsButton = new JButton("Save");
	
	// Challenge
	
	JButton m_tryChallengeButton = new JButton("Try");

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
		initPanelChallenge();

		m_frame.setVisible(true);
	}

	public void prepareGUI() {
		m_frame.setTitle("Launcher: What Lurks In The Shadow ?");
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setLayout(new BorderLayout());
		m_frame.setBounds(200, 200, 400, 400);
	}

	public void initPanelMenu() {
		m_panelMenu.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		JLabel label;
		
		// Title

		label = new JLabel("Menu");
		label.setHorizontalAlignment(JLabel.CENTER);
		m_panelMenu.add(label, BorderLayout.NORTH);
		
		// Buttons
		
		m_newGameButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(m_newGameButton, c);

		m_optionsButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(m_optionsButton, c);

		m_challengeButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(m_challengeButton, c);
		
		m_panelMenu.add(panel, BorderLayout.CENTER);
		
		// Display

		m_frame.setContentPane(m_panelMenu);
	}

	public void initPanelOptions() {

		// Init

		m_panelOptions.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		JLabel label;
		JComboBox comboBox;

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

			comboBox = listAutomata.get(i);
			comboBox.setSelectedIndex(i);
			c.gridx = 1;
			c.gridy = i;
			panel.add(comboBox, c);
		}

		m_panelOptions.add(panel, BorderLayout.CENTER);

		// Button

		m_saveOptionsButton.addActionListener(this);
		m_panelOptions.add(m_saveOptionsButton, BorderLayout.SOUTH);
	}
	
	public void initPanelChallenge() {

		// Init

		m_panelChallenge.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		JLabel label;
		JRadioButton challenge;

		// Title

		label = new JLabel("Challenge");
		label.setHorizontalAlignment(JLabel.CENTER);
		m_panelChallenge.add(label, BorderLayout.NORTH);

		// List
		
		challenge = new JRadioButton("Name: ");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(challenge, c);
		
		label = new JLabel("1000");
		c.gridx = 1;
		c.gridy = 0;
		panel.add(label, c);

		m_panelChallenge.add(panel, BorderLayout.CENTER);

		// Button

		m_tryChallengeButton.addActionListener(this);
		m_panelChallenge.add(m_tryChallengeButton, BorderLayout.SOUTH);
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
		} else if (s == m_saveOptionsButton) {
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
		} else if (s == m_challengeButton) {
			m_frame.setContentPane(m_panelChallenge);
			m_frame.revalidate();
		} else if (s == m_tryChallengeButton) {
			// Change Options
			GameMain.main(null);
			m_frame.dispose();
		}
	}
}

public class Launcher {
	public static void main(String[] args) {
		new GameMenu();
	}
}
