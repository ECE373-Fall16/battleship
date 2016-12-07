    import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;


	public class InitialScreen extends JFrame {

		public static void main(String[] args) {
			InitialScreen initial = new InitialScreen();
		}
				JButton LogInB = new JButton("Log In");
				JButton NewUserB = new JButton("New User");
				
				JPanel InitialScreen = new JPanel();
				
				GridLayout experimentLayout = new GridLayout(0,1);

				InitialScreen(){
					
					JWindow window = new JWindow();
					try {
						window.getContentPane().add(
						    new JLabel("", new ImageIcon(new URL("http://im2.ezgif.com/tmp/ezgif.com-896f73fa97.gif")), SwingConstants.CENTER));
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					window.setBounds(150, 150, 414, 211);
					window.setLocationRelativeTo(null);

					window.setVisible(true);
					try {
					    Thread.sleep(15000);
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}
					window.setVisible(false);
					window.dispose();
					
					
					
					
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

