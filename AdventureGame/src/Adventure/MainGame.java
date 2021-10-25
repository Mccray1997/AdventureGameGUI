package Adventure;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Adventure Game gui
 * @author Gilbert McCray
 *
 */


public class MainGame {
	// Varibles for the ui
	JFrame frame;
	Container contain;
	JPanel titleNamePanel,startPanel,mainTextPanel,choiceButtonPanel,playerPanel;
	JLabel titleName, hpLabel, hpLabelNum, weaponLabel, weaponLabelName;
	JButton startButton,choiceOne,choiceTwo,choiceThree,choiceFour;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 28);
	Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
	JTextArea mainTextArea;
	
	// Varibles for the player
	int playerHP, minDamage, maxDamage,heal, goblinHP, goblinDamage, weaponMinD, weaponMaxD;
	String weapon,position;
	
	// tsHandler object
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	
	// ChoiceHandler object
	ChoiceHandler choiceHandle = new ChoiceHandler();
	
	/**
	 * Main Game constructor
	 */
	public MainGame() {
		
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(null);
		
		contain = frame.getContentPane();
		
		titleNamePanel = mainTextPanel(100, 100, 600, 150);

		titleName = newLabel("Adventure Game");
		titleName.setFont(titleFont);
		
		startPanel = ButtonPanel(300, 300, 200, 100);

		
		startButton = ChoiceButton("Start");
		startButton.addActionListener(tsHandler);
		
		startPanel.add(startButton);
		titleNamePanel.add(titleName);
		
		contain.add(titleNamePanel);
		contain.add(startPanel);
		frame.setVisible(true);
	}// end constructor
	
	
	/**
	 * Creates the a screen for the gui.
	 */
	public void createScreen() {
		
		// Sets the main screen to false
		titleNamePanel.setVisible(false);
		startPanel.setVisible(false);
		
		// Adds the main text panel
		mainTextPanel = mainTextPanel(100, 100, 600, 250);
		contain.add(mainTextPanel);
		
		// Adds the main text area to the main text panel
		mainTextArea = mainTextArea(100, 100, 600, 250);
		mainTextPanel.add(mainTextArea);
		
		// Adds the button panel
		choiceButtonPanel = ButtonPanel(250,350,300,150);
		contain.add(choiceButtonPanel);
		
		// adds button 
		choiceOne = ChoiceButton("One");
		choiceButtonPanel.add(choiceOne);
		choiceOne.addActionListener(choiceHandle);
		choiceOne.setActionCommand("c1");
		
		choiceTwo = ChoiceButton("Two");
		choiceButtonPanel.add(choiceTwo);
		choiceTwo.addActionListener(choiceHandle);
		choiceTwo.setActionCommand("c2");
		
		choiceThree = ChoiceButton("Three");
		choiceButtonPanel.add(choiceThree);
		choiceThree.addActionListener(choiceHandle);
		choiceThree.setActionCommand("c3");
		
		choiceFour = ChoiceButton("Four");
		choiceButtonPanel.add(choiceFour);
		choiceFour.addActionListener(choiceHandle);
		choiceFour.setActionCommand("c4");
		
		
		// Adds the weapon and hp to the top of the gui.
		playerPanel = mainTextPanel(100,15,600,50);
		playerPanel.setLayout(new GridLayout (1,4));
		contain.add(playerPanel);
		hpLabel = newLabel("HP: ");
		playerPanel.add(hpLabel);
		
		hpLabelNum = newLabel(null);
		playerPanel.add(hpLabelNum);
		
		weaponLabel = newLabel("Weapon: ");
		playerPanel.add(weaponLabel);
		
		weaponLabelName = newLabel(null);
		playerPanel.add(weaponLabelName);
		
		playerSetUp();
	}
	
	/**
	 * Creates new Label for gui.
	 * @param message
	 * @return new Label
	 */
	public JLabel newLabel(String message) {
		JLabel label = new JLabel(message);
		label.setFont(buttonFont);
		label.setForeground(Color.white);
		return label;
	}
	
	/**
	 * Creates a panel for the main text in the gui
	 * @param x  x cord 
	 * @param y	 y cord
	 * @param width width of the text area 
	 * @param height  height of the text area
	 * @return mainPanel The panel for the main text
	 */
	public JPanel mainTextPanel(int x, int y, int width, int height) {
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(x, y, width, height);
		mainPanel.setBackground(Color.black);
		return mainPanel;
	}
	
	
	/**
	 * Crates a area for the text. 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public JTextArea mainTextArea(int x, int y, int width, int height) {
		JTextArea text = new JTextArea("This is the main text");
		text.setBounds(x, y, width, height);
		text.setBackground(Color.black);
		text.setForeground(Color.white);
		text.setFont(buttonFont);
		text.setLineWrap(true);
		
		return text;
	}
	
	/**
	 * Creates a panel for a button inside the gui. 
	 * @param x x Cord
	 * @param y y cord 
	 * @param width  width of the panel 
	 * @param height	height of the panel 
	 * @return
	 */
	public JPanel ButtonPanel(int x, int y, int width, int height) {
		JPanel buttonP = new JPanel();
		buttonP.setBounds(x, y, width, height);
		buttonP.setBackground(Color.black);
		buttonP.setLayout(new GridLayout(4,1));
		return buttonP;
		
	}
	
	/**
	 * Creates a button 
	 * @param number the text to appear on the button
	 * @return Button for the gui 
	 */
	public JButton ChoiceButton(String number) {
		JButton button;
		button = new JButton(number);
		button.setBackground(Color.black);
		button.setFont(buttonFont);
		return button; 
		
	}
	
	/**
	 * Sets the players character up for the game.
	 */
	
	public void playerSetUp() {
		playerHP = 10;
		maxDamage = playerHP;
		minDamage = 1;
		weapon = "knife";
		weaponLabelName.setText(weapon);
		hpLabelNum.setText(Integer.toString(playerHP));
		
		townGate();
	}
	
	/**
	 * Specs for enemy within the game.
	 */
	public void goblin() {
		goblinHP = 5;
		goblinDamage = 3;
	}
	
	/**
	 * Game location
	 */
	public void townGate() {
		// players curent location
		position = "townGate";
		mainTextArea.setText("You are at the town gate. What do you do? ");
		
		choiceOne.setText("Talk to the guard.");
		choiceTwo.setText("Attack the guard");
		choiceThree.setText("Leave");
		choiceFour.setText(null);
	}
	
	/**
	 * Game action at the town gate
	 */
	public void talkToGuard() {
		// Players current location
		position = "talkGuard";
		mainTextArea.setText("You may not enter the town right now. We are on lockdown.");
		
		choiceOne.setText("Question the guard.");
		choiceTwo.setText("Attack the guard");
		choiceThree.setText("Leave");
		choiceFour.setText(null);
	}
	
	/**
	 * Game action at the town gate.
	 */
	public void attackGuard() {
		Random rand = new Random();
		int guardDamage = rand.nextInt(maxDamage - minDamage) + minDamage;
		playerHP -= guardDamage;
		hpLabelNum.setText(Integer.toString(playerHP)); 
		position = "attackGuard";
		mainTextArea.setText("Guard: Idiot.\n"
				+ "The guard hits you with the butt of the spear. \n "
				+"You rescive " + guardDamage + " damage from the guard");
		
		choiceOne.setText("Aplogize to the Guard. ");
		choiceTwo.setText("Do you really wan to attack again.");
		choiceThree.setText("Leave without saying a word. ");
		choiceFour.setText(null);
	}
	
	/**
	 * Town location
	 */
	public void crossRoad() {
		position = "crossRoad";
		mainTextArea.setText("You are back at the cross roads outside of town.\n"
				+ "To the North is the town gate. \n" +
				"To the East is a forest. \n" +
				"To the South is a river. \n"+
				"To the West is a cave. \n");
		choiceOne.setText("Go North");
		choiceTwo.setText("Go East");
		choiceThree.setText("Go South ");
		choiceFour.setText("Go West");
	}
	
	
	/**
	 * Game location
	 */
	public void river() {
		
		position = "river";
		int temp = playerHP;
		playerHP = 10;
		heal = (temp - 10)*-1;
		hpLabelNum.setText(Integer.toString(playerHP));
		
		mainTextArea.setText("You come the river to the south of the town. \n"
				+ "You decide to rest along the river bank and take a nap"
				+ "You regain " + heal + " hp.");
		choiceOne.setText("Go back to the cross roads.");
		choiceTwo.setText(null);
		choiceThree.setText(null);
		choiceFour.setText(null);
		
	}
	
	/**
	 * Game location.
	 */
	public void forest() {
		position = "forest";
		mainTextArea.setText("You come to the forest that is west of the town.\n"
				+ "As you are walking you are attacked by a goblin.\n"
				+ "Do you want to fight or run away. ");
		choiceOne.setText("Fight to the death.");
		choiceTwo.setText("Run for your life");
		choiceThree.setText(null);
		choiceFour.setText(null);
	}
	
	/**
	 * Game action at the town gate.
	 */
	public void attackGoblin() {
		Random rand = new Random();
		int guardDamage = rand.nextInt(maxDamage - minDamage) + minDamage;
		playerHP -= guardDamage;
		hpLabelNum.setText(Integer.toString(playerHP)); 
		position = "attackGuard";
		mainTextArea.setText("Guard: Idiot.\n"
				+ "The guard hits you with the butt of the spear. \n "
				+"You rescive " + guardDamage + " damage from the guard");
		
		choiceOne.setText("Aplogize to the Guard. ");
		choiceTwo.setText("Do you really wan to attack again.");
		choiceThree.setText("Leave without saying a word. ");
		choiceFour.setText(null);
	}
	
	
	/**
	 * Game Location.
	 */
	public void cave() {
		position = "cave";
		mainTextArea.setText("You have come to the cave to the east of the town. \n" + 
		"You look into the cave and you see something glittering in the faint light.\n "
				+ "You go into the cave to see what it is and you find a longsword. \n"
		+ "Do you want to equip the sword?");
		
		choiceOne.setText("Place into your pack");
		choiceTwo.setText("Equip the sword. ");
		choiceThree.setText("Throw the sword away.");
		choiceFour.setText("Eat it");
	}
	
	public void eatSword() {
		position = "idiot";
		mainTextArea.setText("");
	}
	
	public void setSword() {
		weapon = "Sword";
		weaponLabelName.setText(weapon);
	}
	
	/**
	 * @implNote ActionListener
	 * @author Gilbert McCray
	 *
	 */
	
	public class TitleScreenHandler implements ActionListener{
		
		/**
		 * When a button is mashed it calls the create screen function.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			createScreen();
			
		}
			
	}//end TitleScreenHandler class
	
	public class ChoiceHandler implements ActionListener{
		
		/**
		 * Implements the choices you make at each locatoin.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String yourChoice = e.getActionCommand();
			
				if(position.equals("townGate")) {
					
						switch(yourChoice) {
						case "c1": talkToGuard(); break;
						case "c2": attackGuard(); break;
						case "c3": crossRoad(); break;
						case "c4": break;
						}
					}
				else if(position.equals("talkGuard")){
						switch(yourChoice) {
						case "c1": talkToGuard(); break;
						case "c2": attackGuard(); break;
						case "c3": crossRoad();break;
						case "c4": break;
						}
				}
					else if(position.equals("attackGuard")) {
						switch(yourChoice) {
						case "c1": talkToGuard(); break;
						case "c2": attackGuard(); break;
						case "c3": crossRoad();break;
						case "c4": break;
						}
					}
					
					else if (position.equals("crossRoad")) {
						switch(yourChoice) {
						case "c1": townGate(); break;
						case "c2": cave(); break;
						case "c3": river();break;
						case "c4": forest();
						
						}
					}
					else if (position.equals("river")) {
						switch(yourChoice) {
						case "c1": crossRoad(); break;
						case "c2": break;
						case "c3": break;
						case "c4": break;
						}
					}
					else if(position.equals("cave")) {
						switch(yourChoice) {
						case "c1": crossRoad(); break;
						case "c2": crossRoad();setSword();break;
						case "c3": crossRoad();break;
						case "c4": break;
						}
						
					}
					else if (position.equals("forest")) {
						switch(yourChoice) {
						case "c1": crossRoad(); break;
						case "c2": crossRoad();break;
						case "c3": ;break;
						case "c4": break;
						}
					}
		}
	}
}


	
	
	

