import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
 
public class MultiThreadedSocketServer {
 
    ServerSocket myServerSocket;
    boolean ServerOn = true;
 
 
    public MultiThreadedSocketServer() 
    { 
        try
        { 
            myServerSocket = new ServerSocket(11111); 
        } 
        catch(IOException ioe) 
        { 
            System.out.println("Could not create server socket on port 11111. Quitting."); 
            System.exit(-1); 
        } 
 
 
 
 
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("It is now : " + formatter.format(now.getTime()));
 
 
 
 
        // Successfully created Server Socket. Now wait for connections. 
        while(ServerOn) 
        {                        
            try
            { 
                // Accept incoming connections. 
                Socket clientSocket = myServerSocket.accept(); 
 
                // accept() will block until a client connects to the server. 
                // If execution reaches this point, then it means that a client 
                // socket has been accepted. 
 
                // For each client, we will start a service thread to 
                // service the client requests. This is to demonstrate a 
                // Multi-Threaded server. Starting a thread also lets our 
                // MultiThreadedSocketServer accept multiple connections simultaneously. 
 
                // Start a Service thread 
 
                ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
                cliThread.start(); 
 
            } 
            catch(IOException ioe) 
            { 
                System.out.println("Exception encountered on accept. Ignoring. Stack Trace :"); 
                ioe.printStackTrace(); 
            } 
 
        }
 
        try
        { 
            myServerSocket.close(); 
            System.out.println("Server Stopped"); 
        } 
        catch(Exception ioe) 
        { 
            System.out.println("Problem stopping server socket"); 
            System.exit(-1); 
        } 
 
 
 
    } 
 
    public static void main (String[] args) 
    { 
        new MultiThreadedSocketServer();        
    } 
 
 
    class ClientServiceThread extends Thread 
    { 
        Socket myClientSocket;
        boolean m_bRunThread = true; 
 
        public ClientServiceThread() 
        { 
            super(); 
        } 
 
        ClientServiceThread(Socket s) 
        { 
            myClientSocket = s; 
 
        } 
 
        public void run() 
        {            
            // Obtain the input stream and the output stream for the socket 
            // A good practice is to encapsulate them with a BufferedReader 
            // and a PrintWriter as shown below. 
            BufferedReader in = null; 
            PrintWriter out = null; 
 
            // Print out details of this connection 
            System.out.println("Accepted Client Address - " + myClientSocket.getInetAddress().getHostName()); 
 
            try
            {                                
                in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream())); 
                out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream())); 
 
                // At this point, we can read for input and reply with appropriate output. 
 
                // Run in a loop until m_bRunThread is set to false 
                while(m_bRunThread) 
                {                    
                    // read incoming stream 
                    String clientCommand = in.readLine(); 
                    System.out.println("Client Says :" + clientCommand);
 
                    if(!ServerOn) 
                    { 
                        // Special command. Quit this thread 
                        System.out.print("Server has already stopped"); 
                        out.println("Server has already stopped"); 
                        out.flush(); 
                        m_bRunThread = false;   
 
                    } 
 
                    if(clientCommand.equalsIgnoreCase("quit")) { 
                        // Special command. Quit this thread 
                        m_bRunThread = false;   
                        System.out.print("Stopping client thread for client : "); 
                    } else if(clientCommand.equalsIgnoreCase("end")) { 
                        // Special command. Quit this thread and Stop the Server
                        m_bRunThread = false;   
                        System.out.print("Stopping client thread for client : "); 
                        ServerOn = false;
                    } else {
                            // Process it 
                            out.println("Server Says : " + clientCommand); 
                            out.flush(); 
                    }
                } 
            } 
            catch(Exception e) 
            { 
                e.printStackTrace(); 
            } 
            finally
            { 
                // Clean up 
                try
                {                    
                    in.close(); 
                    out.close(); 
                    myClientSocket.close(); 
                    System.out.println("...Stopped"); 
                } 
                catch(IOException ioe) 
                { 
                    ioe.printStackTrace(); 
                } 
            } 
        } 
 
 
    } 
}