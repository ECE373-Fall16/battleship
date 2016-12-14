import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
 
import java.awt.Font;
 
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.*;

public  class LogIn extends JFrame{
	static Player currentUser = new Player();
	static boolean found = false;
	static boolean loggedin = false;

	public static void main(String[] args) {
		LogIn loginscreen = new LogIn();
	}
	
	JButton loginButton = new JButton("Login");
	JPanel loginScreen = new JPanel();
	JTextField userField = new JTextField(10);
	JPasswordField passwordField = new JPasswordField(10);
	GridLayout experimentLayout = new GridLayout(0,1);
	JLabel loginLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	
	ImageIcon imageIcon = new ImageIcon("C:\\Users\\Sai Yarram\\Documents\\GitHub\\battleship\\Help.JPG");
	

	LogIn(){
		super("User Authenticication");
		setSize(600,600);
		super.setLocationRelativeTo(null);
		loginScreen.setLayout(null);
		JLabel Background = new JLabel();
		Background.setIcon(imageIcon);
		loginScreen.setOpaque(false);
		loginScreen.setLayout(new GridBagLayout());
		
		loginScreen.add(Background);
		loginScreen.add(loginLabel);
		loginScreen.add(userField);
		loginScreen.add(passwordLabel);
		loginScreen.add(passwordField);
		loginScreen.add(loginButton);
		

		loginLabel.setFont(new Font("Courier New Bold", Font.PLAIN, 18));
		passwordLabel.setFont(new Font("Courier New Bold", Font.PLAIN, 18));
		getContentPane().add(loginScreen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		loginEvent();
	}	
		public void loginEvent(){
			loginButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String username = userField.getText().toLowerCase();
					String password = passwordField.getText();
					 found = PlayerDatabase.runme(username,password);
					 
					if(found && !alreadyLoggedIn(username)){
						Player.setCurrentUser(username);
						PlayerDatabase.login(username);
						loggingin(username);
						JOptionPane.showMessageDialog(null,"Login Verified");
						HomeScreen home = new HomeScreen();
						home.setVisible(true);
						dispose();
						
					}else if(!found){
						userField.setText("");
						passwordField.setText("");
						userField.requestFocus();
					    JOptionPane.showMessageDialog(null,"Wrong Username / Password","ERROR", 2);

						
				}else if(alreadyLoggedIn(username)){
					PlayerDatabase.alreadyloggedin(username);

					userField.setText("");
					passwordField.setText("");
					userField.requestFocus();
				    JOptionPane.showMessageDialog(null,"ERROR! ACCOUNT ALREADY SIGNED IN","ERROR", 0);

				}
			}
		});
	}
		
		public static boolean alreadyLoggedIn(String name){
			loggedin = false;
			String username = name;
			File file = new File("loggedinplayers");
			Scanner scan;
			try {
				scan = new Scanner(file);
			
			while (scan.hasNext()) {
			    String line = scan.next();
			    if(line.equals(username)) { 
			    loggedin = true;
			    }
			}
			scan.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return loggedin;
		}

	public static void loggingin(String name){
			String username = name;
			FileWriter writer;
			try {
				writer = new FileWriter("loggedinplayers", true);
				writer.write(username + "\n");
				writer.close();		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void endofgamelog(){
			FileWriter writer,p1writer,p2writer,p3writer;
			try {
				writer = new FileWriter("loggedinplayers");
				writer.write("");
				
				p1writer = new FileWriter("player_one's_ships");
				p1writer.write("");
				
				p2writer = new FileWriter("player_two's_ships");
				p2writer.write("");
				
				p3writer = new FileWriter("firecoordinant");
				p3writer.write("");
				
				writer.close();		
				p1writer.close();
				p2writer.close();
				p3writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}	
	


}

