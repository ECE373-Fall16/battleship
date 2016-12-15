import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;




public class InitialScreen extends JFrame {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
	InitialScreen initial =	new InitialScreen();
	initial.setBounds(800,400,800,800);
	}
	
	private Image image;
	JButton LogInB = new JButton("Log In");
	JButton NewUserB = new JButton("New User");
	Color Grey = new Color(192,192,192);
	
	public InitialScreen() {
		image = new ImageIcon("Traveling through the sea.gif").getImage();
		
		JPanel container = new MyBackground();
		container.setLayout(null);
		LogInB.setBounds(250,375,300,70);
		LogInB.setBorderPainted(true);
		LogInB.setFont(new Font("Serif", Font.BOLD, 24));
		LogInB.setForeground(Color.DARK_GRAY);
		LogInB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		NewUserB.setBounds(250,450,300,70);
		NewUserB.setBorderPainted(true);
		NewUserB.setFont(new Font("Serif", Font.BOLD, 24));
		NewUserB.setForeground(Color.DARK_GRAY);
		NewUserB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JLabel x1 = new JLabel("Fleet Destroyer");
		x1.setFont(new Font("Serif", Font.BOLD, 75));
		container.add(x1);
		x1.setBounds(160,100,500,100);
		x1.setForeground(Color.WHITE);
			
		add(container);
		container.add(LogInB);
		container.add(NewUserB);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
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
			
	public class MyBackground extends JPanel {
		
		public MyBackground() {
			setBackground(new Color(0, true));
		}
		
		@Override
		public void paintComponent(Graphics g) {
			//Paint background first
			g.drawImage (image, 0, 0, getWidth (), getHeight (), this);
			
			//Paint the rest of the component. Children and self etc.
			super.paintComponent(g);
		}
	}
	
}