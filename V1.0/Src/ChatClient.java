import java.net.*;
import java.io.*;

public class ChatClient {
	static Socket socket;//new socket
	static DataInputStream in;//new input stream
	static DataOutputStream out;//new output stream

	
public static void main(String[] args) throws Exception{//run
	System.out.println("Connecting...");//connecting
	socket = new Socket("104.196.169.95",3390);//sets socket to ip address and port
	System.out.println("Connection Successful");//print it connected
	in = new DataInputStream(socket.getInputStream());//get input 
	out = new DataOutputStream(socket.getOutputStream());//send output
	Input input = new Input(in);
	Thread thread = new Thread(input);
	thread.start();
	
	MainChatGUI.main(null);
}


public static void sendMessage(String message) throws IOException {
	out.writeUTF(message);

	}

public static void displayMessage(String message){
	MainChatGUI.chatBox.append(message);
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
				ChatClient.displayMessage(message); 
			} catch (IOException e) {
			}
		}
	}
	
}