//Parent class for fleet 
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;


public class Player{//start of player class
	//these are set and stored from server information
	//Don't know if they need to be in the constructor or not
//each player has their own board array
//board array holds boolean spaces that make up ships
//each ship has a their length of boolean spaces 

	//hadi
    public Player(){
    	username = null;
    	password = null;
    }
       

    public Player(String user, String pass){
        username = user;
        password = pass;
    }
	//hadi
	
//ship variables 
	private int carrier_length = 5;
	private int battleship_length = 4;
	private int submarine_length = 3;
	private int cruiser_length = 3;
	private int destroyer_length = 2;
	
	//Carrier variables 
	// carrier spaces = false when hit
	public static boolean carrier_space1= true;		
	public static boolean carrier_space2= true;
	public static boolean carrier_space3= true;
	public static boolean carrier_space4= true;
	public static boolean carrier_space5= true;
	//changed to true when ship is on the board 
	private static boolean carrier_set;
	//changes to true when ship is sunk
	private static boolean carrier_sunk;
	
	//Battleship variables 
	//battleship spaces = false when hit
	public static boolean battleship_space1= true;
	public static boolean battleship_space2= true;
	public static boolean battleship_space3= true;
	public static boolean battleship_space4= true;
	//changed to true when ship is on the board 
	private static boolean battleship_set;
	//changes to true when ship is sunk
	private static boolean battleship_sunk;

	//Submarine variables 
	//submarines spaces  = false when hit
	public static boolean submarine_space1 = true;
	public static boolean submarine_space2 = true;
	public static boolean submarine_space3 = true;
	//changed to true when ship is on the board 
	private  static boolean submarine_set;
	//changes to true when ship is sunk
	private static boolean submarine_sunk;

	//Cruiser variables 
	//cruiser space variables  = false when hit
	public static boolean cruiser_space1 = true;
	public static boolean cruiser_space2 = true;
	public static boolean cruiser_space3 = true;
	//changed to true when ship is on the board 
	private static boolean cruiser_set;
	//changes to true when ship is sunk
	private static boolean cruiser_sunk;

	//Destroyer variables 
	//destroyer space variables = false when hit
	public static boolean destroyer_space1= true;
	public static boolean destroyer_space2 = true;
	//changed to true when ship is on the board
	//changes to true when ship is sunk
	private static boolean destroyer_sunk;

	private static boolean destroyer_set;
//end of ship variables--------------------------------------------------------------------------------------------------------------------------------------------------------------

//player variables 
	private int fleetHealth;
	private int rank;
	private int attackPower;
	private static boolean turn;
	//2D array used for "board" implementation
	boolean board_space[] [] = new boolean [10] [10];	//boolean array for spaces 
	//actual board will be shown on screen, this "board" is just for computation 
	byte[] byteArray = new byte[(byte)10 * (byte)10];//byte array for server communication

	
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	////Hadii
    private String username = "";
    private String password = "";
    static String currentUser = null;
    ///Hadi

	public Player(String username, int fHealth, int rk, int aPower){//player constructor 
		fleetHealth = fHealth;
		rank = rk;
		attackPower = aPower;
		turn = true;
	}//end of constructor

//player based methods
	
	public boolean getTurn(){
		return Player.turn;
	}
	
	public void setTurn(boolean set){
		Player.turn = set;
	}

		
		
	public void shoot(int x, int y, Player player){//start of shoot()
		//shoots at a space and see if its a hit or not, changes board accordingly
		if(isHit(x,y,player)){
			/*change color of square to red*/
			/*Print to the screen: "That's a hit!"*/
			player.board_space[x][y] = false;
		}
		else{
			/*change color of square to white*/
			/*Print to the screen: "That's a miss!"*/
		}
	}//end of shoot()
	
	public boolean isHit(int x, int y, Player player){//will be used in shoot() to check if the shot is a hit or not
		if(player.board_space [x] [y] == false){return false;}//false means a miss or rehit
		
		else{//a true space means there is a ship space there 
			player.board_space[y] [x] = false; //sets that ships space to false 
			return true;
		}
	}

	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
//ship based methods
	//setShip methods
	//each method sets the ship on the "board" with appropriate coordinates and creates each object
	
	

	public void setCarrier(Integer x1, Integer y1, Integer x2, Integer y2){//start setCarrier()
		if (x1==x2){//for vertical ships
				//boolean spaces for carrier
				if(y1>y2){
					board_space [x1] [y1]   = Player.carrier_space1;
					board_space [x1] [y1+1] = Player.carrier_space2;
					board_space [x1] [y1+2] = Player.carrier_space3;
					board_space [x1] [y1+3] = Player.carrier_space4;
					board_space [x1] [y2]   = Player.carrier_space5;
					return;//breaks out of method
				}
				else{
					board_space [x1] [y2]   = Player.carrier_space1;
					board_space [x1] [y2-1] = Player.carrier_space2;
					board_space [x1] [y2-2] = Player.carrier_space3;
					board_space [x1] [y2-3] = Player.carrier_space4;
					board_space [x1] [y1]   = Player.carrier_space5;
					return;//breaks out of method
				}
		}		
		else if(y2 == y1){ // for horizontal ships
				//boolean spaces for carrier 
				if(x1>x2){
					board_space [x1] [y1]   = Player.carrier_space1;
					board_space [x1+1] [y1] = Player.carrier_space2;
					board_space [x1+2] [y1] = Player.carrier_space3;
					board_space [x1+3] [y1] = Player.carrier_space4;
					board_space [x2] [y1]   = Player.carrier_space5;
				}
				else{
					board_space [x2] [y1]   = Player.carrier_space1;
					board_space [x2-1] [y1] = Player.carrier_space2;
					board_space [x2-2] [y1] = Player.carrier_space3;
					board_space [x2-3] [y1] = Player.carrier_space4;
					board_space [x1] [y1]   = Player.carrier_space5;
					
				}
		}
		Player.setCarrierMarker(true);

	}// end setCarrier()
				
	public void setBattleship(Integer x1, Integer y1, Integer x2, Integer y2){//start setBattleship
		if (x1==x2){ // for vertical ships
				//boolean spaces for battleship 
				if(y1>y2){
					board_space[x1] [y1]   = Player.battleship_space1;
					board_space[x1] [y1+1] = Player.battleship_space2;
					board_space[x1] [y1+2] = Player.battleship_space3;
					board_space[x1] [y2]   = Player.battleship_space4;
					return;//breaks out of method
				}
				else{
					board_space[x1] [y2]   = Player.battleship_space1;
					board_space[x1] [y2-1] = Player.battleship_space2;
					board_space[x1] [y2-2] = Player.battleship_space3;
					board_space[x1] [y1]   = Player.battleship_space4;
					return;//breaks out of method
				}
		}							
		else if(y2 == y1){ // for horizontal ships
				//boolean spaces for battleship
				if(x1>x2){
					board_space[x1] [y1]   = Player.battleship_space1;
					board_space[x1+1] [y1] = Player.battleship_space2;
					board_space[x1+2] [y1] = Player.battleship_space3;
					board_space[x2] [y1]   = Player.battleship_space4;
					return;//breaks out of method
				}
				else{
					board_space[x2] [y1]   = Player.battleship_space1;
					board_space[x2-1] [y1] = Player.battleship_space2;
					board_space[x2-2] [y1] = Player.battleship_space3;
					board_space[x1] [y1]   = Player.battleship_space4;
					return;//breaks out of method
				}
			}
		Player.setBattleshipMarker(true);//boolean flag set to true when ship is set
	} //end setBattleship()
				
	public void setSubmarine(Integer x1, Integer y1, Integer x2, Integer y2){//start of setSubmarine
		if (x1==x2){ // for vertical ships 
				if(y1>y2){
					board_space[x1] [y1]   = Player.submarine_space1;
					board_space[x1] [y1+1] = Player.submarine_space2; 
					board_space[x1] [y2]   = Player.submarine_space3;
					return;//breaks out of method
				}
				else{
					board_space[x1] [y2]   = Player.submarine_space1;
					board_space[x1] [y2-1] = Player.submarine_space2; 
					board_space[x1] [y1]   = Player.submarine_space3;
					return;//breaks out of method
				}
		}
		else if(y2 == y1){ // for horizontal ships
				if(x1>x2){
					board_space[x1] [y1]   = Player.submarine_space1;
					board_space[x1+1] [y1] = Player.submarine_space2;
					board_space[x2] [y1]   = Player.submarine_space3;
					return;//breaks out of method
				}
				else{
					board_space[x2] [y1]   = Player.submarine_space1;
					board_space[x2-1] [y1] = Player.submarine_space2;
					board_space[x1] [y1]   = Player.submarine_space3;
					return;//breaks out of method
				}
		}
		Player.setSubmarineMarker(true);//boolean flag set to true when ship is set
	}//end setSubmarine
				
	public void setCruiser(Integer x1, Integer y1, Integer x2, Integer y2){//start of setCruiser
		if (x1==x2){ // for vertical ships 
				//boolean spaces for cruiser
				if(y1>y2){
					board_space[x1] [y1]   = Player.cruiser_space1;
					board_space[x1] [y1+1] = Player.cruiser_space2;
					board_space[x1] [y2]   = Player.cruiser_space3;
					return;//breaks out of method
				}
				else{
					board_space[x1] [y2]   = Player.cruiser_space1;
					board_space[x1] [y2-1] = Player.cruiser_space2;
					board_space[x1] [y1]   = Player.cruiser_space3;
					return;//breaks out of method
					
				}

		}
		else if(y2 == y1){ // for horizontal ships
				//boolean spaces for cruiser
				if(x1>x2){
					board_space[x1] [y1]   = Player.cruiser_space1;
					board_space[x1+1] [y1] = Player.cruiser_space2;
					board_space[x2] [y1]   = Player.cruiser_space3;
					return;//breaks out of method
				}
				else{
					board_space[x2] [y1]   = Player.cruiser_space1;
					board_space[x2-1] [y1] = Player.cruiser_space2;
					board_space[x1] [y1]   = Player.cruiser_space3;
					return;//breaks out of method
				}
		}

		Player.setCruiserMarker(true);//boolean flag set to true when ship is set

	}//end setCruiser
				
	public void setDestroyer(Integer x1, Integer y1, Integer x2, Integer y2){//start of setDestroyer 
		if (x1==x2){ // for vertical ships 
				//boolean spaces for destroyer
				if(y1>y2){
					board_space[x1] [y1] = Player.destroyer_space1;
					board_space[x1] [y2] = Player.destroyer_space2;
					return;
				}
				else{
					board_space[x1] [y2] = Player.destroyer_space1;
					board_space[x1] [y1] = Player.destroyer_space2;
					return;
				}

		}
		else if(y2 == y1){ // for horizontal ships
				board_space[x1] [y1] = Player.destroyer_space1;
				board_space[x2] [y1] = Player.destroyer_space2;
				return;

			
		}
		Player.setDestroyerMarker(true);//boolean flag set to true when ship is set
		
	}//end setDestroyer

//------------------------------------------------------------------------------------------------------------
	//isSet methods
	//all methods return true for set and false for not set 
		public boolean isSetCarrier(){
			if(carrier_set == true){return true;}
			else {return false;}
		}
		public boolean isSetBattleship(){
			if(battleship_set == true){return true;}
			else {return false;}		
		}
		public boolean isSetSubmarine(){
			if(submarine_set == true){return true;}
			else {return false;}
		}
		public boolean isSetCruiser(){
			if(submarine_set == true){return true;}
			else{return false;}
		}
		public boolean isSetDestroyer(){
			if(destroyer_set == true){return true;}
			else {return false;}
		}
//------------------------------------------------------------------------------------------------------------

		//isSetShip methods
		//lets player change the boolean flag for set on the board 
		public static void setCarrierMarker(boolean mark){
			carrier_set = mark;
		}
		public static void setBattleshipMarker(boolean mark){
			battleship_set = mark;
		}
		public static void setSubmarineMarker(boolean mark){
			submarine_set = mark;
		}
		public static void setCruiserMarker(boolean mark){
			cruiser_set = mark;
		}
		public static void setDestroyerMarker(boolean mark){
			destroyer_set = mark;
		}
	
//---------------------------------------------------------------------------------------------------------------
		
		//isSunk methods
		//checks all spaces of ship to see if its sunk
		public boolean isSunkCarrier(){
			if(carrier_space1 == false && carrier_space2 == false && carrier_space3 == false && carrier_space4 == false && carrier_space5 == false){//checks all spaces 
				/*Print to the screen: "Carrier has been sunk"*/
				carrier_sunk=true;
				return carrier_sunk;
			}
			else {return false;}
		}

		public boolean isSunkBattleship(){
			if(battleship_space1 == false && battleship_space2 == false && battleship_space3 == false && battleship_space4 == false){//checks all spaces
				/*Print to the screen: "Battleship has been sunk"*/
				battleship_sunk=true;
				return battleship_sunk;
			}
			else {return false;}
		}
		
		public boolean isSunkSubmarine(){
			if(submarine_space1 == false && submarine_space2 == false && submarine_space3 == false){//checks all spaces 
				/*Print to the screen: "Submarine has been sunk"*/
				submarine_sunk=true;
				return submarine_sunk;
			}
			else {return false;}
		}
		
		public boolean isSunkCruiser(){
			if(cruiser_space1 == false && cruiser_space2 == false && cruiser_space3 == false){// checks all spaces 
				/*Print to the screen: "cruiser has been sunk"*/
				cruiser_sunk=true;
				return cruiser_sunk;
			}
			else {return false;}
		}
		
		public boolean isSunkDestroyer(){
			if(destroyer_space1 == false && destroyer_space2 == false){// checks all spaces 
				/*Print to the screen: "Destroyer has been sunk"*/
				destroyer_sunk=true;
				return destroyer_sunk;
			}
			else {return false;}
		}
		
//------------------------------------------------------------------------------------------------------------------------------
				
//end of shipbased methods----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Hadiiii
		
		 public void setUser(String user){
		        this.username = user;
		    }
		    public static void setCurrentUser(String cuser){
		    	Player.currentUser = cuser;
		    }
		    public static String getCurrentUser(){
		    	return currentUser;
		    }
		    
		    public void setPassword(String pass){
		        this.password = pass;
		    }
		    
		    public String getUser()
		    {
		        return username;
		    }
		    
		    public String getPass(){
		        return password;
		    }
		    
		    
		    
		    	 public String toString() {
		    	        return ("Username: "+this.getUser()+
		    	                    "; Password: "+ this.getPass()) + "\n";
		    	   }    


}//end of Player Class
		
	

	