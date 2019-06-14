package LurkInTheShadow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	JPanel m_panelChallenge = new JPanel();

	// Menu

	JButton m_newGameButton = new JButton();
	JButton m_optionsButton = new JButton();
	JButton m_challengeButton = new JButton();

	// MenuBar

	JMenuItem m_i1 = new JMenuItem("À propos");

	// Options

	JButton m_saveOptionsButton = new JButton("Save");
	JButton m_cancelOptionsButton = new JButton("Cancel");

	// Challenge

	JButton m_selectFileButton = new JButton("Select File");
	JButton m_tryChallengeButton = new JButton("Try");
	JButton m_cancelChallengeButton = new JButton("Cancel");

	IAutomaton[] automatons = new IAutomaton[14];

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
	
	IAI_Definitions iai_def;

	GameMenu() {
		prepareGUI();
		initMenuBar();
		initPanelMenu();
		try {
			initPanelOptions();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initPanelChallenge();

		m_frame.setVisible(true);
	}

	public void prepareGUI() {
		m_frame.setTitle("Launcher: What Lurks In The Shadow ?");
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setLayout(new BorderLayout());
		// m_frame.getContentPane().setLayout(null);
		m_frame.setBounds(200, 200, 700, 700);
		m_frame.setResizable(false);
	}

	public void initMenuBar() {

		// Init

		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Help");
		m_i1.addActionListener(this);
		menu.add(m_i1);
		mb.add(menu);

		// Display

		m_frame.setJMenuBar(mb);
	}

	public void initPanelMenu() {

		// Init

		m_panelMenu.setLayout(new BorderLayout());

		ImagePanel panel = new ImagePanel(new ImageIcon(
				"src/Sprites/background.png").getImage());
		panel.setBackground(new Color(0F, 0F, 0F, 0F));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		panel.add(Box.createVerticalGlue());
		panel.add(Box.createVerticalGlue());
		panel.add(Box.createVerticalGlue());
		panel.add(Box.createVerticalGlue());
		panel.add(Box.createVerticalGlue());

		// Buttons

		m_newGameButton.setOpaque(false);
		m_newGameButton.setContentAreaFilled(false);
		m_newGameButton.setBorderPainted(false);
		m_newGameButton.setIcon(new ImageIcon("src/Sprites/newgame.png"));
		m_newGameButton.addActionListener(this);
		m_newGameButton.setHorizontalAlignment(JLabel.CENTER);
		m_newGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_newGameButton);

		m_optionsButton.setOpaque(false);
		m_optionsButton.setContentAreaFilled(false);
		m_optionsButton.setBorderPainted(false);
		m_optionsButton.setIcon(new ImageIcon("src/Sprites/options.png"));
		m_optionsButton.addActionListener(this);
		m_optionsButton.setHorizontalAlignment(JLabel.CENTER);
		m_optionsButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_optionsButton);

		m_challengeButton.setOpaque(false);
		m_challengeButton.setContentAreaFilled(false);
		m_challengeButton.setBorderPainted(false);
		m_challengeButton.setIcon(new ImageIcon("src/Sprites/challenge.png"));
		m_challengeButton.addActionListener(this);
		m_challengeButton.setHorizontalAlignment(JLabel.CENTER);
		m_challengeButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_challengeButton);

		panel.add(Box.createVerticalGlue());

		m_panelMenu.add(panel, BorderLayout.CENTER);

		// Display

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

		label = new JLabel("OPTIONS");
		Font font = new Font("TimesRoman", Font.BOLD, 64);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		m_panelOptions.add(label, BorderLayout.NORTH);

		// List

		String[] components = new String[] { "Player", "Warrior", "Shooter",
				"Mage", "Fireball", "Bullet", "Monster", "Queen", "Obst",
				"Floor", "Items" };

		LinkedList<JComboBox> listAutomata = new LinkedList<JComboBox>();

		AI_Definitions ai_def = ((AI_Definitions) AutomataParser
				.from_file("src/Automates/Automate2.txt"));
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

		Options.AUTOMATA_PLAYER = Sauvegarde.recherche_automate("Player",
				iai_def);
		Options.AUTOMATA_WARRIOR = Sauvegarde.recherche_automate("Allie2",
				iai_def);
		Options.AUTOMATA_SHOOTER = Sauvegarde.recherche_automate(
				"AllieModeReine", iai_def);
		Options.AUTOMATA_MAGE = Sauvegarde
				.recherche_automate("Allie1", iai_def);
		Options.AUTOMATA_FIREBALL = Sauvegarde.recherche_automate("BouleDeFeu",
				iai_def);
		Options.AUTOMATA_BULLET = Sauvegarde.recherche_automate("Bullet",
				iai_def);
		Options.AUTOMATA_MONSTER = Sauvegarde.recherche_automate("Monstre",
				iai_def);
		Options.AUTOMATA_QUEEN = Sauvegarde
				.recherche_automate("Reine", iai_def);
		Options.AUTOMATA_OBST = Sauvegarde.recherche_automate("Wall", iai_def);
		Options.AUTOMATA_FLOOR = Sauvegarde.recherche_automate("Ground",
				iai_def);
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

		// Buttons

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		m_saveOptionsButton.setBackground(Color.green);
		m_saveOptionsButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(m_saveOptionsButton, c);

		m_cancelOptionsButton.setBackground(Color.red);
		m_cancelOptionsButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(m_cancelOptionsButton, c);

		m_panelOptions.add(panel, BorderLayout.SOUTH);

	}

	public void initPanelChallenge() {

		// Init

		m_panelChallenge.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JLabel label;

		// Title

		label = new JLabel("CHALLENGE");
		Font font = new Font("TimesRoman", Font.BOLD, 64);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(label);

		panel.add(Box.createVerticalGlue());
		panel.add(Box.createVerticalGlue());
		panel.add(Box.createVerticalGlue());
		panel.add(Box.createVerticalGlue());

		// Buttons

		m_selectFileButton = new JButton("Select File");
		m_selectFileButton.setSize(225, 90);
		m_selectFileButton.addActionListener(this);
		m_selectFileButton.setHorizontalAlignment(JLabel.CENTER);
		m_selectFileButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_selectFileButton);

		m_tryChallengeButton.setSize(225, 90);
		m_tryChallengeButton.setBackground(Color.green);
		m_tryChallengeButton.addActionListener(this);
		m_tryChallengeButton.setHorizontalAlignment(JLabel.CENTER);
		m_tryChallengeButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_tryChallengeButton);

		m_cancelChallengeButton.setSize(225, 90);
		m_cancelChallengeButton.setBackground(Color.red);
		m_cancelChallengeButton.addActionListener(this);
		m_cancelChallengeButton.setHorizontalAlignment(JLabel.CENTER);
		m_cancelChallengeButton
				.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_cancelChallengeButton);

		panel.add(Box.createVerticalGlue());

		m_panelChallenge.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();

		if (s == m_i1) {
			JOptionPane.showMessageDialog(m_frame,
					"Copyright (C) 2018-2019  Groupe 8");
		} else if (s == m_newGameButton) {
			try {
				GameMain.main(null);
			} catch (Exception e1) {
			}
			m_frame.dispose();
		} else if (s == m_optionsButton) {
			m_frame.setContentPane(m_panelOptions);
			m_frame.revalidate();
		} else if (s == m_saveOptionsButton) {
			Options.AUTOMATA_PLAYER = (IAutomaton) m_automataPlayer
					.getSelectedItem();
			Options.AUTOMATA_WARRIOR = (IAutomaton) m_automataWarrior
					.getSelectedItem();
			Options.AUTOMATA_SHOOTER = (IAutomaton) m_automataShooter
					.getSelectedItem();
			Options.AUTOMATA_MAGE = (IAutomaton) m_automataMage
					.getSelectedItem();
			Options.AUTOMATA_FIREBALL = (IAutomaton) m_automataFireball
					.getSelectedItem();
			Options.AUTOMATA_BULLET = (IAutomaton) m_automataBullet
					.getSelectedItem();
			Options.AUTOMATA_MONSTER = (IAutomaton) m_automataMonster
					.getSelectedItem();
			Options.AUTOMATA_QUEEN = (IAutomaton) m_automataQueen
					.getSelectedItem();
			Options.AUTOMATA_OBST = (IAutomaton) m_automataObst
					.getSelectedItem();
			Options.AUTOMATA_FLOOR = (IAutomaton) m_automataFloor
					.getSelectedItem();
			Options.AUTOMATA_ITEMS = (IAutomaton) m_automataItems
					.getSelectedItem();

			m_frame.setContentPane(m_panelMenu);
			m_frame.revalidate();
		} else if (s == m_challengeButton) {
			m_frame.setContentPane(m_panelChallenge);
			m_frame.revalidate();
		} else if (s == m_selectFileButton) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {

				Options.option_load = true;

				File selectedFile = fileChooser.getSelectedFile();

				Sauvegarde sauv = Sauvegarde.decode(selectedFile,iai_def);

				Options.AUTOMATA_PLAYER = sauv.tab_auto[0];
				Options.AUTOMATA_WARRIOR = sauv.tab_auto[1];
				Options.AUTOMATA_SHOOTER = sauv.tab_auto[2];
				Options.AUTOMATA_MAGE = sauv.tab_auto[3];
				Options.AUTOMATA_FIREBALL = sauv.tab_auto[4];
				Options.AUTOMATA_BULLET = sauv.tab_auto[5];
				Options.AUTOMATA_MONSTER = sauv.tab_auto[6];
				Options.AUTOMATA_QUEEN = sauv.tab_auto[7];
				Options.AUTOMATA_OBST = sauv.tab_auto[8];
				Options.AUTOMATA_FLOOR = sauv.tab_auto[9];
				Options.AUTOMATA_ITEMS = sauv.tab_auto[10];
				Options.map = sauv.tab_map;

				System.out.println(selectedFile.getName());
			}
		} else if (s == m_cancelOptionsButton) {
			m_frame.setContentPane(m_panelMenu);
			m_frame.revalidate();
		} else if (s == m_challengeButton) {
			m_frame.setContentPane(m_panelChallenge);
			m_frame.revalidate();
		} else if (s == m_selectFileButton) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getName());
			}
		} else if (s == m_tryChallengeButton) {
			try {
				GameMain.main(null);
			} catch (Interpreter_Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			m_frame.dispose();
		} else if (s == m_cancelChallengeButton) {
			m_frame.setContentPane(m_panelMenu);
			m_frame.revalidate();
		}
	}
}

class ImagePanel extends JPanel {

	private Image img;

	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

}

public class Launcher {
	public static void main(String[] args) throws Exception {
		new GameMenu();
	}
}
