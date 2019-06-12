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

import interpreter.IAI_Definitions;
import interpreter.Interpreter_Exception;
import ricm3.parser.Ast;
import ricm3.parser.Ast.AI_Definitions;
import sauvegarde.Sauvegarde;
import ricm3.parser.AutomataParser;
import interpreter.IAutomaton;

class GameMenu implements ActionListener {

	JFrame m_frame = new JFrame();

	JPanel m_panelMenu = new JPanel();
	JPanel m_panelOptions = new JPanel();

	// Menu

	JButton m_newGameButton = new JButton("New Game");
	JButton m_optionsButton = new JButton("Options");
	JButton m_challengeButton = new JButton("Challenge");
	IAutomaton[] automatons = new IAutomaton[11];

	// Options

	JButton m_saveOptions = new JButton("Save");

	JComboBox<IAutomaton> m_automataPlayer;
	JComboBox<IAutomaton> m_automataWarrior;
	JComboBox<IAutomaton> m_automataShooter;
	JComboBox<IAutomaton> m_automataMage;
	JComboBox<IAutomaton> m_automataFireball;
	JComboBox<IAutomaton> m_automataBullet;
	JComboBox<IAutomaton> m_automataMonster;
	JComboBox<IAutomaton> m_automataQueen;
	JComboBox<IAutomaton> m_automataObst;
	JComboBox<IAutomaton> m_automataFloor;
	JComboBox<IAutomaton> m_automataItems;

	GameMenu() throws Exception {
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

	public void initPanelOptions() throws Exception {

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

		String[] components = new String[] { "Player", "Warrior", "Shooter", "Mage", "Fireball", "Bullet", "Monster", "Queen",
				"Obst", "Floor", "Items" };

		LinkedList<JComboBox> listAutomata = new LinkedList<JComboBox>();
		
		IAI_Definitions iai_def;
		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("src/Automates/Automate2.txt"));
		iai_def = ai_def.make();

		for (int i = 0; i < iai_def.automatas.size(); i++) {
			automatons[i] = iai_def.automatas.get(i);
		}

		m_automataPlayer = new JComboBox<IAutomaton>(automatons);
		m_automataWarrior = new JComboBox<IAutomaton>(automatons);
		m_automataShooter = new JComboBox<IAutomaton>(automatons);
		m_automataMage = new JComboBox<IAutomaton>(automatons);
		m_automataFireball = new JComboBox<IAutomaton>(automatons);
		m_automataBullet = new JComboBox<IAutomaton>(automatons);
		m_automataMonster = new JComboBox<IAutomaton>(automatons);
		m_automataQueen = new JComboBox<IAutomaton>(automatons);
		m_automataObst = new JComboBox<IAutomaton>(automatons);
		m_automataFloor = new JComboBox<IAutomaton>(automatons);
		m_automataItems = new JComboBox<IAutomaton>(automatons);
		
		Options.AUTOMATA_PLAYER = Sauvegarde.recherche_automate("Player", iai_def);
		Options.AUTOMATA_WARRIOR = Sauvegarde.recherche_automate("Allie2", iai_def);
		Options.AUTOMATA_SHOOTER = Sauvegarde.recherche_automate("AllieModeReine", iai_def);
		Options.AUTOMATA_MAGE = Sauvegarde.recherche_automate("Allie1", iai_def);
		Options.AUTOMATA_FIREBALL = Sauvegarde.recherche_automate("BouleDeFeu", iai_def);
		Options.AUTOMATA_BULLET = Sauvegarde.recherche_automate("Bullet", iai_def);
		Options.AUTOMATA_MONSTER = Sauvegarde.recherche_automate("Monstre", iai_def);
		Options.AUTOMATA_QUEEN = Sauvegarde.recherche_automate("Reine", iai_def);
		Options.AUTOMATA_OBST = Sauvegarde.recherche_automate("Wall", iai_def);
		Options.AUTOMATA_FLOOR = Sauvegarde.recherche_automate("Ground1", iai_def);
		Options.AUTOMATA_ITEMS = Sauvegarde.recherche_automate("Item", iai_def);
		
		listAutomata.add(m_automataPlayer);
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

		m_panelOptions.add(panel, BorderLayout.CENTER);

		// Button

		m_saveOptions.addActionListener(this);
		m_panelOptions.add(m_saveOptions, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m_newGameButton) {
			try {
				GameMain.main(null);
			} catch (Exception e1) {
			}
			m_frame.dispose();
		} else if (s == m_optionsButton) {
			m_frame.setContentPane(m_panelOptions);
			m_frame.revalidate();
		} else if (s == m_saveOptions) {
			Options.AUTOMATA_PLAYER = (IAutomaton) m_automataPlayer.getSelectedItem();
			Options.AUTOMATA_WARRIOR = (IAutomaton) m_automataWarrior.getSelectedItem();
			Options.AUTOMATA_SHOOTER = (IAutomaton) m_automataShooter.getSelectedItem();
			Options.AUTOMATA_MAGE = (IAutomaton) m_automataMage.getSelectedItem();
			Options.AUTOMATA_FIREBALL = (IAutomaton) m_automataFireball.getSelectedItem();
			Options.AUTOMATA_BULLET = (IAutomaton) m_automataBullet.getSelectedItem();
			Options.AUTOMATA_MONSTER = (IAutomaton) m_automataMonster.getSelectedItem();
			Options.AUTOMATA_QUEEN = (IAutomaton) m_automataQueen.getSelectedItem();
			Options.AUTOMATA_OBST = (IAutomaton) m_automataObst.getSelectedItem();
			Options.AUTOMATA_FLOOR = (IAutomaton) m_automataFloor.getSelectedItem();
			Options.AUTOMATA_ITEMS = (IAutomaton) m_automataItems.getSelectedItem();
			
			Options. config_changed=true;
			m_frame.setContentPane(m_panelMenu);
			m_frame.revalidate();
		}
	}
}

public class Launcher {
	public static void main(String[] args) throws Exception {
		new GameMenu();
	}
}
