    import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
import java.io.*;
import java.util.*;


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
					setSize(300,200);
					super.setLocationRelativeTo(null);

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