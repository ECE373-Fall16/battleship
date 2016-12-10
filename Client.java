import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.io.*;

public class Client {
	static Socket socket;//new socket
	static DataInputStream in;//new input stream
	static DataOutputStream out;//new output stream	
	
	static String playernumber = "x";
	
	static boolean myturn;
	
	static boolean hitconnected = false;
	static boolean hitrecieved = false;
	static boolean hitlaunched = false;
	static String hitrecievedvalue;
	static int hitcounter, realhit;
	static String message = "";
	static ArrayList<Integer> mylocations = new ArrayList<>();
	
public static void main(String[] args) throws Exception{//run
	System.out.println("Connecting...");//connecting
	Thread.sleep(1000);
	socket = new Socket("localhost",3389);//sets socket to ipaddress and port
	System.out.println("Connection Successful");//print it connected
	Thread.sleep(1000);
System.out.println();
	in = new DataInputStream(socket.getInputStream());//get input 
	out = new DataOutputStream(socket.getOutputStream());//send ooutput
	Input input = new Input(in);
	Thread thread = new Thread(input);
	thread.start();

	PlayerDatabase.initializedb();

	while(!LogIn.found ){
		Thread.sleep(1000);
	}
	
	while(LogIn.loggedin ){
		Thread.sleep(1000);
	}
		
	String current = Player.getCurrentUser();
	
	System.out.println("Current User is " + current);
	out.writeUTF(current);
		
	while(!GameEngine.set){
	Thread.sleep(1000);
	}
	
	System.out.println(GameEngine.getFinalCoordinates());
	out.writeUTF(GameEngine.getFinalCoordinates());
	
	playernumber = in.readUTF();

	check_which_player(playernumber);
	
	System.out.println("my number is " + playernumber);

	
	ShipSetup.setUserShips(GameEngine.getFinalCoordinates());
	mylocations = ShipSetup.intList;
	
	System.out.println("my ships are " + mylocations);

	
	
	while((mylocations.size() !=0) || (hitcounter != 17)){
		System.out.println("I'm in here #1");
		if(!myturn){
			System.out.println("I'm in here #2 because Im 2");

			String hitR = in.readUTF();
			System.out.println("here we have hitR: " + hitR);
			while(!hitrecieved){
				Thread.sleep(1000);
			}
			System.out.println("I'm in here #3 I got a hit");

			hitrecievedvalue = in.readUTF();
			System.out.println("hitrecievedvalue" + hitrecievedvalue);

			checkifhit(Integer.parseInt(hitrecievedvalue));
			hitrecieved = false;
		}
		
		while(!hitlaunched){
			Thread.sleep(1000);
		}
		
		System.out.println("I'm in here #100 I sent a hit!: " + realhit);

		String hitting = "" + realhit;
		out.writeUTF(hitting);//see if it will send int
		//Thread.sleep(1000);
		checkifhitconnected();
	}
	
	gameStatus();
	
}

	public static void check_which_player(String x){
		if(x.equals("1")){
			myturn = true;
		}else if(x.equals("2")){
			myturn = false;
		}
	}
	
	public static void checkifhit(int hit){
		int location = hit;
		
		if(mylocations.contains(location)){
			GameEngine.button[location].setBackground(Color.RED);
			mylocations.remove(location);
		}else{
			GameEngine.button[location].setBackground(Color.WHITE);
		}
		
	}
	
	public static void checkifhitconnected() throws IOException{
		String hit = in.readUTF();
		System.out.println(hit);
		if(hit.equals("HIT")){
			hitconnected = true;
			GameEngine.buttonF[realhit].setBackground(Color.RED);
			hitcounter++;
			System.out.println("the hit counter is: " + hitcounter);
			hitconnected = false;
		}else if(hit.equals("MISS")){
			GameEngine.buttonF[realhit].setBackground(Color.WHITE);
	}
		
		hitlaunched = false;
		myturn = false;

	}
	
	public static void gameStatus(){
		if(mylocations.size() == 0){
			System.out.println("You Lost!");
		}else if(hitcounter == 17){
			System.out.println("You Won!");
		}
		

		GameEngine.textField.setVisible(false);
		GameEngine.FireButton.setVisible(false);	
		//GameEngine.MainMenuButton.setVisible(true);//NEEDS TO BE IMPLEMENTED
	}
	
	public static void recievemessage(String x){
	System.out.println("Mesage from input " + x);
}
	
	
	public static void setFire(String x){
		realhit = ShipSetup.getRealHit(x);
		hitlaunched = true;
	}


	

	
}

class Input implements Runnable{
	
	DataInputStream in;
	
	public Input(DataInputStream in){
		this.in = in;
	}
	
	public void run() {
		while(true){
			String message;
			/*
			  try {
			 
				message = in.readUTF();
				Client.recievemessage(message);
				System.out.println(" Message in Input is: " + message); 
			} catch (IOException e) {
				
			}
			*/
		}
	}
}