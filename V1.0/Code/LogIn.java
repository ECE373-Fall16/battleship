import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;



@SuppressWarnings("serial")
public class LogIn extends JFrame {
	static Player currentUser = new Player();
	static boolean found = false;
	static boolean loggedin = false;
	

    public static void main(String[] args) {
		new LogIn();

    }
    private Image image;
	
	JButton loginButton = new JButton("Login");
	JPanel loginScreen = new JPanel();
	JTextField userField = new JTextField(10);
	JPasswordField passwordField = new JPasswordField(10);
	JLabel loginLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	Color Grey = new Color(192,192,192);
	
    public LogIn() {
    image = new ImageIcon("LogIn.jpg").getImage();

	
	JPanel container = new MyBackground();

	container.setLayout(null);
	userField.setBounds(605,590,135,25);
	userField.setFont(new Font("Serif", Font.BOLD, 24));
	userField.setBackground(Grey);
	userField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	passwordField.setBounds(605,620,135,25);
	passwordField.setFont(new Font("Serif", Font.BOLD, 24));
	passwordField.setBackground(Grey);
	passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	loginButton.setBounds(450,650,290,35);
	loginButton.setBorderPainted(true);
	loginButton.setFont(new Font("Serif", Font.BOLD, 24));
	loginButton.setForeground(Color.BLACK);
	loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	loginLabel.setBounds(455,590,145,25);
	loginLabel.setFont(new Font("Serif", Font.BOLD, 24));
	loginLabel.setBackground(Color.BLACK);
	loginLabel.setForeground(Color.BLACK);
	loginLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	passwordLabel.setBounds(455,620,145,25);
	passwordLabel.setFont(new Font("Serif", Font.BOLD, 24));
	passwordLabel.setBackground(Color.BLACK);
	passwordLabel.setForeground(Color.BLACK);
	passwordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	
	container.add(userField);
	container.add(passwordField);
	container.add(passwordLabel);
	container.add(loginLabel);
	container.add(loginButton);
	

	
	
	add(container);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	super.setLocationRelativeTo(null);
	setBounds(800,400,800,800);
	super.setLocationRelativeTo(null);

    setVisible(true);
	setResizable(false);
	loginEvent();
	//Music();
    }
	

		public void loginEvent(){
			loginButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String username = userField.getText().toLowerCase();
					@SuppressWarnings("deprecation")
					String password = passwordField.getText();
					
					Client.sendUser(username, password);
					
					if(Client.recieveConfirmation()){
							Player.setCurrentUser(username);
							JOptionPane.showMessageDialog(null,"Login Verified");
							HomeScreen home = new HomeScreen();
							home.setVisible(true);
							dispose();
					}else{
						userField.setText("");
						passwordField.setText("");
						userField.requestFocus();
					    JOptionPane.showMessageDialog(null,"ERROR SIGNING IN","ERROR", 2);

					}
					
					
			}
		});
	}

	
	public static void Music(){
				try {
				File Music = new File("Background.wav");
				 AudioInputStream audioIn = AudioSystem.getAudioInputStream(Music);
				 Clip clip = AudioSystem.getClip();
				 clip.open(audioIn);
				 clip.loop(5);
				 
				 }
				 
				 catch (UnsupportedAudioFileException e) {
					 e.printStackTrace();
				  } catch (IOException e) {
					 e.printStackTrace();
				  } catch (LineUnavailableException e) {
					 e.printStackTrace();
				  }
				
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
