import java.net.*;
import java.io.*;

public class KnockKnockServer {															
	public static void main(String[] args) throws NumberFormatException, IOException {  // NumberFormatException used for converting 
		System.out.println("The Knock Knock Joke Server is running.");					// bufferedreader string to an integer
		ServerSocket serverSocket = null;
		Socket clientSocket = null;								
		
		try { 																	// Attempts to create a socket at port 8675
			serverSocket = new ServerSocket(8675);
			System.out.println("Waiting for client on port 8675...");
		} catch (IOException e) {
			System.err.println("Server cannot listen on port 8675.");
			System.exit(1);
		}  
		
		try {                                                                 	// Attempts to accept the connection with the client
			clientSocket = serverSocket.accept(); 
			System.out.println("Just connected to" + clientSocket.getRemoteSocketAddress());
		} catch (IOException e) {
			System.err.println("Failed to accept connection with client.");
			System.exit(1);
		} 
		
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
		int inputLine, outputLine;
		KnockKnockProtocol kkp = new KnockKnockProtocol();
		
		try {
		
			while ((inputLine = Integer.valueOf(in.readLine())) != 999) {
				outputLine = kkp.processInput(inputLine);
				if (inputLine == 100)
					System.out.println("Do you want to hear a Knock Knock Joke?");

				else if (inputLine == 110 || inputLine == 777 || inputLine == 999) {
					System.out.println("Client has disconnected.");
					out.close();
					in.close();
					clientSocket.close();
					break;
				}
				else if (inputLine == 111) 
					System.out.println("KNOCK KNOCK");

				else if (inputLine == 120) 
					System.out.println("KING TUT");

				else if (inputLine == 130) 
					System.out.println("KINGTUTKEY FRIED CHICKEN.  LOL.  GOODBYE.");

				out.println(outputLine);
			}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
		} catch (IOException e) {
			System.err.println("Yo.");
		}
	} // end main method
} // end KnockKnockServer class
