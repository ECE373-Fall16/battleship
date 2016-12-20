import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignUp extends JFrame {
	static Player currentUser = new Player();

	public static void main(String[] args) {
		SignUp signup = new SignUp();
		signup.setLocationRelativeTo(null);

	}

	JButton signupButton = new JButton("Sign Up");
	JPanel signupScreen = new JPanel();
	JTextField newuserField = new JTextField();
	JPasswordField newpasswordField = new JPasswordField();
	FlowLayout experimentLayout = new FlowLayout();
	JLabel newloginLabel = new JLabel("Username: ");
	JLabel newpasswordLabel = new JLabel("Password: ");

	SignUp() {
		super("User Authenticication");
		setSize(600, 600);
		super.setLocationRelativeTo(null);

		signupScreen.setLayout(experimentLayout);
		signupScreen.add(newloginLabel);
		signupScreen.add(newuserField);
		signupScreen.add(newpasswordLabel);
		signupScreen.add(newpasswordField);
		signupScreen.add(signupButton);

		getContentPane().add(signupScreen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		signupEvent();
	}

	public void signupEvent() {
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = newuserField.getText().toLowerCase();
				@SuppressWarnings("deprecation")
				String password = newpasswordField.getText();
				boolean found = false;

				if ((username.matches(".*\\s+.*")) || password.matches(".*\\s+.*") || username.equals("")
						|| password.equals("")) {
					JOptionPane.showMessageDialog(null, "Error! No Spaces between Usernames/Passwords!", "ERROR", 2);

					newuserField.setText("");
					newpasswordField.setText("");
					newuserField.requestFocus();
				} else {
					found = PlayerDatabase.newUserForm(username, password);
					if (found) {

						JOptionPane.showMessageDialog(null, "User Successfully Created!");
						InitialScreen.main(null);
						dispose();

					} else {
						newuserField.setText("");
						newpasswordField.setText("");
						newuserField.requestFocus();

						JOptionPane.showMessageDialog(null, "Error! Username Is Already In Use!", "ERROR", 2);
					}
				}
			}
		});
	}
}