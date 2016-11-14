//import classes and libraries 
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

//------------------------------------------------------------------------------------------------------------------------------------------------------------

public class BattleshipEngine{
	private static boolean game = true;//variable for game to run
	private static boolean setup_player1 = true;//flag for player1 to set up board
	private static boolean setup_player2 = true;//flag for player2 to set up board
	public static void main(String args[]){
		//creates two different players
		//each player has a its own fleet
		//each fleet has its own board 
		Player player1 = new Player("",1,1,1);
		Player player2 = new Player("",1,1,1);
		//info for constructor comes from server
		//Print to screen: Set up your ships!
		LayoutSetup board1 = new LayoutSetup();//creates object that is the board graphic
		LayoutSetup board2 = new LayoutSetup(); 
		board1.run();//runs the window that is the graphics of the game
		while(setup_player1){//player1 board setup
			if(board1.set){
					player1.setCarrier(board1.AircraftCoordinates[0],board1.AircraftCoordinates[1],board1.AircraftCoordinates[2],board1.AircraftCoordinates[3]);//puts carrier on boolean array for player1
					player1.setBattleship(board1.BattleShipCoordinates[0],board1.BattleShipCoordinates[1],board1.BattleShipCoordinates[2],board1.BattleShipCoordinates[3]);
					player1.setSubmarine(board1.SubmarineCoordinates[0],board1.SubmarineCoordinates[1],board1.SubmarineCoordinates[2],board1.SubmarineCoordinates[3]);
					player1.setCruiser(board1.CruiserCoordinates[0],board1.CruiserCoordinates[1],board1.CruiserCoordinates[2],board1.CruiserCoordinates[3]);
					player1.setDestroyer(board1.DestroyerCoordinates[0],board1.DestroyerCoordinates[1],board1.DestroyerCoordinates[2],board1.DestroyerCoordinates[3]);
				}
			setup_player1 = false;
			return;
		}	
	//end of setup player1
		board2.run();
		while(setup_player2){//player2 board setup
				if(board2.set){
					player2.setCarrier(board2.AircraftCoordinates[0],board2.AircraftCoordinates[1],board2.AircraftCoordinates[2],board2.AircraftCoordinates[3]);//puts carrier on boolean array for player1
					player2.setBattleship(board2.BattleShipCoordinates[0],board2.BattleShipCoordinates[1],board2.BattleShipCoordinates[2],board2.BattleShipCoordinates[3]);
					player2.setSubmarine(board2.SubmarineCoordinates[0],board2.SubmarineCoordinates[1],board2.SubmarineCoordinates[2],board2.SubmarineCoordinates[3]);
					player2.setCruiser(board2.CruiserCoordinates[0],board2.CruiserCoordinates[1],board2.CruiserCoordinates[2],board2.CruiserCoordinates[3]);	
					player2.setDestroyer(board2.DestroyerCoordinates[0],board2.DestroyerCoordinates[1],board2.DestroyerCoordinates[2],board2.DestroyerCoordinates[3]);
				}
			setup_player2=false;
			return;
		}//end of setup player2
		
		
//end of setup phase for game
//--------------------------------------------------------------------------------------------------------------------------------------------------	
		while(game){//game loop
			while(player1.getTurn() == true){//turn loop for player1
				//checks if opponents ships are sunk, if so game is over 
				if(player2.isSunkCarrier()==true && player2.isSunkBattleship() == true && player2.isSunkSubmarine() == true && player2.isSunkCruiser() == true && player2.isSunkDestroyer() == true){
					player1.setTurn(false);
					game = false;
					return;
				}
				if(board1.fire){//shoot conditional
					int x;
					int y;
					x = (int)(board1.content.charAt(0));
					y = (int)(board1.content.charAt(1));
					System.out.println(x +y);
					player1.shoot(x,y,player2);
					player1.setTurn(false);
					//checks if opponents ships are sunk, if so game is over 
					if(player2.isSunkCarrier()==true && player2.isSunkBattleship() == true && player2.isSunkSubmarine() == true && player2.isSunkCruiser() == true && player2.isSunkDestroyer() == true){
						game = false;
						return;
					}
				}//end shoot conditional
			}//end turn loop for player1
	
			while(player2.getTurn() == true){//turn loop for player1
				if(board2.fire){//shoot conditional
					int x;
					int y;
					x = (int)(board2.content.charAt(0));
					y = (int)(board2.content.charAt(1));
					player2.shoot(x,y,player1);
					player2.setTurn(false);
					//checks if opponents ships are sunk, if so game is over 
					if(player1.isSunkCarrier()==true && player1.isSunkBattleship() == true && player1.isSunkSubmarine() == true && player1.isSunkCruiser() == true && player1.isSunkDestroyer() == true){
						game = false;
						return;
					}
				}//end shoot conditional
			}//end turn loop for player2
			
		}//end of game
		System.out.print("gameover");
		
	}//end main
	
}//end BattleshipEngine class