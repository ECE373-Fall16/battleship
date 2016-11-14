import java.net.*;
import java.io.*;

public class Server {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	static boolean player1turn = true;


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
	int lastplayer = 1;
	
	public Users(DataOutputStream out, DataInputStream in, Users[] user){
		this.out = out;
		this.in = in;
		this.user = user;
	}

	public void run() {
		
	
		for(int i = 0; i < 2; i++){
			if(user[i] != null){
				if(i == 0){
					try {
						 name = in.readUTF();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						try {
							shiplocations = in.readUTF();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						
					System.out.println("Player " + i + "'s ship locations are: " + shiplocations);
					user[0] = null;
				}
			}
				if(user[i] != null){

				if(i == 1){
					try {
						 name = in.readUTF();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						try {
							shiplocations = in.readUTF();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						
					System.out.println("Player " + i + "'s ship locations are: " + shiplocations);
					user[1]=null;
				}
			}
		}	
	/**	
		try {
		 name = in.readUTF();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			shiplocations = in.readUTF();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		*/
		while(true){
			try {
				String message = in.readUTF();
				System.out.println("I got a message that said" + message);
				for(int i = 0; i < 2; i++){
					if(user[i] != null){
						user[i].out.writeUTF(name + ": " + message + "user is " + i);
						
						//should be a boolean
						//create a variable for whos turn
						//and create a varaiable for last perosn to go
						//if last person = i; wait; else go
						//also make a database for people currentl logged in so the same one doesnt log in twice
						//also make a database for each person's ships
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.out = null;
				this.in = null;
				}
		}
		
		
		
	}
	
}