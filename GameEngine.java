import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameEngine extends JFrame
{
	
	static Integer[] matchPoints = new Integer[45];
	static int firecoord;
    private static final String INITIAL_TEXT = "Nothing Pressed";
    private static final String ADDED_TEXT = " was Pressed";
    private JLabel positionLabel;
    private JLabel Xaxis;
	public static int count = 0;
	public String content;
	static boolean set = false;
	public boolean fire;
	
  //Initializing the buttons
  
    private JButton resetButton;
	public JButton aCarrier;
	public JButton bShip;
	public JButton cSub;
	public JButton dCruiser;
	public JButton eDestroyer;
	public JButton FireButton;
	public JButton RandomizeShips;
	public JButton FinalizeShips;
	
	public static JButton buttonF2[] = new JButton[100];
	public static JButton buttonF[] = new JButton[100];
	public static JButton button[] = new JButton[100];
    static int iterator = 0; 
	
	private static int gridSize = 10;
    static String Action;
	
// Creating Integer Objects to store the set coordinates for the ships
    Integer AircraftCoordinates[] = new Integer[4];
    Integer BattleShipCoordinates[] = new Integer[4];
    Integer SubmarineCoordinates[] = new Integer[4];
    Integer CruiserCoordinates[] = new Integer[4];
    Integer DestroyerCoordinates[] = new Integer[4];
    static Integer ACpoints[] = new Integer[5];
    static Integer BSpoints[] = new Integer[5];
    static Integer SUBpoints[] = new Integer[5];
    static Integer Cpoints[] = new Integer [5];
    static Integer Dpoints[] = new Integer [5];
    
    int ShipLocation[] = new int[20];
    
 // Creating booleans for the ActionListener  
    static boolean AircraftCarrier = false;
	static boolean BattleShip = false;
	static boolean Submarine = false;
	static boolean Cruiser = false;
	static boolean Destroyer = false;
	
	//length of ships are correct
	static boolean FullSizeAC = false;
	static boolean FullSizeBS = false;
	static boolean FullSizeSUB = false;
	static boolean FullSizeC = false;
	static boolean FullSizeD = false;
	
	static boolean HideShipButtons = true;
	static boolean SettingUpShips = true;
	static boolean JumpShip = false;
	
	int ShipX;
	int ShipY; int ASx,ASy,BSx,BSy,SSx,SSy,CSx,CSy,DSx,DSy =0;
	public int z1 = 0;
	public int z2 = 0;
	
	
	static int[] Sendship(int[] ShipLocation) {
		return ShipLocation;
	}
	
	
	

//--------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------

public GameEngine()
    {
        super("Fleet Destroyer Engine");
    }

public void createAndDisplayGUI()
    {       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension (1500,600));
        contentPane.setBackground(Color.WHITE);
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(50,50,260,260);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setForeground(Color.BLACK);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    
        JPanel LeftPanelFire = new JPanel();

		
//Setting the Background and creating JLabels
		contentPane.setBackground(Color.BLACK);
        JLabel x1 = new JLabel("0    1    2    3    4    5    6    7    8   9");
		x1.setFont(new Font("Serif", Font.BOLD, 28));
		contentPane.add(x1);
		x1.setBounds(525,1,530,50);
		x1.setForeground(Color.WHITE);
		
		JLabel x2 = new JLabel("0    1    2    3    4    5    6    7    8   9");
		x2.setFont(new Font("Serif", Font.BOLD, 28));
		contentPane.add(x2);
		x2.setBounds(1025,1,530,50);
		x2.setForeground(Color.WHITE);
		
		JLabel y1 = new JLabel("<html>0<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9</html>");
		y1.setFont(new Font("Serif", Font.BOLD, 31));
		contentPane.add(y1);
		y1.setBounds(960,3,530,500);
		y1.setForeground(Color.WHITE);
		
		JLabel Opp = new JLabel("Target Range");
			Opp.setBounds(645,445,200,50);
			Opp.setFont(new Font("Times New Roman", Font.BOLD, 26));
			contentPane.add(Opp);
			Opp.setForeground(Color.RED);
				
		JLabel Home = new JLabel("Your Ships");
			Home.setBounds(1170,445,200,50);
			Home.setFont(new Font("Times New Roman", Font.BOLD, 26));
			Home.setForeground(Color.green);
			contentPane.add(Home);
			
        //Tells your coordinate pressed on your ships grid
        JPanel labelPanel = new JPanel();
        positionLabel = new JLabel();
        positionLabel.setFont(new Font("Algerian", Font.BOLD, 22));
        labelPanel.setBounds(0, 100, 100, 100);
        
        //Chat
		JTextField ChatBox = new JTextField("Chat!",5);
		ChatBox.setBounds(100,450,300,100);
		ChatBox.setColumns(10);	
		ChatBox.setVisible(false);
		ChatBox.setFont(new Font("Algerian", Font.ROMAN_BASELINE, 36));
		ChatBox.setHorizontalAlignment(SwingConstants.CENTER);
		ChatBox.setBackground(Color.LIGHT_GRAY);
			
//--------------------------------------------------------------------------------------------		
//--------------------------------------------------------------------------------------------	

        	
//left side panel buttons
        resetButton = new JButton("Reset");
		aCarrier = new JButton("Set Aircraft Carrier");
		bShip = new JButton("Set BattleShip");
		cSub = new JButton("Set Submarine");
		dCruiser = new JButton("Set Cruiser");
		eDestroyer = new JButton("Set Destroyer");
		RandomizeShips = new JButton("Randomize Ships");
		FinalizeShips = new JButton("Finalize Ships");
		FireButton = new JButton("Fire on Location");
		FireButton.setBackground(Color.red);

//left panel graphics
		labelPanel.add(positionLabel);
		//leftPanel.add(labelPanel);
		
		JPanel buttonLeftPanel = new JPanel();
		buttonLeftPanel.setLayout(new BoxLayout(buttonLeftPanel, BoxLayout.Y_AXIS));		
		
		leftPanel.add(buttonLeftPanel);
		contentPane.add(leftPanel);
		contentPane.add(ChatBox);

		
		
		
		
		 /*Dimension D = new Dimension();
		 D = aCarrier.getPreferredSize();
		 resetButton.setPreferredSize(new Dimension (130,26));
		 System.out.print(D);*/
		 
		buttonLeftPanel.add(resetButton);
		buttonLeftPanel.add(aCarrier);
		buttonLeftPanel.add(bShip);
		buttonLeftPanel.add(cSub);
		buttonLeftPanel.add(dCruiser);
		buttonLeftPanel.add(eDestroyer);
		buttonLeftPanel.add(RandomizeShips);
		buttonLeftPanel.add(FinalizeShips);
		buttonLeftPanel.add(FireButton);
		buttonLeftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
//--------------------------------------------------------------------------------------------		
//--------------------------------------------------------------------------------------------			
			
		//protocol for placing the Aircraft Carrier
	aCarrier.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = true;
					BattleShip = false;
					Submarine = false;
					Cruiser = false;
					Destroyer = false;
					
					if(AircraftCoordinates[2] != null){
						Reset(AircraftCoordinates,5);
						FullSizeAC = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your AircraftCarrier (5 spaces long)");

					;}
				});
	
		//protocol for placing the Battleship
	bShip.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = true;
					Submarine = false;
					Cruiser = false;
					Destroyer = false;
					
					if(BattleShipCoordinates[2] != null){
						Reset(BattleShipCoordinates,4);
						FullSizeBS = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your BattleShip (4 spaces long)");
				}});
		
		//protocol for placing the Submarine
	cSub.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = false;
					Submarine = true;
					Cruiser = false;
					Destroyer = false;
					
					if(SubmarineCoordinates[2] != null){
						Reset(SubmarineCoordinates,3);
						FullSizeSUB = false;
					}	
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Submarine (3 spaces long)");
				}});
		
		//protocol for placing the Cruiser
	dCruiser.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = false;
					Submarine = false;
					Cruiser = true;
					Destroyer = false;
					
					if(CruiserCoordinates[2] != null){
						Reset(CruiserCoordinates,3);
						FullSizeC = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Cruiser (3 spaces long)");
				}});

		//protocol for placing the Destroyer
	eDestroyer.addActionListener(new ActionListener() 

				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = false;
					Submarine = false;
					Cruiser = false;
					Destroyer = true;
					
					if(DestroyerCoordinates[2] != null){
						Reset(DestroyerCoordinates,2);
						FullSizeD = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Destroyer (2 spaces long)");
					}});

//-------------------------------------------------------------------
//-------------------------------------------------------------------		
					//Randomly places ships on board (not finished)

//Can't put any ships on the board b4 using hard wire it wont move the black boxes
RandomizeShips.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae){
		
		Reset(AircraftCoordinates,5);
		Reset(BattleShipCoordinates,4);
		Reset(SubmarineCoordinates,3);
		Reset(CruiserCoordinates,3);
		Reset(DestroyerCoordinates,2);
		
		int i = 0;
		while( i<5){
			
			Random rand = new Random();
			int RxCoor1 = rand.nextInt(9);
			int RxCoor2 = rand.nextInt(9);
			int RyCoor1 = rand.nextInt(9);
			int RyCoor2 = rand.nextInt(9);
			
			if(i == 0){
					//aCarrier(RxCoor1,RyCoor1);
					//aCarrier(RxCoor2,RyCoor2);
					AircraftCarrier = true;
					takeCoordinates(0, 0, AircraftCoordinates, FullSizeAC,4);
					takeCoordinates(4, 0, AircraftCoordinates, FullSizeAC,4);
					
					if(FullSizeAC == true){
						FillInShip(AircraftCoordinates,5, ACpoints);
						i=1;
					}		
			}
			
			if(i == 1){
				
					//bBattleShip(RxCoor1,RyCoor1);
					//bBattleShip(RxCoor2,RyCoor2);
					BattleShip = true;
					takeCoordinates(0, 1, BattleShipCoordinates, FullSizeBS,3 );
					takeCoordinates(3, 1, BattleShipCoordinates, FullSizeBS,3 );;
					
					if(FullSizeBS == true){
						FillInShip(BattleShipCoordinates,4, BSpoints);
						i=2;
					}		
			}
			
			if(i == 2){
					//cSubmarine(RxCoor1,RyCoor1);
					//cSubmarine(RxCoor2,RyCoor2);
					Submarine = true;
					takeCoordinates(0, 2, SubmarineCoordinates, FullSizeSUB,2 );
					takeCoordinates(2, 2, SubmarineCoordinates, FullSizeSUB,2 );
					
					if(FullSizeSUB == true){
						FillInShip(SubmarineCoordinates,3, SUBpoints);
						i=3;
						}		
			}
		
			if(i == 3){
					//dCruiser(RxCoor1,RyCoor1);
					//dCruiser(RxCoor2,RyCoor2);
					Cruiser = true;
					takeCoordinates(0, 3, CruiserCoordinates, FullSizeC,2 );
					takeCoordinates(2, 3, CruiserCoordinates, FullSizeC,2 );
						
					if(FullSizeC == true){
						FillInShip(CruiserCoordinates,3, Cpoints);
						i=4;
						}		
			}
			
			if(i == 4){
					//eDestroyer(RxCoor1,RyCoor1);
					//eDestroyer(RxCoor2,RyCoor2);
					Destroyer = true;
					takeCoordinates(0, 4, DestroyerCoordinates, FullSizeD,1 );
					takeCoordinates(1, 4, DestroyerCoordinates, FullSizeD,1 );
					
						
					if(FullSizeD == true){
						FillInShip(DestroyerCoordinates,2, Dpoints);
						i=5;
						}		
			} 
		}
	}});

//-------------------------------------------------------------------	
//-------------------------------------------------------------------			
	
	//Firing Functionality
		
// JTEXTFIELD FOCUS,WHERE WE ENTER COORDINATES	
		JTextField textField = new JTextField("Enter Coordiantes to Fire!",5);
		textField.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 14));
		leftPanel.add(textField);
		textField.requestFocusInWindow();
		textField.setColumns(10);
		
		
		fire = true;
		textField.requestFocusInWindow();
		
		textField.setVisible(false);
		FireButton.setVisible(false);
		
		//clear text field and re populates when focus lost
		textField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					textField.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE,36));
					textField.setText("");
		      }
		      public void focusLost(FocusEvent e) {
		    	  //textField.setText("Enter Fire Coordinates Here");
		      }});
		
		
		//grabs coordinates for fire box
		textField.addActionListener(new ActionListener()
		{
		
		public void actionPerformed(ActionEvent event)
			{
			content = textField.getText();
			System.out.print(content);
			}
		});
		
		//button for firing on coordinates
		FireButton.addActionListener(new ActionListener()
		{
		
		public void actionPerformed(ActionEvent event) {
			//Client.setFire(textField.getText());
			System.out.println("The entered text is: " + textField.getText());
			String FireCoordinates = textField.getText();
			FireCoordinates = FireCoordinates.replace(",", "").replaceAll(" ", "");
			int firecoordx = Integer.parseInt(FireCoordinates);
			///EDIT HERE NO PARSING NEED TO GET INDIVIDUAL CHARACTERS MULTIPLY AND ADDD
			//ALSO MAKE SURE THROWSA EXCEPTION IF NUMBERS MORE THAN TWO DIGITS
			
			//*****buttonF[firecoordx].setBackground(Color.black);
			textField.setText("");
			//buttonF2[firecoordx].setBackground(Color.green);
			//textField.setText("");
			//******delete later****
			content = textField.getText();
			System.out.print(content);
			}

		
		});
		
//----------------------------------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------------------------------		
				// Brings back a button after the ship has been set on the board (more to come)

		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				aCarrier.setVisible(true);
				bShip.setVisible(true);
				cSub.setVisible(true);
				dCruiser.setVisible(true);
				eDestroyer.setVisible(true);
				
			}
		}
				);
//-------------------------------------------------------------------
//-------------------------------------------------------------------	
		//protocol for locking in ships

	FinalizeShips.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent ae){
				
				if (FullSizeAC == true && FullSizeBS == true && FullSizeSUB == true 
						&& FullSizeC == true && FullSizeD == true){
					
						aCarrier.setVisible(false);
						bShip.setVisible(false);
						cSub.setVisible(false);
						dCruiser.setVisible(false);
						eDestroyer.setVisible(false);
						
						AircraftCarrier = false;
						BattleShip = false;
						Submarine = false;
						Cruiser = false;
						Destroyer = false;
						
						leftPanel.setBounds(150,50,200,100);
						
						FinalizeShips.setVisible(false);
						textField.setVisible(true);
						ChatBox.setVisible(true);
						FireButton.setVisible(true);
						resetButton.setVisible(false);
						RandomizeShips.setVisible(false);
						
						
						
					
					//puts all ship coordinates into 1 array 
						
						JOptionPane.showMessageDialog(null, "Your ships are set load the cannons!!");
							int x = 0;
							for(int z = 0; z < 20; z++){
							
								if(x == 4)
								{x = 0;}
								
								if (z<4){
									ShipLocation[z] = AircraftCoordinates[x];
									x++;
								}
								else if(z>=4 && z<8){
									ShipLocation[z] = BattleShipCoordinates[x];
									x++;
								}
								else if(z>=8 && z<12){
									ShipLocation[z] = SubmarineCoordinates[x];
									x++;
								}
								else if(z>=12 && z<16){
									ShipLocation[z] = CruiserCoordinates[x];
									x++;
								}
								else if(z>=16 && z<20){
									ShipLocation[z] = DestroyerCoordinates[x];
									x++;
								}	
							}
							String shipfinalcoordinates = "";
							for(int i = 0 ; i <20; i++){
								if(i==0){
									shipfinalcoordinates += "AircraftCarrier ";
								}
								if(i==4){
									shipfinalcoordinates += " BattleShip ";
								}
								if(i==8){
									shipfinalcoordinates += " Submarine ";
								}
								if(i==12){
									shipfinalcoordinates += " Cruiser ";
								}
								if(i==16){
									shipfinalcoordinates += " Destroyer ";
								}
	
								shipfinalcoordinates += "" + ShipLocation[i];
								
							}
							setFinalCoordinates(shipfinalcoordinates);
			}
				
				else{
					JOptionPane.showMessageDialog(null, "You haven't set up all of your ships yet");
				}
				
				set=true;
		}
	}
);
//-------------------------------------------------------------------
//-------------------------------------------------------------------
		
		
//firing Grid	
		JPanel FirePanel = new JPanel();
			FirePanel.setBounds(520,50,400,400);

		if(iterator == 0){
			for (int a = 0; a < gridSize; a++)
			{
				for (int b = 0; b < gridSize; b++)
				{
					int y = b*10;
					int z = y+a;
					
				    buttonF[z] = new JButton();
					
				    buttonF[z].setPreferredSize(new Dimension(35,35));
				    buttonF[z].setOpaque(true);
				    buttonF[z].setBorderPainted(false);
				    buttonF[z].setBackground(Color.BLUE);
				    
				    buttonF[z].setActionCommand( b + "," + a );
				    
				    FirePanel.add(buttonF[z]);
					

						}
				}
			iterator++;
		}
			
		if(iterator == 1){	
			
			for (int a = 0; a < gridSize; a++)
			{
				for (int b = 0; b < gridSize; b++)
				{
					int y = b*10;
					int z = y+a;
					
				    buttonF2[z] = new JButton();
					
				    buttonF2[z].setPreferredSize(new Dimension(35,35));
				    buttonF2[z].setOpaque(true);
				    buttonF2[z].setBorderPainted(false);
				    buttonF2[z].setBackground(Color.GREEN);
				    
				    buttonF2[z].setActionCommand( b + "," + a );
				    
				    FirePanel.add(buttonF[z]);
					

						}
				}
		}
//-------------------------------------------------------------------
//-------------------------------------------------------------------
			
			

//Home ships grid
		JPanel buttonPanel = new JPanel();
			buttonPanel.setBounds(1020,50,400,400);
			for (int i = 0; i < gridSize; i++)
			{
				for (int j = 0; j < gridSize; j++)
				{
	
					int y = j *10;
					int z = y+i;
					
				    button[z] = new JButton();
					
				    button[z].setPreferredSize(new Dimension(35,35));
				    button[z].setOpaque(true);
				    button[z].setBorderPainted(false);
				    button[z].setBackground(Color.BLUE);

					button[z].setPreferredSize(new Dimension(35,35));

					button[z].setBackground(Color.BLUE);
					button[z].setOpaque(true);
					
					button[z].setActionCommand( j + "," + i );
					
					buttonPanel.add(button[z]);
//-------------------------------------------------------------------
//-------------------------------------------------------------------			
					
//Action Listener for Home Ships Grid	
	
button[z].addActionListener(new ActionListener()
{
		
	
			

	public void actionPerformed(ActionEvent ae)
						{
							if(AircraftCarrier == true){
							 count = count +1;
							JButton but = (JButton) ae.getSource();
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++)
								{
									intArray[i] = Integer.parseInt(strArray[i]);
									ASx = intArray[0];
									ASy = intArray[1];
								}
								//aCarrier(ASx,ASy);
								takeCoordinates(ASx, ASy, AircraftCoordinates, FullSizeAC,4);
								
								z1 = (ASx*10)+ASy;
								button[z1].setBackground(Color.black);
								
								if(count == 2 && FullSizeAC == true){
									AircraftCarrier = false;
									count = 0;
									FillInShip(AircraftCoordinates,5, ACpoints);
										if(JumpShip == false){
											JOptionPane.showMessageDialog(null, "Your AircraftCarrier is in position");
											aCarrier.setVisible(false);
										}
									}
									
								else if(count == 2 && FullSizeAC != true){
									JOptionPane.showMessageDialog(null, "Repick your second coordinate or press Set Aircraft Carrier to start again");
									count = 1;
									button[z1].setBackground(Color.BLUE);
								}
							
							}
							
							
							else if(BattleShip == true){
								count = count + 1;
							JButton but = (JButton) ae.getSource(); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									BSx = intArray[0];
									BSy = intArray[1];
									
								}
									//bBattleShip(BSx,BSy);
									takeCoordinates(BSx, BSy, BattleShipCoordinates, FullSizeBS,3 );
									
									z1 = ((BSx*10)+BSy);
									
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeBS == true){
										BattleShip = false;	
										count = 0;
										FillInShip(BattleShipCoordinates,4, BSpoints);
										if(JumpShip == false){
											JOptionPane.showMessageDialog(null, "Your BattleShip is in Position");
											bShip.setVisible(false);
										}
									}
									else if(count == 2 && FullSizeBS != true){
										JOptionPane.showMessageDialog(null, "Repick your second coordinate or press Set BattleShip to start again");
										count = 1;
										button[z1].setBackground(Color.BLUE);
									}
							}
							else if (Submarine == true){
								count = count + 1;
							JButton but = (JButton) ae.getSource(); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									SSx = intArray[0];
									SSy = intArray[1];	

								}
									//cSubmarine(SSx,SSy);
									takeCoordinates(SSx, SSy, SubmarineCoordinates, FullSizeSUB,2 );
									
									z1 = (SSx*10)+SSy;
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeSUB == true){
										Submarine = false;	
										count = 0;
										FillInShip(SubmarineCoordinates,3,SUBpoints);
										if(JumpShip == false){
											JOptionPane.showMessageDialog(null, "Your Submarine is in Position");
											cSub.setVisible(false);
										}
									}
									else if(count == 2 && FullSizeSUB != true){
										JOptionPane.showMessageDialog(null, "Repick your second coordinate or press Set Submarine to start again");
										count = 1;
										button[z1].setBackground(Color.BLUE);
									}
							}
							else if (Cruiser == true){
								count = count+1;
							JButton but = (JButton) ae.getSource();
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									CSx = intArray[0];
									CSy = intArray[1];

								}
									//dCruiser(CSx,CSy);
									takeCoordinates(CSx, CSy, CruiserCoordinates, FullSizeC,2 );
							
									z1 = (CSx*10)+CSy;
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeC == true){
										Cruiser = false;
										count = 0;
										FillInShip(CruiserCoordinates,3,Cpoints);
										if(JumpShip == false){
											JOptionPane.showMessageDialog(null, "Your Cruiser is in position");
											dCruiser.setVisible(false);
										}
									}
									else if(count == 2 && FullSizeC != true){
										JOptionPane.showMessageDialog(null, "Repick your second coordinate or press Set Cruiser to start again");
										count = 1;
										button[z1].setBackground(Color.BLUE);
									}
							}
							else if (Destroyer == true){
								count = count +1;
							JButton but = (JButton) ae.getSource();
							//but.setBackground(Color.gray); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									DSx = intArray[0];
									DSy = intArray[1];

								}
									//eDestroyer(DSx,DSy);
									takeCoordinates(DSx, DSy, DestroyerCoordinates, FullSizeD,1 );
									
									z1 = (DSx*10)+DSy;
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeD == true){
										Destroyer = false;
										count = 0;
										FillInShip(DestroyerCoordinates,2,Dpoints);
										if(JumpShip == false){
											JOptionPane.showMessageDialog(null, "Your Destoyer is in position");
											eDestroyer.setVisible(false);
										}
									}
									else if(count == 2 && FullSizeD != true){
										JOptionPane.showMessageDialog(null, "Repick your second coordinate or press Set Destroyer to start again");
										count = 1;
										button[z1].setBackground(Color.BLUE);
									}
							}
							
							
							/*JButton but = (JButton) ae.getSource();
							positionLabel.setText(but.getActionCommand() + ADDED_TEXT); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++)
								{
									intArray[i] = Integer.parseInt(strArray[i]);
									x = intArray[0];
									y = intArray[1];
								}
									aCarrier(x,y);*/
							
			
						}});
				
				
					
				}
        }
						contentPane.add(buttonPanel);
						contentPane.add(FirePanel);
						setContentPane(contentPane);
						pack();
						setLocationByPlatform(true);
						setVisible(true);
						//setExtendedState(JFrame.MAXIMIZED_BOTH);
						
						/*
						ImageIcon BackgroundWater = new ImageIcon("Background water.jpg"); // load the image to a imageIcon
						Image image = BackgroundWater.getImage(); // transform it 
						Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
						BackgroundWater = new ImageIcon(newimg);  // transform it back			
						contentPane.add(BackgroundWater)
						 */
					    
    }
//-------------------------------------------------------------------
//-------------------------------------------------------------------
//-------------------------------------------------------------------


					//Places ship coordinates in their arrays
// Almost there just can't get this method to change the FullSize bo0leans ie. FullsizeAC wont change when Full changes
public void takeCoordinates(int X, int Y, Integer Coordinates[], boolean Full, int Size ){
	
	if(Coordinates[0] == null){
		Coordinates[0] = X;
		Coordinates[1] = Y;
	}else{
			Coordinates[2] = X;
			Coordinates[3] = Y;
	}
	
	if (Coordinates[2] != null){
		int x1 = Coordinates[0];
		int y1 = Coordinates[1];
		int x2 = Coordinates[2];
		int y2 = Coordinates[3];
		int DeltaY = y2-y1;
		int DeltaX = x2-x1;
			
			if((x1 == x2) && (Math.abs(DeltaY)==Size)){ 
				
				Full = true;
				System.out.println("Array" + Arrays.toString(Coordinates));
				//System.out.println("DY"+DeltaX+","+DeltaY);
				
				//Temporary until we get Full to manipulate the actual booleans		
				if (AircraftCarrier == true){FullSizeAC = true;}
				if(BattleShip == true){FullSizeBS = true;}
				if(Submarine == true){FullSizeSUB = true;}
				if(Cruiser == true){FullSizeC = true;}
				if(Destroyer == true){FullSizeD = true;}
			}
			else if((y1==y2) && (Math.abs(DeltaX)==Size)){
				
				Full = true;
				System.out.println("Array" + Arrays.toString(Coordinates));
				//System.out.println("DX"+ DeltaX+","+DeltaY);
				
				//Temporary until we get Full to manipulate the actual booleans		
				if (AircraftCarrier == true){FullSizeAC = true;}
				if(BattleShip == true){FullSizeBS = true;}
				if(Submarine == true){FullSizeSUB = true;}
				if(Cruiser == true){FullSizeC = true;}
				if(Destroyer == true){FullSizeD = true;}
			}		
	}
}


/// HADIIIIII
static String shiplocations = "";
public void setFinalCoordinates(String fcoord){
	shiplocations = fcoord;
}
public static String getFinalCoordinates(){
	return shiplocations;
}
//////Hadi


		//Places ship on board and checks for overlap (might want to split to two methods)
public static void FillInShip(Integer[] Coor,int x, Integer[] blocks){
	Integer[] points = blocks;
	//int x = points.length;
	
	JumpShip = false;
	
	for (int i = 0;i<45;i++){
			matchPoints[i] = null;
		}
	
	// right
		if( Coor[1] == Coor[3] && Coor[0]<Coor[2]){
					
							for(int a = 0; a<x;a++){
								int t = (Coor[0]*10) + Coor[1];
								t = t + a*10;
								button[t].setBackground(Color.black);
								points[a] = t;
							}
			}
		
		// left
		if( Coor[1] == Coor[3] && Coor[2]<Coor[0]){
					
							for(int a = 0; a<x;a++){
								int t = (Coor[2]*10) + Coor[1];
								t = t + a*10;
								button[t].setBackground(Color.black);
								points[a] = t;
							}
			}

		// down
		if( Coor[0] == Coor[2] && Coor[1]<Coor[3]){
					
							for(int a = 0; a<x;a++){
								int t = (Coor[0]*10) + Coor[1];
								t = t + a;
								button[t].setBackground(Color.black);
								points[a] = t;
							}
				}
		// up
		if( Coor[0] == Coor[2] && Coor[1]>Coor[3]){
					
							for(int a = 0; a<x;a++){
								int t = (Coor[0]*10) + Coor[3];
								t = t + a;
								button[t].setBackground(Color.black);
								points[a] = t;
							}
		}
		int match = 0;
		int matchAC = 0;
		int matchBS = 0;
		int matchSUB = 0;
		int matchC = 0;
		int matchD = 0;
		
		//static Integer[] matchPoints = new Integer[45];
		int spot = 0;
		
			for (int a = 0; a<5;a++){
				for (int b = 0;b<5;b++){
					if((points[a] == ACpoints[b]) && (points[a] != null) ){
						matchAC = matchAC + 1;
							spot = a;
							matchPoints[spot] = points[a];
					}
					if((points[a] == BSpoints[b]) && (points[a] != null) ){
						matchBS = matchBS + 1;
							spot = 10+a;
							matchPoints[spot] = points[a];
					}	
					if((points[a] == SUBpoints[b]) && (points[a] != null) ){
						matchSUB = matchSUB + 1;	
							spot = 20+a;
							matchPoints[spot] = points[a];
					}	
					if((points[a] == Cpoints[b]) && (points[a] != null) ){
						matchC = matchC + 1;	
							spot = 30+a;
							matchPoints[spot] = points[a];
					}
					if((points[a] == Dpoints[b]) && (points[a] != null) ){
						matchD = matchD + 1;
							spot = 40+a;
							matchPoints[spot] = points[a];
					}
					
				}
			}
			match = matchAC+matchBS+matchSUB+matchC+matchD;
			if(match != 0 && match !=x){
				JumpShip = true;
				Reset(Coor,x);
				blocks = null;
				
			
			
			if (matchAC == x){
				for(int i = 0;i<5;i++){
					matchPoints[i]= null;
				}
			}
			if (matchBS == x){
				for(int i = 10;i<15;i++){
					matchPoints[i]= null;
				}
				
			}
			if (matchSUB == x){
				for(int i = 20;i<25;i++){
					matchPoints[i]= null;
				}
			}
			if (matchC == x){
				for(int i = 30;i<35;i++){
					matchPoints[i]= null;
				}
			}
			if (matchD == x || matchD ==0){
				for(int i = 40;i<45;i++){
					matchPoints[i]= null;
				}
			}
			
			for (int i = 0;i<45;i++){
				if(matchPoints[i]!=null){
					button[matchPoints[i]].setBackground(Color.black);
					matchPoints[i] = null;
				}
			}
						JOptionPane.showMessageDialog(null, "Abondon Ship!");
			}
			//Thread.flush();
}

		//Takes ship off the board and replaces the 'overlap block' for the other ship
public static void Reset(Integer[] Coor, int x){
		
	if((Coor[1]!=null) && (Coor[2]!=null)){
	
		// right
	if( Coor[1] == Coor[3] && Coor[0]<Coor[2]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[0]*10) + Coor[1];
							t = t + a*10;
							button[t].setBackground(Color.BLUE);
						}
		}
	
	// left
	if( Coor[1] == Coor[3] && Coor[2]<Coor[0]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[2]*10) + Coor[1];
							t = t + a*10;
							button[t].setBackground(Color.BLUE);
						}
		}

	// down
	if( Coor[0] == Coor[2] && Coor[1]<Coor[3]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[0]*10) + Coor[1];
							t = t + a;
							button[t].setBackground(Color.BLUE);
						}
			}
	// up
	if( Coor[0] == Coor[2] && Coor[1]>Coor[3]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[0]*10) + Coor[3];
							t = t + a;
							button[t].setBackground(Color.BLUE);
						}
			}
	

		for(int c = 0;c<4;c++){
			Coor[c] = null;
		} 
		count = 0;
	}
}





//-------------------------------------------------------------------
//-----------------------MAINNNNNNNNNN-------------------------------
//-------------------------------------------------------------------
//-------------------------------------------------------------------
	
public static void main(String[] args){
         SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new GameEngine().createAndDisplayGUI();

            }

        });
   

    }
}
