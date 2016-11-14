import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public  class LogIn extends JFrame{
	static Player currentUser = new Player();
	public static void main(String[] args) {
		LogIn loginscreen = new LogIn();
	}
	
	JButton loginButton = new JButton("Login");
	JPanel loginScreen = new JPanel();
	JTextField userField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	GridLayout experimentLayout = new GridLayout(0,1);
	JLabel loginLabel = new JLabel("Username: ");
	JLabel passwordLabel = new JLabel("Password: ");

	LogIn(){
		super("User Authenticication");
		setSize(300,200);
		super.setLocationRelativeTo(null);

		loginScreen.setLayout(experimentLayout);
		loginScreen.add(loginLabel);
		loginScreen.add(userField);
		loginScreen.add(passwordLabel);
		loginScreen.add(passwordField);
		loginScreen.add(loginButton);


		getContentPane().add(loginScreen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		loginEvent();
	}	
	static boolean found;
		public void loginEvent(){
			loginButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String username = userField.getText().toLowerCase();
					String password = passwordField.getText();
					 found = PlayerDatabase.runme(username,password);
					if(found){
						Player.setCurrentUser(username);
						JOptionPane.showMessageDialog(null,"We Made It");
						HomeScreen home = new HomeScreen();
						home.setVisible(true);
						dispose();
						
					}else{
						userField.setText("");
						passwordField.setText("");
						userField.requestFocus();
						JOptionPane.showMessageDialog(null,"Wrong Username / Password");
				}
			}
		});
	}
}