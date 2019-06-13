package LurkInTheShadow;

import java.net.URL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class GameMenu implements ActionListener {

	JFrame m_frame = new JFrame();

	JPanel m_panelMenu = new JPanel();
	JPanel m_panelOptions = new JPanel();
	JPanel m_panelChallenge = new JPanel();

	// Menu

	JButton m_newGameButton = new JButton();
	JButton m_optionsButton = new JButton();
	JButton m_challengeButton = new JButton();

	// Options

	JButton m_saveOptionsButton = new JButton("Save");
	JButton m_cancelOptionsButton = new JButton("Cancel");

	// Challenge

	JButton m_selectFileButton = new JButton("Select File");
	JButton m_tryChallengeButton = new JButton("Try");
	JButton m_cancelChallengeButton = new JButton("Cancel");

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
		initPanelChallenge();
		initPanelOptions();

		m_frame.setVisible(true);
	}

	public void prepareGUI() {
		m_frame.setTitle("Launcher: What Lurks In The Shadow ?");
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setLayout(new BorderLayout());
		m_frame.setBounds(200, 200, 700, 700);
	}

	public void initPanelMenu() {

		// Init

		m_panelMenu.setLayout(new BorderLayout());

		ImagePanel panel = new ImagePanel(
				new ImageIcon("C:\\Users\\yoann\\OneDrive\\Documents\\Polytech_Info\\background.png").getImage());
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
		m_newGameButton.setIcon(new ImageIcon("C:\\Users\\yoann\\OneDrive\\Documents\\Polytech_Info\\new_game.png"));
		m_newGameButton.addActionListener(this);
		m_newGameButton.setHorizontalAlignment(JLabel.CENTER);
		m_newGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_newGameButton);

		m_optionsButton.setOpaque(false);
		m_optionsButton.setContentAreaFilled(false);
		m_optionsButton.setBorderPainted(false);
		m_optionsButton.setIcon(new ImageIcon("C:\\Users\\yoann\\OneDrive\\Documents\\Polytech_Info\\options.png"));
		m_optionsButton.addActionListener(this);
		m_optionsButton.setHorizontalAlignment(JLabel.CENTER);
		m_optionsButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_optionsButton);

		m_challengeButton.setOpaque(false);
		m_challengeButton.setContentAreaFilled(false);
		m_challengeButton.setBorderPainted(false);
		m_challengeButton.setIcon(new ImageIcon("C:\\Users\\yoann\\OneDrive\\Documents\\Polytech_Info\\challenge.png"));
		m_challengeButton.addActionListener(this);
		m_challengeButton.setHorizontalAlignment(JLabel.CENTER);
		m_challengeButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_challengeButton);

		panel.add(Box.createVerticalGlue());

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

		label = new JLabel("OPTIONS");
		Font font = new Font("TimesRoman", Font.BOLD, 64);
		label.setFont(font);
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

		int i;

		for (i = 0; components.length > i; i++) {

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
		m_cancelChallengeButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		panel.add(m_cancelChallengeButton);

		panel.add(Box.createVerticalGlue());

		m_panelChallenge.add(panel, BorderLayout.CENTER);
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
			GameMain.main(null);
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
	public static void main(String[] args) {
		new GameMenu();
	}
}
