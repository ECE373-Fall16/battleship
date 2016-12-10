import java.awt.*;
import java.awt.event.*;
import java.awt.Frame;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class LeaderBoard extends JFrame
{

public LeaderBoard() 
		{
			super("Fleet Destroyer Engine");
		}

public void CreateLeaderBoard()
	{
		String [] columnNames = {"Position", "UserName", "Wins", "Loses", "Experience"};
		Object [][] Data = {
							{1, "Smith",0, 0, 0},
							{2, "Doe"  ,0, 0, 0},
							{3, "Black",0, 0, 0},
							{4, "White",0, 0, 0},
							{5, "Brown",0, 0, 0},
							};
		
		DefaultTableModel Model = new DefaultTableModel(Data,columnNames) 
		{
			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
			
		};					

		JTable Table = new JTable(Model);
		this.add(new JScrollPane(Table));
        this.setTitle("Table ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
		this.setPreferredSize(new Dimension(150,100));
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
					new LeaderBoard().CreateLeaderBoard();

				}

			});
	 

		}
	

}