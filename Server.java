import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.awt.Color;
import java.io.*;

public class Server extends Client {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;

	static Users[] user = new Users[2];

	public static void main(String[] args) throws Exception {
		System.out.println("Starting FleetDestroyer Server...");
		serverSocket = new ServerSocket(3389);
		Thread.sleep(1000);
		System.out.println("FleetDestroyer Server Started");
		PlayerDatabase.initializedb();
		resetLoggedInPlayers();
		while (true) {
			socket = serverSocket.accept();
			for (int i = 0; i < 2; i++) {

				System.out.println("Connection from: " + socket.getInetAddress());
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				if (user[i] == null) {
					user[i] = new Users(out, in, user);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}

	public static void resetLoggedInPlayers() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("loggedinplayers");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}
	}
}

class Users implements Runnable {

	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[2];
	String name;
	String shiplocations;

	static int loggers = 0;
	static String playerone = "";
	static String playertwo = "";
	static boolean p1turn = true;
	static boolean changed = false;
	static String username = "";
	static String password = "";

	public Users(DataOutputStream out, DataInputStream in, Users[] user) {
		this.out = out;
		this.in = in;
		this.user = user;
	}

	public void run() {
		boolean LogIn = false;
		while (!LogIn) {
			try {
				username = in.readUTF();
				password = in.readUTF();
				System.out.println(username + "   " + password);

			} catch (IOException e) {
			}

			boolean found = PlayerDatabase.runme(username, password);

			if (found && !alreadyLoggedIn(username)) {
				loggingin(username);
				try {
					out.writeUTF("Login Confirmed!");
					
					String values = LeaderBoard.returnLeaderBoard();
					System.out.println(values);
					out.writeUTF(values);
				} catch (IOException e) {
				}
				LogIn = true;
			} else if ((!found) || alreadyLoggedIn(username)) {
				try {
					out.writeUTF("Invalid Login");
				} catch (IOException e) {
				}
				LogIn = false;
			}

		}
		
		System.out.println("we made it out");
		if (loggers == 0) {
			playerone = username;
			try {
				out.writeUTF("1");
				System.out.println("we sent out");

			} catch (IOException e) {}
			loggers++;
		} else if (loggers == 1) {
			playertwo = username;
			try {
				out.writeUTF("2");
				System.out.println("we sent out again");

			} catch (IOException e) {}
			loggers++;
		}
		while(loggers != 2){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		System.out.println("We got two");
		
		try {
			out.writeUTF("MatchSet");
		} catch (IOException e2) {}

		System.out.println("We sent the matchset");
		try {
			name = in.readUTF();
		} catch (IOException e1) {
		}
		try {
			shiplocations = in.readUTF();
		} catch (IOException e1) {
		}



		System.out.println(shiplocations);
		ShipSetup.setUserShips(shiplocations);
		System.out.println("Player one is: " + playerone + " player two is: " + playertwo);

		String tempwords = "";
		int hitmarker = 0;
		int playernumber = 0;

		while (true) {
			while (!ShipSetup.gameover) {
				// try {
				String message;
				try {
					message = in.readUTF();
					System.out.println("the hit I recieved initially is: " + message);

					String command = message.substring(0, 1);
					System.out.println("The command is: " + command);
					message = message.substring(1, message.length());
					System.out.println("The message therefore is: " + message);

					if (command.equals("1")) {
						playernumber = 1;
						sendHit(tempwords, hitmarker, playernumber, message);
					}
					if (command.equals("2")) {
						playernumber = 2;
						sendHit(tempwords, hitmarker, playernumber, message);
					}
				} catch (IOException e) {
					// System.out.println("ERROR");
				}

				// ShipSetup.gameOver();

			}
		}
	}

	public static void loggingin(String name) {
		String username = name;
		FileWriter writer;
		try {
			writer = new FileWriter("loggedinplayers", true);
			writer.write(username + "\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public static boolean alreadyLoggedIn(String name) {
		boolean loggedin = false;
		String username = name;
		File file = new File("loggedinplayers");
		Scanner scan;
		try {
			scan = new Scanner(file);

			while (scan.hasNext()) {
				String line = scan.next();
				if (line.equals(username)) {
					loggedin = true;
				}
			}
			scan.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}

		return loggedin;
	}

	public void sendHit(String tempwords, int hitmarker, int playernumber, String message) throws IOException {
		for (int i = 0; i < 2; i++) {
			if (user[i] != null) {

				hitmarker = Integer.parseInt(message);
				if (playernumber == 1) {
					tempwords = ShipSetup.removeEnemyShip(hitmarker);
				}
				if (playernumber == 2) {
					tempwords = ShipSetup.removeShip(hitmarker);
				}
				System.out.println(
						"An attack was launched by " + user[playernumber - 1].name + " at: " + tempwords + "!");

				user[i].out.writeUTF(tempwords + "" + hitmarker);

			}
		}
	}
}
