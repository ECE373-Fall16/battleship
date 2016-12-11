import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.awt.Color;
import java.io.*;

public class Server extends Client{

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;

	

	static Users[] user = new Users[2];
	
	public static void main(String[] args) throws Exception{		
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(3389);
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
	
	static int loggers = 0;
	static String playerone  = "";
	static String playertwo = "" ;
	static boolean p1turn = true;
	static boolean changed = false;
	
	public Users(DataOutputStream out, DataInputStream in, Users[] user){
		this.out = out;
		this.in = in;
		this.user = user;
	}

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
			try {
				out.writeUTF("1");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loggers++;	
		}else if(loggers == 1){
			playertwo = name;
			try {
				out.writeUTF("2");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//loggers++;
		}
		
		
		System.out.println(shiplocations);
		ShipSetup.setUserShips(shiplocations);
		System.out.println("Player one is: " + playerone + " player two is: " + playertwo);

		String tempwords = "";
		int hitmarker;
		
		while(true){
			while(!ShipSetup.gameover){
			//try {
				String message;
				try {
					message = in.readUTF();
					String playernumber = message.substring(0,1);
					message = message.substring(1,message.length());
					int number = Integer.parseInt(playernumber);


				for(int i = 0; i < 2; i++){
					if(user[i] != null){
						
						hitmarker = ShipSetup.getRealHit(message);
						if(number == 1){
						tempwords = ShipSetup.removeEnemyShip(hitmarker);
						}
						if(number == 2){
							tempwords = ShipSetup.removeShip(hitmarker);
						}
						System.out.println("An attack was launched by " + user[number - 1].name + " at: " + tempwords + "!");
						
						user[i].out.writeUTF(tempwords + "" + hitmarker);

						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
					
				
				ShipSetup.gameOver();
			} 
			
			}
		}
}
	