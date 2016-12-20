import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import java.io.*;
import javax.swing.table.*;
import java.util.Comparator;
import java.util.*;



@SuppressWarnings("serial")
public class LeaderBoard extends JPanel
{
static String text = "";
private static int LineCount = 0;
static String[][] LeaderBoardArray = new String[5][5];
static int[][] SortLeaderBoard = new int[5][5];
String PlayerA = "admin";
String PlayerB = "nick";
boolean Result = false;



	/*public LeaderBoard() 
			{
				super("Fleet Destroyer Engine");
			}*/

	public LeaderBoard()
	
	{
		setLayout(new BorderLayout());
		String [] columnNames = {"Position", "UserName", "Wins", "Loses", "Experience"};
		Object [][] Data = {
								{1, LeaderBoardArray[0][0],LeaderBoardArray[0][1], LeaderBoardArray[0][2], LeaderBoardArray[0][3]},
								{2, LeaderBoardArray[1][0],LeaderBoardArray[1][1], LeaderBoardArray[1][2], LeaderBoardArray[1][3]},
								{3, LeaderBoardArray[2][0],LeaderBoardArray[2][1], LeaderBoardArray[2][2], LeaderBoardArray[2][3]},
								{4, LeaderBoardArray[3][0],LeaderBoardArray[3][1], LeaderBoardArray[3][2], LeaderBoardArray[3][3]},
								{5, LeaderBoardArray[4][0],LeaderBoardArray[4][1], LeaderBoardArray[4][2], LeaderBoardArray[4][3]},
								};
			
			DefaultTableModel Model = new DefaultTableModel(Data,columnNames) 
			{
				@Override
				public boolean isCellEditable(int row, int column) 
				{
					return false;
				}
				
			};					
		JTable table = new JTable(Model);
        table.setOpaque( false );
        DefaultTableCellRenderer renderer =
            (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane( table );
        scrollPane.setOpaque( false );
        scrollPane.getViewport().setOpaque( false );

        final ImageIcon icon = new ImageIcon("LeaderBoardImage.jpg");

        JPanel background = new JPanel( new BorderLayout() )
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.add( scrollPane );
        add(background);
		
		
	}
	
	public void CreateLeaderBoard()
		{
			GetLeaderBoardValues(); //Calls upon the values from the text file to fill the JTable.
			SortLeaderBoardValues(LeaderBoardArray);
			
			JFrame frame = new JFrame("LeaderBoard");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(new LeaderBoard());
			frame.setLocationByPlatform( true );
			frame.pack();
			frame.setVisible( true );
			
			UpdateLeaderBoardValues(LeaderBoardArray,PlayerA,PlayerB,Result);
			OverWriteLeaderBoardValues();
			
			
			
		}
		
	public static void GetLeaderBoardValues() 
		{
		try 
			{
				FileReader File = new FileReader("LeaderBoard.txt");
				BufferedReader Reader = new BufferedReader(File);
				String text = "";
				String line = Reader.readLine();
				while (LineCount!= 5) //While End of File. Grab Each Line of Text and send it to the method SetLeaderBoardValues
					{
						text= line;
						SetLeaderBoardValues(text,LineCount);
						line = Reader.readLine();
						LineCount++;
						//System.out.println(text);
					}
			Reader.close();
			}
		catch(FileNotFoundException ex){System.out.println("Unable to open file ");}
		catch(IOException ex){System.out.println("Error reading file");}
		}
		
	public static void SetLeaderBoardValues(String text, int LineCount) //Splits the line and sets them in an array 
	{
		String str = text;
		
		String[] Split = str.split(" ");
		int i = LineCount;
		for(int j=0;j<=4;j++)
		{
			LeaderBoardArray[i][j] = Split[j];			
		}
		
		
	}
	
	public static void SortLeaderBoardValues(String[][] LeaderBoardArray){
		
		Arrays.sort(LeaderBoardArray, new Comparator<String[]>()
		{
			 @Override
			public int compare(final String[] first, final String[] second){
				
				
				return Double.valueOf(second[1]).compareTo(Double.valueOf(first[1]));
			}
		
		}
		);
		
	}
	
	public static void OverWriteLeaderBoardValues()
	{
		File OverWrite = new File("LeaderBoard.txt");
		String append; 
		
		try
		{
			FileWriter OF = new FileWriter(OverWrite, false);
			BufferedWriter BF = new BufferedWriter(OF);
			for(int i=0; i<=4;i++)
				{
					for(int j=0;j<=4;j++)
					{
					append = LeaderBoardArray[i][j] + " ";
					BF.write(append +"" );
					
					//append = "";
					}		
					BF.newLine();
					//System.out.println("          ");
				}
				
			BF.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			
		}           
	}

	
	
	public static void UpdateLeaderBoardValues(String [][] LeaderBoardArray, String Player1, String Player2, boolean result){
		
		
		
		boolean found = false;
		int Wins;
		int Loses;
		int Exp;
		Random Rand = new Random();
		
		//If Result equals true then we know that Player1 won.
		if(result == true)
		{
			while(found == false)
			{
				for(int i =0; i<=4;i++){
					
					if(LeaderBoardArray[i][0].equals(Player1))
					{
						String W = LeaderBoardArray[i][1];
						Wins = Integer.parseInt(W);
						Wins = Wins+1;
						
						
						String E = LeaderBoardArray[i][3];
						Exp = Integer.parseInt(E);
						int R = Rand.nextInt(5) + 6; 
						Exp = Exp*R;
						
						W = String.valueOf(Wins);
						E = String.valueOf(Exp);
						
						LeaderBoardArray[i][1] = W;
						LeaderBoardArray[i][3] = E;
						break;
						
					}
					else{System.out.println("User Not Found 1" + " " + LeaderBoardArray[i][0]);}
					
				}
				for(int i =0; i<4;i++){
					if(LeaderBoardArray[i][0].equals(Player2))
					{
						String L = LeaderBoardArray[i][2];
						Loses = Integer.parseInt(L);
						Loses = Loses+1;
						
						String E = LeaderBoardArray[i][3];
						Exp = Integer.parseInt(E);
						int R = Rand.nextInt(5) + 2; 
						Exp = Exp*R;
						
						
						L = String.valueOf(Loses);
						E = String.valueOf(Exp);
						
						LeaderBoardArray[i][2] = L;
						LeaderBoardArray[i][3] = E;
						break;
						
					}
				}
				found = true;
				break;
			}
			
		}
		
		if(result == false)
		{
			while(found == false)
			{
				for(int i =0; i<=4;i++){
					
					if(LeaderBoardArray[i][0].equals(Player2))
					{
						String W = LeaderBoardArray[i][1];
						Wins = Integer.parseInt(W);
						Wins = Wins+1;
						
						
						String E = LeaderBoardArray[i][3];
						Exp = Integer.parseInt(E);
						int R = Rand.nextInt(5) + 6; 
						Exp = Exp*R;
						
						W = String.valueOf(Wins);
						E = String.valueOf(Exp);
						
						LeaderBoardArray[i][1] = W;
						LeaderBoardArray[i][3] = E;
						break;
						
					}
					else{//System.out.println("User Not Found 2" + " " + LeaderBoardArray[i][0]);
					}
					
				}
				for(int i =0; i<4;i++){
					if(LeaderBoardArray[i][0].equals(Player1))
					{
						String L = LeaderBoardArray[i][2];
						Loses = Integer.parseInt(L);
						Loses = Loses+1;
						
						String E = LeaderBoardArray[i][3];
						Exp = Integer.parseInt(E);
						int R = Rand.nextInt(5) + 2; 
						Exp = Exp*R;
						
						
						L = String.valueOf(Loses);
						E = String.valueOf(Exp);
						
						LeaderBoardArray[i][2] = L;
						LeaderBoardArray[i][3] = E;
						break;
						
					}
				}
				found = true;
				break;
			}
			
		}
	}
	
	public static String returnLeaderBoard() {
		GetLeaderBoardValues(); // Calls upon the values from the text file to
		SortLeaderBoardValues(LeaderBoardArray);
		String leaderBoard = "";

		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
					leaderBoard += LeaderBoardArray[i][j] + " ";
			}
			if (i != 4) {
				leaderBoard += "\n";
			}
		}

		return leaderBoard;

	}
	
	
public static void main(String[] args) throws Exception {
			 EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					new LeaderBoard().CreateLeaderBoard();
									
					//System.out.println(returnLeaderBoard());
				}

			});
	 

		}
	

}
