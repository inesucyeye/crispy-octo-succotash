import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class KnockKnockClient {
	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println(
					"Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = "127.0.0.1" ;
		int portNumber = 8732;  

		try (
				Socket clientSocket = new Socket(hostName, portNumber);
				PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				) {
			BufferedReader stdIn =
					new BufferedReader(new InputStreamReader(System.in));

			String fromServer;

			/**
			 * Ask user if they want to hear a knock knock joke, if they agree; then get their input          
			 */
			Scanner sc = new Scanner(System.in);
			System.out.println("Do you want to hear a knock knock joke? Type y otherwise program quits");
			String userInput = sc.nextLine();
			char test = userInput.charAt(0);

			if (test == 'y') {

				/**
				 *  Knock Knock joke using our protocol logic
				 */

				outToServer.println("100");
			 while (((fromServer = in.readLine()) !=null)){
					if (fromServer.equals("200")) { 
						outToServer.println(111);

					} 
					else if (fromServer.equals("210")) {                       
						System.out.println("Server: Knock Knock");
						outToServer.println(120); 
						System.out.println("Client: Who is there?");  

					} else if (fromServer.equals("220")) {
						System.out.println("Server: King Tut");
						outToServer.println(130);
						System.out.println("Client: King Tut who?");
					}
					else if (fromServer.equals("230")) {
						System.out.println("Server: King Tutkey Fried Chicken");
						outToServer.println(999);
					}
					else if (fromServer.equals("999")) {
						System.out.println("exit");
						System.exit(1);
					}                        
				}
			}
			else{ 
				outToServer.println(999);
				System.out.println("bye");
				System.exit(1);
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}
	}
}
