import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

public class LayoutSetup extends JFrame
{
    private static final String INITIAL_TEXT = "Nothing Pressed";
    private static final String ADDED_TEXT = " was Pressed";
    private JLabel positionLabel;
    private JLabel Xaxis;
	public int count = 0;
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
	public JButton FinalizeShips;
   
	private static int gridSize = 10;
    static String Action;
	
// Creating Integer Objects to store the set coordinates for the ships
    Integer AircraftCoordinates[] = new Integer[4];
    Integer BattleShipCoordinates[] = new Integer[4];
    Integer SubmarineCoordinates[] = new Integer[4];
    Integer CruiserCoordinates[] = new Integer[4];
    Integer DestroyerCoordinates[] = new Integer[4];
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
	
	
	static int[] Sendship(int[] ShipLocation) {
		return ShipLocation;
	}
	
	
	

//--------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------

public LayoutSetup()
    {
        super("Layout Example");
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
			System.out.println("The entered text is: " + textField.getText());
			textField.setText("");
			
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
								System.out.println(getFinalCoordinates());	
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
					aCarrier.setBackground(Color.DARK_GRAY);}});
		
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
					bShip.setBackground(Color.DARK_GRAY);}});
		
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
							+ " your Submarine (4 spaces long)");
					cSub.setBackground(Color.DARK_GRAY);}});
		
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
					dCruiser.setBackground(Color.DARK_GRAY);}});

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
					eDestroyer.setBackground(Color.DARK_GRAY);
					}});
//-------------------------------------------------------------------
//-------------------------------------------------------------------
//-------------------------------------------------------------------	
//Right side Grid of buttons
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
			//FirePanel.setLayout(new GridLayout(gridSize, gridSize));
			FirePanel.setBounds(520,50,400,400);
			for (int a = 0; a < gridSize; a++)
			{
				for (int b = 0; b < gridSize; b++)
				{
					JButton buttonF = new JButton();

					buttonF.setPreferredSize(new Dimension(35,35));

					buttonF.setActionCommand( a + "," + b );
					FirePanel.add(buttonF);
					buttonF.setBackground(Color.BLUE);
					
					buttonF.addActionListener(new ActionListener()
					{
				
						public void actionPerformed(ActionEvent ae)
						{
							buttonF.setBackground(Color.RED);
							buttonF.setOpaque(true);
							buttonF.setBorderPainted(false);

							
						}
	                });
						}
				}	
//-------------------------------------------------------------------
//-------------------------------------------------------------------
			
			

//place ships grid
		JPanel buttonPanel = new JPanel();
			//buttonPanel.setLayout(new GridLayout(gridSize, gridSize));
			buttonPanel.setBounds(1020,50,400,400);
			for (int i = 0; i < gridSize; i++)
			{
				for (int j = 0; j < gridSize; j++)
				{
					JButton button = new JButton();

					button.setPreferredSize(new Dimension(35,35));

					button.setBackground(Color.BLUE);
					button.setOpaque(true);
					
					button.setActionCommand( i + "," + j );
//-------------------------------------------------------------------
//-------------------------------------------------------------------			
					
					button.addActionListener(new ActionListener()
					{
				
	public void actionPerformed(ActionEvent ae)
						{
							if(AircraftCarrier == true){
							 count = count +1;
							JButton but = (JButton) ae.getSource();
							//but.setBackground(Color.gray);
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
								if(count == 2 && FullSizeAC == true){
									JOptionPane.showMessageDialog(null, "Your ship is being built!");
									AircraftCarrier = false;
									aCarrier.setVisible(false);
									count = 0;
								}
								else if(count == 2 && FullSizeAC != true){
									JOptionPane.showMessageDialog(null, "You Fucked up repick your second coordinate");
									count = 1;
								}
							
							}
							
							
							else if(BattleShip == true){
								count = count + 1;
							JButton but = (JButton) ae.getSource();
							//but.setBackground(Color.gray);
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
									if(count == 2 && FullSizeBS == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										BattleShip = false;	
										bShip.setVisible(false);

										count = 0;
									}
									else if(count == 2 && FullSizeBS != true){
										JOptionPane.showMessageDialog(null, "You Fucked up repick your second coordinate");
										count = 1;
									}
							}
							else if (Submarine == true){
								count = count + 1;
							JButton but = (JButton) ae.getSource();
							//but.setBackground(Color.gray);
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
									if(count == 2 && FullSizeSUB == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										Submarine = false;	
										cSub.setVisible(false);
										count = 0;
									}
									else if(count == 2 && FullSizeSUB != true){
										JOptionPane.showMessageDialog(null, "You Fucked up repick your second coordinate");
										count = 1;
									}
							}
							else if (Cruiser == true){
								count = count+1;
							JButton but = (JButton) ae.getSource();
							//but.setBackground(Color.gray);
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
									if(count == 2 && FullSizeC == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										Cruiser = false;
										dCruiser.setVisible(false);

										count = 0;
									}
									else if(count == 2 && FullSizeC != true){
										JOptionPane.showMessageDialog(null, "You Fucked up repick your second coordinate");
										count = 1;
									}
									System.out.print(count);
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
									if(count == 2 && FullSizeD == true){
										JOptionPane.showMessageDialog(null, "Your ship is being built!");
										Destroyer = false;
										eDestroyer.setVisible(false);
										count = 0;
									}
									else if(count == 2 && FullSizeD != true){
										JOptionPane.showMessageDialog(null, "You Fucked up repick your second coordinate");
										count = 1;
									}
							}
							
							{
							JButton but = (JButton) ae.getSource();
							//but.setBackground(Color.gray);
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
				buttonPanel.add(button);
            }
        }
						contentPane.add(buttonPanel);
						contentPane.add(FirePanel);
						setContentPane(contentPane);
						pack();
						setLocationByPlatform(true);
						setVisible(true);
    }
//-------------------------------------------------------------------
//-------------------------------------------------------------------
//-------------------------------------------------------------------

public void aCarrier(int ASx, int ASy){

	if(AircraftCoordinates[0] == null){
				AircraftCoordinates[0] = ASx;
				AircraftCoordinates[1] = ASy;
				System.out.println("x1  " + AircraftCoordinates[0]);
				System.out.println("y1  " + AircraftCoordinates[1]);
			}
	else{
				AircraftCoordinates[2] = ASx;
				AircraftCoordinates[3] = ASy;
				System.out.println("x2  " + AircraftCoordinates[2]);
				System.out.println("y2  " + AircraftCoordinates[3]);
	}
		if (AircraftCoordinates[2] != null){
			int x1 = AircraftCoordinates[0];
			int y1 = AircraftCoordinates[1];
			int x2 = AircraftCoordinates[2];
			int y2 = AircraftCoordinates[3];
		if ( Math.hypot((x2-x1),(y2-y1)) == 4 ){
			FullSizeAC = true;
		}
		//if(x1 == x2){
			
		//}
	}

}

public void bBattleShip(int BSx, int BSy){
		
	if(BattleShipCoordinates[0] == null){
				BattleShipCoordinates[0] = BSx;
				BattleShipCoordinates[1] = BSy;
				System.out.println("x1" + " " +BattleShipCoordinates[0]);
				System.out.println("y1" + " " +BattleShipCoordinates[1]);
			}
	else{
				BattleShipCoordinates[2] = BSx;
				BattleShipCoordinates[3] = BSy;
				System.out.println("x2" + " " +BattleShipCoordinates[2]);
				System.out.println("y2" + " " +BattleShipCoordinates[3]);
				
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
				System.out.println("x1" + " " +SubmarineCoordinates[0]);
				System.out.println("y1" + " " +SubmarineCoordinates[1]);
			}
	else{
				SubmarineCoordinates[2] = SSx;
				SubmarineCoordinates[3] = SSy;
				System.out.println("x2" + " " +SubmarineCoordinates[2]);
				System.out.println("y2" + " " +SubmarineCoordinates[3]);
			}
	if (SubmarineCoordinates[2] != null){
		int x1 = SubmarineCoordinates[0];
		int y1 = SubmarineCoordinates[1];
		int x2 = SubmarineCoordinates[2];
		int y2 = SubmarineCoordinates[3];
		if ( Math.hypot((x2-x1),(y2-y1)) == 3 ){
			FullSizeSUB = true;
		}
	}
			
	}
public void dCruiser(int CSx, int CSy){
	if(CruiserCoordinates[0] == null){
				CruiserCoordinates[0] = CSx;
				CruiserCoordinates[1] = CSy;
				System.out.println("x1" + " " +CruiserCoordinates[0]);
				System.out.println("y1" + " " +CruiserCoordinates[1]);
			}
	else{
				CruiserCoordinates[2] = CSx;
				CruiserCoordinates[3] = CSy;
				System.out.println("x2" + " " +CruiserCoordinates[2]);
				System.out.println("y2" + " " +CruiserCoordinates[3]);
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
				System.out.println("x1" + " " +DestroyerCoordinates[0]);
				System.out.println("y1" + " " +DestroyerCoordinates[1]);
			}
	else{
				DestroyerCoordinates[2] = DSx;
				DestroyerCoordinates[3] = DSy;
				System.out.println("x2" + " " +DestroyerCoordinates[2]);
				System.out.println("y2" + " " +DestroyerCoordinates[3]);
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



//-------------------------------------------------------------------
//-----------------------MAINNNNNNNNNN-------------------------------
//-------------------------------------------------------------------
//-------------------------------------------------------------------
	
public static void main(String[] args){
  //      SwingUtilities.invokeLater(new Runnable()
    //    {
          //  public void run()
            //{
                new LayoutSetup().createAndDisplayGUI();

            }

       // });
   

    //}
}