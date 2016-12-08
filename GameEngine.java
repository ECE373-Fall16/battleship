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
    static  boolean AircraftCarrier = false;
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
        //contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPane.setBackground(Color.WHITE);
		JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
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
		y1.setFont(new Font("Serif", Font.BOLD, 40));
		contentPane.add(y1);
		y1.setBounds(955,6,530,500);
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
//--------------------------------------------------------------------------------------------		
//--------------------------------------------------------------------------------------------	
		
        
        JPanel labelPanel = new JPanel();
        positionLabel = new JLabel(INITIAL_TEXT, JLabel.CENTER);
        
		JPanel buttonLeftPanel = new JPanel();
		buttonLeftPanel.setLayout(new BoxLayout(buttonLeftPanel, BoxLayout.Y_AXIS));
		
		leftPanel.setBounds(50,50,260,300);
		
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

//left panel graphics
		labelPanel.add(positionLabel);
		buttonLeftPanel.add(resetButton);
		leftPanel.add(labelPanel);
		leftPanel.add(buttonLeftPanel);
		contentPane.add(leftPanel);
		buttonLeftPanel.add(aCarrier);
		buttonLeftPanel.add(bShip);
		buttonLeftPanel.add(cSub);
		buttonLeftPanel.add(dCruiser);
		buttonLeftPanel.add(eDestroyer);
		buttonLeftPanel.add(RandomizeShips);
		buttonLeftPanel.add(FinalizeShips);
		buttonLeftPanel.add(FireButton);
		
// JTEXTFIELD FOCUS,WHERE WE ENTER COORDINATES	
		JTextField textField = new JTextField("Enter Coordiantes to Fire!",5);
		leftPanel.add(textField);
		textField.requestFocusInWindow();
		textField.setColumns(10);
		content = textField.getText();
		fire = true;
		textField.requestFocusInWindow();
		
		textField.setVisible(false);
		FireButton.setVisible(false);
		
		
		textField.addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent e) {
		  textField.setText("");
        
      }
		
		

      public void focusLost(FocusEvent e) {
        
      }
          });
		
		
		
			FireButton.addActionListener(new ActionListener()
		{
		
		public void actionPerformed(ActionEvent event)
			{
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
			//******delete later*****
			}
		
		}
		
		);
		
//----------------------------------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------------------------------		
	

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
							
							FinalizeShips.setVisible(false);
							textField.setVisible(true);
							FireButton.setVisible(true);
							
							resetButton.setVisible(false);
							
						
						//puts all ship coordinates into 1 array 
							
							JOptionPane.showMessageDialog(null, "Your ships are set begin game play");
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

//---------------------------------------------------------------

 
		
		//protocol for placing the Aircraft Carrier
	aCarrier.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = true;
					BattleShip = false;
					Submarine = false;
					Cruiser = false;
					Destroyer = false;
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your AircraftCarrier (5 spaces long)");

					
					if(AircraftCoordinates[2] != null){
						Reset(AircraftCoordinates,5);
						FullSizeAC = false;
					}

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
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your BattleShip (4 spaces long)");
					

					if(BattleShipCoordinates[2] != null){
						Reset(BattleShipCoordinates,4);
						FullSizeBS = false;
					}
				
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
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Submarine (3 spaces long)");
					
					if(SubmarineCoordinates[2] != null){
						Reset(SubmarineCoordinates,3);
						FullSizeSUB = false;
					}
					
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
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Cruiser (3 spaces long)");
					
					if(CruiserCoordinates[2] != null){
						Reset(CruiserCoordinates,3);
						FullSizeC = false;
					}
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
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Destroyer (2 spaces long)");
					
					if(DestroyerCoordinates[2] != null){
						Reset(DestroyerCoordinates,2);
						FullSizeD = false;
					}
					}});
//-------------------------------------------------------------------
//-------------------------------------------------------------------
	
	RandomizeShips.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent ae){
			
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
						aCarrier(0,0);
						aCarrier(4,0);
						
						if(FullSizeAC == true){
							FillInShip(AircraftCoordinates,5, ACpoints);
							i=1;
						}		
				}
				
				if(i == 1){
					
						//bBattleShip(RxCoor1,RyCoor1);
						//bBattleShip(RxCoor2,RyCoor2);
						bBattleShip(0,1);
						bBattleShip(3,1);
						
						if(FullSizeBS == true){
							FillInShip(BattleShipCoordinates,4, BSpoints);
							i=2;
						}		
				}
				
				if(i == 2){
						//cSubmarine(RxCoor1,RyCoor1);
						//cSubmarine(RxCoor2,RyCoor2);
						cSubmarine(0,2);
						cSubmarine(2,2);
						
						if(FullSizeSUB == true){
							FillInShip(SubmarineCoordinates,3, SUBpoints);
							i=3;
							}		
				}
			
				if(i == 3){
						//dCruiser(RxCoor1,RyCoor1);
						//dCruiser(RxCoor2,RyCoor2);
						dCruiser(0,3);
						dCruiser(2,3);
							
						if(FullSizeC == true){
							FillInShip(CruiserCoordinates,3, Cpoints);
							i=4;
							}		
				}
				
				if(i == 4){
						//eDestroyer(RxCoor1,RyCoor1);
						//eDestroyer(RxCoor2,RyCoor2);
						eDestroyer(0,4);
						eDestroyer(1,4);
						
							
						if(FullSizeD == true){
							FillInShip(DestroyerCoordinates,2, Dpoints);
							i=5;
							}		
				} 
			}
		}});
	
//-------------------------------------------------------------------	
//-------------------------------------------------------------------	

		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				positionLabel.setText(INITIAL_TEXT);
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
			
			

//place ships grid
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
//-------------------------------------------------------------------
//-------------------------------------------------------------------			
					
					button[z].addActionListener(new ActionListener()
					{
				
	public void actionPerformed(ActionEvent ae)
						{
							if(AircraftCarrier == true){
							 count = count +1;
							JButton but = (JButton) ae.getSource();
							positionLabel.setText(
									but.getActionCommand() + ADDED_TEXT); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++)
								{
									intArray[i] = Integer.parseInt(strArray[i]);
									ASx = intArray[0];
									ASy = intArray[1];
								}
								aCarrier(ASx,ASy);
								
								z1 = (ASx*10)+ASy;
								button[z1].setBackground(Color.black);
								
								if(count == 2 && FullSizeAC == true){
									JOptionPane.showMessageDialog(null, "Your ship is being built!");
									AircraftCarrier = false;
									aCarrier.setVisible(false);
									count = 0;
									FillInShip(AircraftCoordinates,5, ACpoints);
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
							positionLabel.setText(
									but.getActionCommand() + ADDED_TEXT); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									BSx = intArray[0];
									BSy = intArray[1];
									
								}
									bBattleShip(BSx,BSy);
									z1 = ((BSx*10)+BSy);
									
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeBS == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										BattleShip = false;	
										bShip.setVisible(false);
										count = 0;
										FillInShip(BattleShipCoordinates,4, BSpoints);
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
							positionLabel.setText(
									but.getActionCommand() + ADDED_TEXT); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									SSx = intArray[0];
									SSy = intArray[1];	

								}
									cSubmarine(SSx,SSy);
									
									z1 = (SSx*10)+SSy;
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeSUB == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										Submarine = false;	
										cSub.setVisible(false);
										count = 0;
										FillInShip(SubmarineCoordinates,3,SUBpoints);
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
							positionLabel.setText(
									but.getActionCommand() + ADDED_TEXT); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									CSx = intArray[0];
									CSy = intArray[1];

								}
									dCruiser(CSx,CSy);
									
									z1 = (CSx*10)+CSy;
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeC == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										Cruiser = false;
										dCruiser.setVisible(false);
										count = 0;
										FillInShip(CruiserCoordinates,3,Cpoints);
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
							positionLabel.setText(
									but.getActionCommand() + ADDED_TEXT); 
							Action = but.getActionCommand();
							String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++){
									intArray[i] = Integer.parseInt(strArray[i]);
									DSx = intArray[0];
									DSy = intArray[1];

								}
									eDestroyer(DSx,DSy);
									
									z1 = (DSx*10)+DSy;
									button[z1].setBackground(Color.black);
									
									if(count == 2 && FullSizeD == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										Destroyer = false;
										eDestroyer.setVisible(false);
										count = 0;
										FillInShip(DestroyerCoordinates,2,Dpoints);
									}
									else if(count == 2 && FullSizeD != true){
										JOptionPane.showMessageDialog(null, "Repick your second coordinate or press Set Destroyer to start again");
										count = 1;
										button[z1].setBackground(Color.BLUE);
									}
							}
							
							{
							JButton but = (JButton) ae.getSource();
							positionLabel.setText(
									but.getActionCommand() + ADDED_TEXT); 
							Action = but.getActionCommand();
							/*String [] strArray = Action.split(",");
							int [] intArray = new int [strArray.length];
						
							for (int i = 0; i < strArray.length; i++)
								{
									intArray[i] = Integer.parseInt(strArray[i]);
									x = intArray[0];
									y = intArray[1];
								}
									aCarrier(x,y);*/
							}
							
							
							
							
							
                }});
				buttonPanel.add(button[z]);
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

/*static void fillShip(Integer array[],int x){
	if( array[0] == array[2]){
		if(array[1]<array[3]){
			for(int a = 1; a>x;a++){
				int t = (array[0]*10) + array[1];
				t = t + a;
				button[t].setBackground(Color.gray);
			}
		}
	}
	}
	else{
		
	}
	}*/


public void aCarrier(int ASx, int ASy){

		if(AircraftCoordinates[0] == null){
					AircraftCoordinates[0] = ASx;
					AircraftCoordinates[1] = ASy;
				}
		else{
					AircraftCoordinates[2] = ASx;
					AircraftCoordinates[3] = ASy;
		}
			
		
		if (AircraftCoordinates[2] != null){
				int x1 = AircraftCoordinates[0];
				int y1 = AircraftCoordinates[1];
				int x2 = AircraftCoordinates[2];
				int y2 = AircraftCoordinates[3];
				int DeltaY = y2-y1;
				int DeltaX = x2-x1;
				
				
				
				if((x1 == x2) && (Math.abs(DeltaY)==4)){
					FullSizeAC = true;
					System.out.println("Array" + Arrays.toString(AircraftCoordinates));
					System.out.println("DY"+DeltaX+","+DeltaY);
				}
				else if((y1==y2) && (Math.abs(DeltaX)==4)){
					FullSizeAC = true;
					System.out.println("Array" + Arrays.toString(AircraftCoordinates));
					System.out.println("DX"+ DeltaX+","+DeltaY);
				}
		}

}

public void bBattleShip(int BSx, int BSy){
		
	if(BattleShipCoordinates[0] == null){
				BattleShipCoordinates[0] = BSx;
				BattleShipCoordinates[1] = BSy;
			}
	else{
				BattleShipCoordinates[2] = BSx;
				BattleShipCoordinates[3] = BSy;
				
			}
	
		if (BattleShipCoordinates[2] != null){
			int x1 = BattleShipCoordinates[0];
			int y1 = BattleShipCoordinates[1];
			int x2 = BattleShipCoordinates[2];
			int y2 = BattleShipCoordinates[3];
			if ( Math.hypot((x2-x1),(y2-y1)) == 3 ){
				FullSizeBS = true;
			}
		}
	}
public void cSubmarine(int SSx, int SSy){
		
	if(SubmarineCoordinates[0] == null){
				SubmarineCoordinates[0] = SSx;
				SubmarineCoordinates[1] = SSy;
			}
	else{
				SubmarineCoordinates[2] = SSx;
				SubmarineCoordinates[3] = SSy;
			}
	if (SubmarineCoordinates[2] != null){
		int x1 = SubmarineCoordinates[0];
		int y1 = SubmarineCoordinates[1];
		int x2 = SubmarineCoordinates[2];
		int y2 = SubmarineCoordinates[3];
		if ( Math.hypot((x2-x1),(y2-y1)) == 2 ){
			FullSizeSUB = true;
		}
	}
			
	}
public void dCruiser(int CSx, int CSy){
	if(CruiserCoordinates[0] == null){
				CruiserCoordinates[0] = CSx;
				CruiserCoordinates[1] = CSy;
			}
	else{
				CruiserCoordinates[2] = CSx;
				CruiserCoordinates[3] = CSy;
			}
		
	if (CruiserCoordinates[2] != null){
		int x1 = CruiserCoordinates[0];
		int y1 = CruiserCoordinates[1];
		int x2 = CruiserCoordinates[2];
		int y2 = CruiserCoordinates[3];
		if ( Math.hypot((x2-x1),(y2-y1)) == 2 ){
			FullSizeC = true;
		}
	}
	}
public void eDestroyer(int DSx, int DSy){	
	if(DestroyerCoordinates[0] == null){
				DestroyerCoordinates[0] = DSx;
				DestroyerCoordinates[1] = DSy;
			}
	else{
				DestroyerCoordinates[2] = DSx;
				DestroyerCoordinates[3] = DSy;
			}
	if (DestroyerCoordinates[2] != null){
		int x1 = DestroyerCoordinates[0];
		int y1 = DestroyerCoordinates[1];
		int x2 = DestroyerCoordinates[2];
		int y2 = DestroyerCoordinates[3];
		if ( Math.hypot((x2-x1),(y2-y1)) == 1 ){
			FullSizeD = true;
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



public static void FillInShip(Integer[] Coor,int x, Integer[] blocks){
	Integer[] points = blocks;
	//int x = points.length;
	
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
				Reset(Coor,x);
				blocks = null;
				JOptionPane.showMessageDialog(null, "Ships overlapped try again");
			}
			
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
			
			//Thread.flush();
			
}


public static void Reset(Integer[] Coor, int x){
		
	
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
