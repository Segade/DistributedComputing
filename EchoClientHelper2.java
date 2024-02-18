import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 * @author M. L. Liu
 */

public class EchoClientHelper2 {

   static final String endMessage = "logout/";
   private MyStreamSocket mySocket;
   private InetAddress serverHost;
   private int serverPort;

   EchoClientHelper2(String hostName,
                     String portNum) throws SocketException,
                     UnknownHostException, IOException {
                                     
  	   this.serverHost = InetAddress.getByName(hostName);
  		this.serverPort = Integer.parseInt(portNum);
      //Instantiates a stream-mode socket and wait for a connection.
   	this.mySocket = new MyStreamSocket(this.serverHost,
         this.serverPort); 
/**/  System.out.println("Connection request made");
   } // end constructor
	
   public String getEcho( String message) throws SocketException,
      IOException{     
      String echo = "";    
      mySocket.sendMessage( message);
	   // now receive the echo
      echo = mySocket.receiveMessage();
      return echo;
   } // end getEcho

   public void done( ) throws SocketException,
                              IOException{
      mySocket.sendMessage(endMessage);
      mySocket.close( );
   } // end done 


private static String getHeader(String message){
int indexOfSlash = message.indexOf('/') + 1;

return message.substring(0, indexOfSlash);
} // end get header 

private static String getMessage(String message){
return message.substring(message.indexOf("/") + 1);
} // end get message 


   public String retrieveMessages() throws SocketException, IOException{
String result = "";
String header = "-";
String message = "";

      mySocket.sendMessage("retrieve/retrieve all the messages");

while (!header.equals("lastmessage/")) {
message = mySocket.receiveMessage();
header = getHeader(message);

result += getMessage(message) + "\n";
} // end while 


return result;
} // end retrieve all messages 




} //end class
