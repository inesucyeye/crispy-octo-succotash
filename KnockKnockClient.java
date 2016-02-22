
package knockknockclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class KnockKnockClient {

    String textFromServer = "";

    public static void main(String[] args) throws IOException {

        String hostName = "127.0.0.1";//args[0];
        int portNumber = 8675;//Integer.parseInt(args[1]);

        try (//opens sockets
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter sendToServer
                = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader inFromServer
                = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn
                = new BufferedReader(
                        new InputStreamReader(System.in))) {

// get user input, if they want a knock knock joke           
          Scanner sc = new Scanner(System.in);
          System.out.println("Do you want to hear a knock knock joke? Type y otherwise program quits");
          String userInput = sc.nextLine();
          char test = userInput.charAt(0);

         if (test == 'y') {

// Start of the actual Knock Knock logic
 String protocolCodefromServer;
 sendToServer.println("100");//send hello to server
 //System.out.println("Sent   100");
    while (((protocolCodefromServer = inFromServer.readLine()) !=null)){
    //while ((userInput = stdIn.readLine()) != null) {// This while accepts scanner input to simulate the server sending a line
                   // System.out.println("Recieved "+protocolCodefromServer);
                    
                    if (protocolCodefromServer.equals("200")) { //if server said hello back
                         //   System.out.println("got 200 back from if stmt");
                            sendToServer.println(111);// say to server: i want a joke
                           // System.out.print("Sent    111 ");
                            
                        } else if (protocolCodefromServer.equals("210")) { // server says: Knock Knock                      
                            System.out.println("Server: Knock Knock....");
                            sendToServer.println(120); //say too server: Who's there?
                            //System.out.print("Sent    120 ");
                            System.out.println("Client: Who's there?");  
                              
                        } else if (protocolCodefromServer.equals("220")) {// may get part one of joker here: if added then respond with protocolCodefromServer + " who?" attached
                            System.out.println("Server: King Tut");
                            sendToServer.println(130);
                            //System.out.print("Sent    130 ");
                            System.out.println("Client: King Tut who?");
                        }
                         else if (protocolCodefromServer.equals("230")) {// may get the punchline here
                             System.out.println("Server: King Tut....key Fried Chicken");
                             sendToServer.println(999);
                        }
                         else if (protocolCodefromServer.equals("999")) {
                             System.out.println("got 999 back from if stmt exit");
                             System.exit(1);
                        }                        
                }//end while stream line not null
         }
         else{ 
          sendToServer.println(999);
          System.out.println("Goodbye");
          System.exit(1);
         }
          
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }
}
