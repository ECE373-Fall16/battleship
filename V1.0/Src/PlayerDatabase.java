import java.util.*;
import java.io.*;

public class PlayerDatabase {

	static int num;
	static List<Player> players = new ArrayList<Player>();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		initializedb();
	}

	public static void initializedb() {
		System.out.println("Initializing FleetDestroyer Database...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {}
		System.out.println("Database Initialized!");

		String[] ids;
		File file = new File("Database");
		String user = null;
		String pass = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				Player newPerson = new Player();
				ids = line.split("\\s+");
				user = ids[0];
				pass = ids[1];
				newPerson.setUser(user);
				newPerson.setPassword(pass);
				players.add(newPerson);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static boolean runme(String user, String password) {

		String userresponse = user;
		String passresponse = password;

		boolean found = false;

		for (Player u : players) {
			if ((u.getUser() != null && u.getUser().equals(userresponse))
					&& (u.getPass() != null && u.getPass().equals(passresponse))) {
				found = true;
				u.getUser();
			}
		}

		return found;

	}

	public static boolean newUserForm(String userx, String passx) {
		String user = userx;
		String pass = passx;
		boolean added = true;
		Player newUser = new Player();

		for (Player u : players) {
			if ((u.getUser() != null && u.getUser().equals(user))) {
				added = false;
			}
		}
		if (added) {
			newUser.setUser(user);
			newUser.setPassword(pass);
			players.add(newUser);
			try {
				File db = new File("Database");
				PrintWriter printer = new PrintWriter(new FileWriter(db, true));
				printer.write(user + " " + pass + "\n");
				printer.flush();
				printer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return added;

	}

	static ArrayList<String> loggedinplayers = new ArrayList<String>();

}