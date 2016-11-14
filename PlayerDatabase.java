import java.util.*;
import java.io.*;

public class PlayerDatabase{
	
	 static int num;
     static List<Player> players = new ArrayList();
     static Scanner scan = new Scanner(System.in);

     
	public static void main(String[] args){
		initializedb();
	}
		
	/*Incase we want to initialize new users from here
	 *      public static void initializenewusers(){   

        System.out.println("Create Your Database, How Many New Users?");
        num = scan.nextInt();
        scan.nextLine();

        for(int i = 0; i < num; i++){        	
            System.out.println("Enter Username: ");
            String newUser = scan.nextLine();
            System.out.println("Enter Password: ");
            String newPassword = scan.nextLine();            
            
            try {
                File db = new File("Database");
                PrintWriter printer = new PrintWriter(new FileWriter(db, true));
                    printer.write(newUser + " " + newPassword + "\n");
                    printer.flush();
                }catch (IOException e){
				e.printStackTrace();
			}
        }
        initializedb();
	}
	 */
     
     public static void initializedb(){                
         System.out.println("Initializing Database...");
         try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
         
         String [] ids;
         File file = new File("Database");
         String user = null;
         String pass = null;
         BufferedReader br = null;
         
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
         String line = null;
         try {
			while( (line = br.readLine())!= null ){
			 	Player newPerson = new Player();
			      ids = line.split("\\s+");
			      user = ids[0];
			      pass = ids[1];  
			      newPerson.setUser(user);
			      newPerson.setPassword(pass);
			      players.add(newPerson);
			      System.out.println("*" + user + "-" + pass + "*");

			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
         
         /*
         
         System.out.println("Do You Want To Add New Users?(Y/N): ");
         String addition = scan.nextLine();


         	(if(addition.equals("y")){
         		initializenewusers();
         	}else{
         		System.out.println("ALL DONE");
         		//LogIn.main(null);;
         		InitialScreen.main(null);
         	}
         	*/    
			try {
				Thread.sleep(1000);
				System.out.println("Launching FleetDestroyer...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

         
  		InitialScreen.main(null);

         }

         
	public static boolean runme(String user, String password){
    	 
    	 String userresponse = user;
    	 String passresponse = password;
         
         boolean found = false;
         String currentUser = null;
         
         for(Player u : players){
             if((u.getUser() != null && u.getUser().equals(userresponse)) && (u.getPass() != null && u.getPass().equals(passresponse))){
             	found = true;
             	currentUser = u.getUser();
             }
         }
         
         return found;
     
 	}
     
     public static boolean newUserForm(String userx, String passx){
    	 String user = userx;
    	 String pass = passx;
    	 boolean added = true;
         Player newUser = new Player();
         
         for(Player u : players){
             if((u.getUser() != null && u.getUser().equals(user))){
             added = false;
            	 System.out.println("Kill me");
             }
         }
         if(added){
        	 newUser.setUser(user);
        	 newUser.setPassword(pass);
        	 players.add(newUser);
	            try {
	                File db = new File("Database");
	                PrintWriter printer = new PrintWriter(new FileWriter(db, true));
	                    printer.write(user + " " + pass + "\n");
	                    printer.flush();
	                }catch (IOException e){
					e.printStackTrace();
				}
         }
         
         return added;
     
     }
     
}