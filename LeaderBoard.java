import java.awt.*;
import java.awt.event.*;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;

public class LeaderBoard extends JFrame
{




public LeaderBoard()
	{
		super("Fleet Destroyer Engine");
	}

public void createLeaderBoard()
	{
	
	JFrame frame = new JFrame("LeaderBoard");
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setSize(500,500);
	
	//frame.pack();
	frame.setVisible(true);
	JPanel contentPane = new JPanel();
	//contentPane.setLayout();
	contentPane.setPreferredSize(new Dimension(1500,1500));
	//contentPane.setBackground(Color.BLACK);
	contentPane.setOpaque(true);
	contentPane.setVisible(true);
	contentPane.setBackground(new Color(204,204,255));
	frame.getContentPane().setBackground(new Color(204,204,255));
	
	frame.add(contentPane);
	
	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
	
	JTextArea Position = new JTextArea(5,5);
	Position.setText("Position");
	Position.setEditable(false);
	contentPane.add(Position);
	JTextArea.setBackground(new Color(204,204,255));
	
	
	
	JTextArea Name = new JTextArea();
	Name.setText("UserName");
	Name.setEditable(false);
	contentPane.add(Name);
	
	JTextArea Wins = new JTextArea();
	Wins.setText("Wins");
	Wins.setEditable(false);
	contentPane.add(Wins);
	
	JTextArea Loses = new JTextArea();
	Loses.setText("Loses");
	Loses.setEditable(false);
	contentPane.add(Loses);
	
	JTextArea Experience = new JTextArea();
	Experience.setText("EXP");
	Experience.setEditable(false);
	contentPane.add(Experience);
	
	
	}






	public static void main(String[] args){
			 SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					new LeaderBoard().createLeaderBoard();

				}

			});
	   

		}
	

}