    import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;


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
				}
				
				public void GameStartEvent(){
					GameStart.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent login) {
							BattleshipEngine.main(null);
							dispose();
						}
					
				});
			}
				}
	

