import java.io.*;
import java.util.ArrayList;
/**
 * This module is to be used with a concurrent Echo server.
 * Its run method carries out the logic of a client session.
 * @author M. L. Liu
 */

class EchoServerThread implements Runnable {
   static final String endMessage = ".";
   MyStreamSocket myDataSocket;
String users[] = new String[10];
ArrayList<String> allMessages = new ArrayList<String>();

   EchoServerThread(MyStreamSocket myDataSocket) {
      this.myDataSocket = myDataSocket;
users[0] ="username:ivanpassword:1234";
 
   } // end constructor 
 
   public void run( ) {
      boolean done = false;
      String message;
String header;
String answer = "-----";

      try {

         while (!done) {
message =myDataSocket.receiveMessage( );
header = getHeader(message);

switch(header){
case "login/":
 if (!findUser(message.trim()) ){
System.out.println("fail/Wrong username or password");
                myDataSocket.sendMessage("fail/Wrong credentials");
                myDataSocket.close( );
 done = true;
} else {
System.out.println("correct username");
                 myDataSocket.sendMessage("loggedin/You are logged in");
}
break;

case "message/" :
String aMessage = message.substring(message.indexOf("/")+1);
allMessages.add(aMessage);
String messages = retrieveMessages();
                 myDataSocket.sendMessage("received/Message sent" );
break;

case "logout/":
System.out.println("Session closed");
myDataSocket.close();
done = true;
break;
} // end switch  





 
          } //end while !done

        }// end try
        catch (Exception ex) {
           System.out.println("Exception caught in thread: " + ex);
        } // end catch
   } //end run

private String getHeader(String message){
int indexOfSlash = message.indexOf('/') + 1;

return message.substring(0, indexOfSlash);
} // end get header 


private boolean findUser(String message){
String credentials = message.substring(6);
boolean pass = false;  
for (String user : users){
if ( credentials.equals(user))
pass = true;
 
} // end for 
 
 return pass;
} // end find user 

private String retrieveMessages(){
String result = "";

for (String aMessage : allMessages)
result += aMessage +"\n";

System.out.println(result);
return result;
} // end retrieve messages


} //end class 
