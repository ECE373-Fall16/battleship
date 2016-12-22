import java.net.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.awt.Color;
import java.io.*;

public class Client {
	static Socket socket;// new socket
	static DataInputStream in;// new input stream
	static DataOutputStream out;// new output stream

	static String playernumber = "x";
	static boolean signedIn = false;

	static boolean myturn;

	static boolean hitconnected = false;
	static boolean hitrecieved = false;
	static boolean hitlaunched = false;
	static int hitrecievedvalue;
	static int hitcounter, realhit;
	static int enemyhitcounter = 0;
	static String message = "";
	static ArrayList<Integer> mylocations = new ArrayList<>();

	public static void main(String[] args) throws Exception {// run
		System.out.println("Connecting...");// connecting
		Thread.sleep(1000);
		socket = new Socket("104.196.169.95", 3389);// 104.196.169.95/sets socket to
												// ipaddress and port
		System.out.println("Connection Successful");// print it connected
		Thread.sleep(1000);
		System.out.println();
		in = new DataInputStream(socket.getInputStream());// get input
		out = new DataOutputStream(socket.getOutputStream());// send ooutput
		
		Thread.sleep(500);
		System.out.println("Launching FleetDestroyer...");
		InitialScreen.main(null);
		
		while (!signedIn) {
			Thread.sleep(1000);
		}
		String LeaderBoardRecieved = in.readUTF();
		Player.setLeaderBoard(LeaderBoardRecieved);
		
		out.writeUTF(Player.getCurrentUser());


		playGame();

	}



	public static void playGame() throws IOException, InterruptedException {

		while ((!GameEngine.set) || (GameEngine.getFinalCoordinates().length() != 79)) {
			Thread.sleep(1000);
		}

		out.writeUTF(GameEngine.getFinalCoordinates());

		
		playernumber = in.readUTF();

		
		boolean setMatch = false;
		while (!setMatch) {
			String x = in.readUTF();
			if(x.equals("MatchSet")){
				setMatch = true;
			}
			Thread.sleep(1000);
		}
		


		check_which_player(playernumber);


		ShipSetup.setUserShips(GameEngine.getFinalCoordinates(),1);
		mylocations = ShipSetup.intList;


		while ((mylocations.size() != 0) && (hitcounter != 17) && (enemyhitcounter !=17) ) {
			while (!myturn) {
				GameEngine.FireButton.setVisible(false);

				String hitR = in.readUTF();
				hitR = hitR.replaceAll("[^0-9.]", "");

				if ((hitR.length() == 2) || (hitR.length() == 1)) {
					hitrecievedvalue = Integer.parseInt(hitR);
					hitrecieved = true;
				}
				while (!hitrecieved) {
					Thread.sleep(1000);
				}
				checkifhit(hitrecievedvalue);
				hitrecieved = false;
			}

			while (!hitlaunched) {
				Thread.sleep(1000);
			}

			String hitting = playernumber + "" + realhit;
			out.writeUTF(hitting);// see if it will send int
			checkifhitconnected();
		}

		gameStatus();

	}

	public static void check_which_player(String x) {
		if (x.equals("1")) {
			myturn = true;
		} else if (x.equals("2")) {
			myturn = false;
		}
	}

	public static void checkifhit(int hit) {
		int location = hit;

		if (mylocations.contains(location)) {
			GameEngine.button[location].setBackground(Color.RED);
			mylocations.remove(new Integer(location));
			enemyhitcounter++;
			
			if(enemyhitcounter == 17){
				gameStatus();
			}
			myturn = false;
		} else {
			GameEngine.button[location].setBackground(Color.WHITE);
			GameEngine.FireButton.setVisible(true);
			myturn = true;
		}

	}

	public static void checkifhitconnected() throws IOException {
		String hit = in.readUTF();
		hit = hit.replaceAll("[^A-Z]", "");

		if (hit.equals("HIT")) {
			hitconnected = true;
			GameEngine.buttonF[realhit].setBackground(Color.RED);
			hitcounter++;
			hitconnected = false;
			myturn = true;

		} else if (hit.equals("MISS")) {
			GameEngine.buttonF[realhit].setBackground(Color.WHITE);
			myturn = false;
			GameEngine.FireButton.setVisible(false);

		}

		hitlaunched = false;

	}

	public static void gameStatus() {
		if (mylocations.size() == 0) {
		    JOptionPane.showMessageDialog(null,"You Lost!","Match Result()", 2);
		} else if (hitcounter == 17) {
		    JOptionPane.showMessageDialog(null,"You Won!","Match Result()", 2);
		}

		GameEngine.textField.setVisible(false);
		GameEngine.FireButton.setVisible(false);
		GameEngine.EndGameButton.setVisible(true);
	}

	public static void setFire(String x) {
		realhit = ShipSetup.getRealHit(x);
		hitlaunched = true;
	}
	
	public static void sendUser(String u, String p) {
		try {
			out.writeUTF(u);
			out.writeUTF(p);
		} catch (IOException e) {
		}
	}

	public static boolean recieveConfirmation() {
		String dataB = "";
		try {
			dataB = in.readUTF();
		} catch (IOException e) {
		}
		if (dataB.equals("Login Confirmed!")) {
			signedIn = true;
		} else {
			signedIn = false;
		}
		return signedIn;
	}
	
	public static void setCommand(String x){
		
	}
}
