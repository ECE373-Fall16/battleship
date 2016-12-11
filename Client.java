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
	static int hitrecievedvalue;
	static int hitcounter, realhit;
	static String message = "";
	static ArrayList<Integer> mylocations = new ArrayList<>();
	
public static void main(String[] args) throws Exception{//run
	System.out.println("Connecting...");//connecting
	Thread.sleep(1000);
	socket = new Socket("localhost",3389);//104.196.169.95/sets socket to ipaddress and port
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
		
	while((!GameEngine.set) || (GameEngine.getFinalCoordinates().length() != 79)){
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
		while(!myturn){
			GameEngine.FireButton.setVisible(false);

			System.out.println("I'm in here #2 because Im 2");

			String hitR = in.readUTF();
			hitR = hitR.replaceAll("[^0-9.]", "");

			System.out.println("here we have hitR: " + hitR);
			if(hitR.length() == 2){
				hitrecievedvalue = Integer.parseInt(hitR);
				hitrecieved = true;
			}
			while(!hitrecieved){
				Thread.sleep(1000);
			}
			

			System.out.println("I'm in here #3 I got a hit");

			System.out.println("hitrecievedvalue" + hitrecievedvalue);

			checkifhit(hitrecievedvalue);
			hitrecieved = false;
		}
		
		while(!hitlaunched){
			Thread.sleep(1000);
		}
		
		System.out.println("I'm in here #100 I sent a hit!: " + realhit);

		String hitting = playernumber + "" + realhit;
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
			mylocations.remove(new Integer(location));
			myturn = false;
		}else{
			GameEngine.button[location].setBackground(Color.WHITE);
			GameEngine.FireButton.setVisible(true);
			myturn = true;
		}
		
	}
	
	public static void checkifhitconnected() throws IOException{
		String hit = in.readUTF();
        hit = hit.replaceAll("[^A-Z]","");

		System.out.println(hit + "here I'm printing");
		if(hit.equals("HIT")){
			hitconnected = true;
			GameEngine.buttonF[realhit].setBackground(Color.RED);
			hitcounter++;
			System.out.println("the hit counter is: " + hitcounter);
			hitconnected = false;
			myturn = true;

		}else if(hit.equals("MISS")){
			GameEngine.buttonF[realhit].setBackground(Color.WHITE);
			myturn = false;
			GameEngine.FireButton.setVisible(false);


	}
		
		hitlaunched = false;

	}
	
	public static void gameStatus(){
		if(mylocations.size() == 0){
			System.out.println("You Lost!");
		}else if(hitcounter == 17){
			System.out.println("You Won!");
		}
		

		GameEngine.textField.setVisible(false);
		//GameEngine.FireButton.setVisible(false);	
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