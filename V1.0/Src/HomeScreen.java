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
import java.util.*;
import javax.sound.sampled.*;


@SuppressWarnings("serial")
public class HomeScreen extends JFrame {
    public static void main(String[] args) {
        new HomeScreen();
		Music();
    }
     
    private Image image;
	JButton GameStart = new JButton("Start Game");
	JButton LeaderBoardB = new JButton("Leaderboard");
	JButton Options = new JButton("Options");
	JButton Help = new JButton("Help");
	JButton Chat = new JButton("Chat");
	
     
    public HomeScreen() {
        image = new ImageIcon("homescreen.jpg").getImage();
        
        JPanel container = new MyBackground();
		container.setLayout(null);
		
		GameStart.setBounds(200,51,200,100);
		GameStart.setOpaque(false);
		GameStart.setContentAreaFilled(false);
		GameStart.setBorderPainted(true);
		GameStart.setFont(new Font("Serif", Font.BOLD, 24));
		GameStart.setForeground(Color.WHITE);
		GameStart.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
		LeaderBoardB.setBounds(200,151,200,100);
		LeaderBoardB.setOpaque(false);
		LeaderBoardB.setContentAreaFilled(false);
		LeaderBoardB.setBorderPainted(true);
		LeaderBoardB.setFont(new Font("Serif", Font.BOLD, 24));
		LeaderBoardB.setForeground(Color.WHITE);
		LeaderBoardB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
		
		Chat.setBounds(200,251,200,100);
		Chat.setOpaque(false);
		Chat.setContentAreaFilled(false);
		Chat.setBorderPainted(true);
		Chat.setFont(new Font("Serif", Font.BOLD, 24));
		Chat.setForeground(Color.WHITE);
		Chat.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));	

		Options.setBounds(200,351,200,100);
		Options.setOpaque(false);
		Options.setContentAreaFilled(false);
		Options.setBorderPainted(true);
		Options.setFont(new Font("Serif", Font.BOLD, 28));
		Options.setForeground(Color.WHITE);
		Options.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));	
        
		Help.setBounds(200,451,200,100);
		Help.setOpaque(false);
		Help.setContentAreaFilled(false);
		Help.setBorderPainted(true);
		Help.setFont(new Font("Serif", Font.BOLD, 24));
		Help.setForeground(Color.WHITE);
		Help.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
		
		add(container);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    	super.setLocationRelativeTo(null);
        setSize(600, 600);
    	super.setLocationRelativeTo(null);
		container.add(GameStart);
		container.add(LeaderBoardB);
		container.add(Chat);
		container.add(Options);
		container.add(Help);
        setVisible(true);
		setResizable(false);
		
		GameStartEvent();
		LeaderBoardEvent();
		ChatEvent();
		OptionsEvent();
		HelpEvent();
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
			
	
	public void GameStartEventtttt(){
		GameStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent enter) {
				
				File file = new File("loggedinplayers");

				try {
				    Scanner scanner = new Scanner(file);
				    int lineNum = 0;
				    while (scanner.hasNextLine()) {
				        scanner.nextLine();
				        lineNum++;
				        }
				    scanner.close();
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
	
		
				public void GameStartEvent(){
					GameStart.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent enter) {
											
							    	GameEngine.main(null);
							    	dispose();
								
						}
					
				});
			}
				public void LeaderBoardEvent(){
					LeaderBoardB.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent leaderboard) {
							try {
								LeaderBoard.main(null);
							} catch (Exception e) {}
					    	dispose();
						}
					});
				}
				public void ChatEvent(){
					Chat.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent options) {
						try {
							ChatClient.main(null);
						} catch (Exception e) {}
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
							HelpScreen.main(null);
						}
					});
				}	
}