    import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;


	public class InitialScreen extends JFrame {

		public static void main(String[] args) {
			InitialScreen ourGUI = new InitialScreen();
		}
				JButton LogInB = new JButton("Log In");
				JButton NewUserB = new JButton("New User");
				
				JPanel InitialScreen = new JPanel();
				
				GridLayout experimentLayout = new GridLayout(0,1);

				InitialScreen(){
					setSize(300,200);
					super.setLocationRelativeTo(null);

					InitialScreen.setLayout(experimentLayout);
					
					InitialScreen.add(LogInB);
					InitialScreen.add(NewUserB);

					getContentPane().add(InitialScreen);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setVisible(true);
					setResizable(false);
					loginEvent();
					signupEvent();
				}
				
				public void loginEvent(){
					LogInB.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent login) {
							LogIn.main(null);
							dispose();
						}
					
				});
			}
				public void signupEvent(){
					NewUserB.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent newuser) {
							SignUp.main(null);
							dispose();
						}
					
				});
			}
				
	}

