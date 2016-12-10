import java.awt.*;
import java.awt.event.*;
import java.awt.Frame;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;

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
		frame.setBackground(Color.BLACK);
	
	
		frame.setVisible(true);
		JPanel contentPane = new JPanel();
	
		contentPane.setPreferredSize(new Dimension(1500,1500));
		contentPane.setOpaque(true);
		frame.getContentPane().setBackground(new Color(204,204,255));
		frame.add(contentPane);
		//contentPane.setVisible(true);
		contentPane.setBackground(Color.BLACK);
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		Font font = new Font("Comic Sans MS", Font.BOLD, 18);
		
		JTextArea Position = new JTextArea(5,5);
		Position.setFont(font);
		Position.setForeground(Color.BLUE);
		Position.setText("Position");
		Position.setEditable(false);
		contentPane.add(Position);
		//JTextArea.setBackground(new Color(204,204,255));
			
		JTextArea Name = new JTextArea();
		Name.setFont(font);
		Name.setForeground(Color.BLUE);
		Name.setText("UserName");
		Name.setEditable(false);
		contentPane.add(Name);
		System.out.println(Name.getPreferredSize());
		
		JTextArea Wins = new JTextArea();
		Wins.setFont(font);
		Wins.setForeground(Color.BLUE);
		Wins.setText("Wins");
		Wins.setEditable(false);
		//contentPane.add(Wins);
		
		
		JTextArea Loses = new JTextArea();
		Loses.setFont(font);
		Loses.setForeground(Color.BLUE);
		Loses.setText("Loses");
		Loses.setEditable(false);
		contentPane.add(Loses);
		
		JTextArea Experience = new JTextArea();
		Experience.setFont(font);
		Experience.setForeground(Color.BLUE);
		Experience.setText("EXP");
		Experience.setEditable(false);
		contentPane.add(Experience);
		
		frame.pack();
		
		//Reading from the File.
		GetLeaderBoardValues();
		
	}
	
	public void GetLeaderBoardValues() 
	{
	try 
		{
			FileReader File = new FileReader("C:\\Users\\Sai Yarram\\Documents\\GitHub\\battleship\\LeaderBoard.txt");
			BufferedReader Reader = new BufferedReader(File);
			String text = "";
			String line = Reader.readLine();
			while (line!= null)
				{
					text+= line;
					line = Reader.readLine();
					System.out.println(text);
				}
			Reader.close();
		}
	catch(FileNotFoundException ex){System.out.println("Unable to open file ");}
    catch(IOException ex){System.out.println("Error reading file");}
	}





	public static void main(String[] args) throws Exception {
			 SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					new LeaderBoard().createLeaderBoard();

				}

			});
	 

		}
	

}