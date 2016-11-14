
/**
 * Test
 */
import java.util.*;
public class Class{
	 static int num;
     static List<Player> players = new ArrayList();
     static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
        
        Player admin = new Player();
        admin.setUser("admin");
        admin.setPassword("admin");
        players.add(admin);
        
        
        System.out.println("How many players?\n");
        num = scan.nextInt();
        
        scan.nextLine();
       // scan.close();

        

        for(int i = 0; i < num; i++){
        	Player newPerson = new Player();

            System.out.println("Enter Username: ");
            newPerson.setUser(scan.nextLine());
            System.out.println("Enter Password: ");
            newPerson.setPassword(scan.nextLine());
            
            players.add(newPerson);
        }
        runme();
	}
    
    public static void runme(){
        System.out.println(players.toString());
        
        System.out.println("Enter your Username");
        String response = scan.nextLine();
        
        boolean found = false;
        String currentUser = null;
        
        for(Player u : players){
            if(u.getUser() != null && u.getUser().equals(response)){
            	found = true;
            	currentUser = u.getUser();
            }
        }
        
       if(found){
        	System.out.println("Eyyy " + currentUser+ " is Verified");
       }else{
        	System.out.println("You not in the system " + response);
        	runme();
        	
        }
        
        scan.close();
    
	}
}


