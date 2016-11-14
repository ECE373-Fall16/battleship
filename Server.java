import java.net.*;
import java.io.*;

public class Server {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;


	static Users[] user = new Users[2];
	
	public static void main(String[] args) throws Exception{		
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(22231);
		Thread.sleep(1000);
		System.out.println("Server Started");
		while(true){
		socket = serverSocket.accept();
		for(int i = 0; i < 2; i++){
			
		System.out.println("Connection from: " + socket.getInetAddress());
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		if(user[i] == null){
			user[i] = new Users(out,in,user);
		Thread thread = new Thread(user[i]);
		thread.start();

		break;
		
		}
		}
	
		}
	}

}
class Users implements Runnable{

	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[2];
	String name;
	String shiplocations;
	boolean opponentturn = false;

	
	public Users(DataOutputStream out, DataInputStream in, Users[] user){
		this.out = out;
		this.in = in;
		this.user = user;
	}

	static int loggers = 0;
	static boolean playeronesturn = true;
	static String playerone  = "";
	static String playertwo = "" ;
	
	public void run() {
		try {
		 name = in.readUTF();
		} catch (IOException e1) {
			//e1.printStackTrace();
		}
		
		try {
			shiplocations = in.readUTF();
			} catch (IOException e1) {
				//e1.printStackTrace();
			}

		if(loggers == 0){
			playerone = name;
			loggers++;	
		}else if(loggers == 1){
			playertwo = name;
			//loggers++;
		}
		
		ShipSetup.setUserShips(shiplocations);
		System.out.println("Player one is: " + playerone + " player two is: " + playertwo);

		
		while(true){
			try {
				String message = in.readUTF();

				for(int i = 0; i < 2; i++){
					if(user[i] != null){

						user[i].out.writeUTF("Player One's name is " + name + ": " + message + "user is " );
						System.out.println("Player one is: " + playerone + " player two is: " + playertwo);
						user[i].out.writeUTF(name + "'s ship locations are: " + shiplocations + "user is " + i);	
						
						/*
							System.out.println("Player one is: " + playerone + " player two is: " + playertwo + "name is " + name);
							String xxx = "";
							if(playeronesturn && (name.equals(playerone))){
							xxx = "here 1";
								//user[i].out.writeUTF(name + "'s ship locations are: " + shiplocations);
							playeronesturn = false;
							//printed = 1;
									//break;
							} 
								
								if (playeronesturn && (!(name.equals(playerone)))){
								//user[i].out.writeUTF("It is not " + name + "'s turn!");
									xxx = "here2";
								//break;
							}
							 if(!playeronesturn && (name.equals(playertwo))){
								//user[i].out.writeUTF(name + "'s ship locations are: " + shiplocations);
								playeronesturn = true;
								xxx = "here3";
								//printed = 1;
								//break;

								} if ((!playeronesturn && (!(name.equals(playertwo))) && (printed != 1))){
									//user[i].out.writeUTF("It is not " + name + "'s turn!");
									//break;
									xxx="here 4";
								}
								user[i].out.writeUTF("Here we have: " + xxx);
								*/

							//}
						
						//should be a boolean
						//create a variable for whos turn
						//and create a varaiable for last perosn to go
						//if last person = i; wait; else go
						//also make a database for people currentl logged in so the same one doesnt log in twice
						//also make a database for each person's ships
					}
				}
			} catch (IOException e) {
				//this.out = null;
				//this.in = null;
				LogIn.endofgamelog();
				}
		}
		
	}
	
	
}










