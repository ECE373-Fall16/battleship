import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HelpScreen extends JFrame
{

	public HelpScreen()
	{
		super("Fleet Destroyer Engine");
	}

	public void CreateAndDisplayGUI()
	{

		setTitle("Help Screen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.set
		
		ImageIcon Icon = new ImageIcon("C:\\Users\\Sai Yarram\\Documents\\GitHub\\battleship\\Help.JPEG");
		JLabel label = new JLabel("", Icon, JLabel.CENTER);
		contentPane.setPreferredSize(new Dimension (500,500));
		contentPane.add(label);
		contentPane.setBackground(Color.BLACK);
		
		
		
		
		JTextField userName = new JTextField(20);
		contentPane.add(userName);
		add(contentPane);
		setVisible(true);
		pack();
		
	
		
		
		
		
	}

	public static void main(String[] args){
         SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new HelpScreen().CreateAndDisplayGUI();

            }

        });
   

    }


}