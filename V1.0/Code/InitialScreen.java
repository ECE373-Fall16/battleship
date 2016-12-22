import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class InitialScreen extends JFrame {
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		InitialScreen initial = new InitialScreen();
		initial.setLocationRelativeTo(null);
		initial.setBounds(800, 400, 800, 800);
		initial.setLocationRelativeTo(null);

	}

	private Image image;
	JButton LogInB = new JButton("Log In");
	JButton NewUserB = new JButton("New User");
	Color Grey = new Color(192, 192, 192);

	public InitialScreen() {
		image = new ImageIcon("Traveling through the sea.gif").getImage();

		JPanel container = new MyBackground();
		container.setLayout(null);
		LogInB.setBounds(250, 375, 300, 70);
		LogInB.setBorderPainted(true);
		LogInB.setFont(new Font("Serif", Font.BOLD, 24));
		LogInB.setForeground(Color.white);
		LogInB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		NewUserB.setBounds(250, 450, 300, 70);
		NewUserB.setBorderPainted(true);
		NewUserB.setFont(new Font("Serif", Font.BOLD, 24));
		NewUserB.setForeground(Color.white);
		NewUserB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel x1 = new JLabel("Fleet Destroyer");
		x1.setFont(new Font("Serif", Font.BOLD, 75));
		container.add(x1);
		x1.setBounds(160, 100, 500, 100);
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
		Music();

	}

	public void loginEvent() {
		LogInB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent login) {
				LogIn.main(null);
				dispose();
			}

		});
	}

	public void signupEvent() {
		NewUserB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent newuser) {
				SignUp.main(null);
				dispose();
			}

		});
	}

	public static void Music() {
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
			// Paint background first
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

			// Paint the rest of the component. Children and self etc.
			super.paintComponent(g);
		}
	}

}