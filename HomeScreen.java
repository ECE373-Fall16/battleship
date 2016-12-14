import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import sun.audio.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;


	public class HomeScreen extends JFrame {
		public static void main(String[] args) {
			HomeScreen ourGUI = new HomeScreen();
		}
				
				JButton GameStart = new JButton("Start Game");
				JButton LeaderBoard = new JButton("Leaderboard");
				JButton Options = new JButton("Options");
				JButton Help = new JButton("Help");
						
				JPanel HomeScreen = new JPanel();
				
				GridLayout experimentLayout = new GridLayout(0,1);

				HomeScreen(){
					
					super("Welcome " + Player.getCurrentUser() + "!");
					setSize(600,600);
					super.setLocationRelativeTo(null);
					Music();
					HomeScreen.setLayout(experimentLayout);
					
					HomeScreen.add(GameStart);
					HomeScreen.add(LeaderBoard);
					HomeScreen.add(Options);
					HomeScreen.add(Help);


					getContentPane().add(HomeScreen);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setVisible(true);
					setResizable(true);
					
					GameStartEvent();
					LeaderBoardEvent();
					OptionsEvent();
					HelpEvent();
					
				}
				
				
			public static void Music(){
				try {
				File Music = new File("C:\\Users\\Sai Yarram\\Documents\\GitHub\\battleship\\Boat.wav");
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
			public void GameStartEvent(){
				GameStart.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent enter) {
						
						File file = new File("loggedinplayers");

						try {
							Scanner scanner = new Scanner(file);
							int lineNum = 0;
							while (scanner.hasNextLine()) {
								String line = scanner.nextLine();
								lineNum++;
								}
							if(lineNum > 1){							
								GameEngine.main(null);
								dispose();
							}else{
							JOptionPane.showMessageDialog(null,"Waiting For Opponent To Connect...","Connecting...", 1);
							}
							
						}catch(FileNotFoundException e) { 
							//handle this
						}	
				}
				
			});
		}

			public void LeaderBoardEvent(){
				LeaderBoard.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent leaderboard) {
						JOptionPane.showMessageDialog(null,"System Under Construction","ERROR #404", 2);
					}
				});
			}
			public void OptionsEvent(){
				Options.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent options) {
						JOptionPane.showMessageDialog(null,"System Under Construction","ERROR #404", 2);
					}		
				});
			}
			public void HelpEvent(){
				Help.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent help) {
						JOptionPane.showMessageDialog(null,"System Under Construction","ERROR #404", 2);
					}
				});
			}	
}