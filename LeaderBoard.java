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
	//frame.setBounds(0,0,1000,1000);
	//frame.pack();
	frame.setVisible(true);
	JPanel contentPane = new JPanel();
	//contentPane.setLayout();
	contentPane.setPreferredSize(new Dimension(1500,1500));
	
	
	contentPane.setBackground(Color.BLACK);
	contentPane.setSize(400,400);
	contentPane.setVisible(true);
	frame.getContentPane().add(contentPane);
	
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