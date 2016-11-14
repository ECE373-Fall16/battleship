import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	static Socket socket;//new socket
	static DataInputStream in;//new input stream
	static DataOutputStream out;//new output stream

	
@SuppressWarnings("resource")
public static void main(String[] args) throws Exception{//run
	System.out.println("Connecting...");//connecting
	socket = new Socket("localhost",22231);//sets socket to ipaddress and port
	System.out.println("Connection Successful");//print it connected
	in = new DataInputStream(socket.getInputStream());//get input 
	out = new DataOutputStream(socket.getOutputStream());//send ooutput
	Input input = new Input(in);
	Thread thread = new Thread(input);
	thread.start();
	Scanner sc = new Scanner(System.in);
	PlayerDatabase.initializedb();

	while(!LogIn.found){
	  Thread.sleep(1000);
	}
	
	String current = Player.getCurrentUser();
	
	System.out.println("Current User is " + current);
	out.writeUTF(current);
		
	while(!LayoutSetup.set){
		  Thread.sleep(1000);
	}
	System.out.println("ALL SET BOYS");
	
	out.writeUTF(LayoutSetup.getFinalCoordinates());
	
	while(true){
		String sendmessage = sc.nextLine();
		out.writeUTF(sendmessage);	

	}


}
}

class Input implements Runnable{
	
	DataInputStream in;
	
	public Input(DataInputStream in){
		this.in = in;
	}
	public void run() {
		while(true){
			String message;
			try {
				 message = in.readUTF();
				System.out.println(message);
 
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
}