
/**
 * Fake Player Class
 */
public class tempPlayer{

    String username,password;
	static String currentUser;
    public tempPlayer(){
    	username = null;
    	password = null;
    }
       

    public tempPlayer(String user, String pass){
        username = user;
        password = pass;
    }

    public void setUser(String user){
        this.username = user;
    }
    public void setCurrentUser(String cuser){
    	Player.currentUser = cuser;
    }
    public static String getCurrentUser(){
    	return currentUser;
    }
    
    public void setPassword(String pass){
        this.password = pass;
    }
    
    public String getUser()
    {
        return username;
    }
    
    public String getPass(){
        return password;
    }
    
    
    
    	 public String toString() {
    	        return ("Username: "+this.getUser()+
    	                    "; Password: "+ this.getPass()) + "\n";
    	   }    }

