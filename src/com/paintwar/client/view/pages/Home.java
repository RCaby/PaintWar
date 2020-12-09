package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.Header;

public class Home extends JPanel {
	
	private static final long serialVersionUID = -1539937711095867060L;
	
	private String playerName;
	private MainWindow manager;
	private static final Dimension DIMENSION_PLAYGAME = new Dimension(200, 75);
	private static final Dimension DIMENSION_CREATE_GAME = new Dimension(200, 100);
	private static final Dimension DIMENSION_JOIN_GAME = new Dimension(200, 75);
	private static final Dimension DIMENSION_GAME_TYPE = new Dimension(200, 25);
	private static final Dimension DIMENSION_GAME_PASSWORD = new Dimension(200, 25);
	private static final Dimension DIMENSION_PLAYER_DATA = new Dimension(700, 400);
	private static final Dimension DIMENSION_EAST_TITLE = new Dimension(700, 25);
	private static final Dimension DIMENSION_EAST_SCROLLER = new Dimension(700, 400);
	// Note : 75 + 25 = 100 -> (Ergo = <3)
	public Home(String name, MainWindow parent) {
		super();
		manager = parent;
		this.playerName = name;
		setLayout(new BorderLayout(25, 25));
		
		
		add(new Header(manager), BorderLayout.NORTH);
		
		
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		center.setLayout(new GridLayout(0, 1));
		
		JPanel centerNorthContainer = new JPanel();
		JPanel centerNorth = new JPanel();
		centerNorth.setLayout(new BoxLayout(centerNorth, BoxLayout.PAGE_AXIS));
		center.add(centerNorthContainer);
		centerNorthContainer.add(centerNorth);
		JButton playGame = new JButton("Jouer");
		JPanel playGamePanel = new JPanel();
		playGamePanel.add(playGame);
		playGame.setPreferredSize(DIMENSION_PLAYGAME);
		centerNorth.add(playGamePanel);
		playGame.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox<String> gameType = new JComboBox<>();
		JPanel gameTypePanel = new JPanel();
		gameTypePanel.add(gameType);
		centerNorth.add(gameTypePanel);
		gameType.setPreferredSize(DIMENSION_GAME_TYPE);
		gameType.addItemListener(new SelectType(gameType));
		gameType.addItem("Partie classée");
		gameType.addItem("Partie non classée");
		
		JPanel centerCenter = new JPanel();
		center.add(centerCenter);
		JButton createGame = new JButton("Créer une partie");
		createGame.setPreferredSize(DIMENSION_CREATE_GAME);
		centerCenter.add(createGame, BorderLayout.CENTER);
		createGame.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel centerSouthContainer = new JPanel();
		JPanel centerSouth = new JPanel();
		center.add(centerSouthContainer);
		centerSouthContainer.add(centerSouth);
		centerSouth.setLayout(new BoxLayout(centerSouth, BoxLayout.PAGE_AXIS));
		JButton joinGame = new JButton("Rejoindre une partie");
		JPanel joinGamePanel = new JPanel();
		joinGame.setPreferredSize(DIMENSION_JOIN_GAME);
		centerSouth.add(joinGamePanel);
		joinGamePanel.add(joinGame);
		JTextField gamePassword = new JTextField("Code de la partie");
		JPanel gamePasswordPanel = new JPanel();
		centerSouth.add(gamePasswordPanel);
		gamePasswordPanel.add(gamePassword);
		gamePassword.setPreferredSize(DIMENSION_GAME_PASSWORD);
		gamePassword.addMouseListener(new ResetText(gamePassword, "Code de la partie"));
		
		JPanel west = new JPanel();
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(10, 10));
		JPanel playerData = new JPanel();
		west.add(playerData, BorderLayout.NORTH);
		playerData.setPreferredSize(DIMENSION_PLAYER_DATA);
		playerData.setLayout(new BoxLayout(playerData, BoxLayout.PAGE_AXIS));
		playerData.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel id = new JLabel("Nom du joueur : " + playerName);
		playerData.add(id);
		JLabel toBecome = new JLabel("Ici seront affichées les informations sur le compte du joueur");
		playerData.add(toBecome);
		JPanel news = new JPanel();
		west.add(news, BorderLayout.SOUTH);
		news.setPreferredSize(DIMENSION_PLAYER_DATA);
		news.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel title = new JLabel("Actualités du jeu");
		incrFontSize(title, 8);
		news.add(title);
		
		
		JPanel east = new JPanel();
		add(east, BorderLayout.EAST);
		east.setBorder(BorderFactory.createLineBorder(Color.black));
		east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
		JPanel eastTitlePanel = new JPanel();
		JLabel eastTitle = new JLabel("Liste d'amis");
		eastTitle.setPreferredSize(DIMENSION_EAST_TITLE);
		incrFontSize(eastTitle, 10); 
		east.add(eastTitlePanel);
		
		eastTitlePanel.add(eastTitle);
		JPanel favoriteFriends = new JPanel();
		east.add(favoriteFriends);
		favoriteFriends.setLayout(new BorderLayout(10, 10));
		JLabel favoriteTitle = new JLabel("Favoris");
		incrFontSize(favoriteTitle, 6);
		favoriteFriends.add(favoriteTitle, BorderLayout.NORTH);
		JScrollPane favoriteScroller = new JScrollPane();
		favoriteFriends.add(favoriteScroller, BorderLayout.CENTER);
		favoriteScroller.setPreferredSize(DIMENSION_EAST_SCROLLER);
		
		JPanel allFriends = new JPanel();
		east.add(allFriends);
		allFriends.setLayout(new BorderLayout(10, 10));
		JLabel allTitle = new JLabel("Tous");
		incrFontSize(allTitle, 6);
		allFriends.add(allTitle, BorderLayout.NORTH);
		JScrollPane allScroller = new JScrollPane();
		allFriends.add(allScroller, BorderLayout.CENTER);
		allScroller.setPreferredSize(DIMENSION_EAST_SCROLLER);
		
		
	}
	
	private void incrFontSize(Component component, int delta) {
		component.setFont(new Font(component.getFont().getName(), component.getFont().getSize(), component.getFont().getSize() + delta));
	}
	
	public class SelectType implements ItemListener {
		
		private JComboBox<String> choice;
		
		public SelectType(JComboBox<String> possibilities) {
			choice = possibilities;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			String newSelected = (String) e.getItem();
			choice.setSelectedItem(newSelected);
			
		}
		
	}
	
public class ResetText implements MouseListener {
		
		private JTextField associatedField;
		private String printing;
		
		public ResetText(JTextField field, String text) {
			this.associatedField = field;
			this.printing = text;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			associatedField.setText("");
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			if (associatedField.getText().equals("")) {
				this.associatedField.setText(printing);
			}
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

}
