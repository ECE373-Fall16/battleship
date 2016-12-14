import java.io.*;
import java.net.*;

public class SuperServer {
	public static void main(String[] args) {
		GameSocket game = new GameSocket();
		ChatLoader chat = new ChatLoader();
		Thread t1 = new Thread(game);
		Thread t2 = new Thread(chat);
		t1.start();
		t2.start();
	}
}

class GameSocket implements Runnable {

	@Override
	public void run() {
		try {
			Server.main(null);
		} catch (Exception e) {}
	}

}

class ChatLoader implements Runnable{

	@Override
	public void run() {
		try {
			ChatSocket.main(null);
		} catch (Exception e) {}
	}
	
}

class ChatSocket {
	static ServerSocket chatSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	static Chat[] user = new Chat[20];

	public static void main(String[] args) throws Exception {

		System.out.println("Starting Chat Server");
		chatSocket = new ServerSocket(3390);
		System.out.println("Chat Server Started");

		while (true) {
			socket = chatSocket.accept();
			for (int i = 0; i < 20; i++) {
				System.out.println("Connection from: " + socket.getInetAddress());
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				if (user[i] == null) {
					user[i] = new Chat(out, in, user);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}
}



class Chat implements Runnable{

	DataOutputStream out;
	DataInputStream in;
	Chat[] user = new Chat[20];
	String name = "Hadi";
	//String name = Player.getCurrentUser();
	public Chat(DataOutputStream out, DataInputStream in, Chat[] user){
		this.out = out;
		this.in = in;
		this.user = user;
	}

	public void run() {
			while(true){
			try {
				String message = in.readUTF();
				for(int i = 0; i < 20; i++){
					if(user[i] != null){
						user[i].out.writeUTF(message);
					}
				}
			} catch (IOException e) {}
		}	
	}
}