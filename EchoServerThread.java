import java.io.*;
/**
 * This module is to be used with a concurrent Echo server.
 * Its run method carries out the logic of a client session.
 * @author M. L. Liu
 */

class EchoServerThread implements Runnable {
   static final String endMessage = ".";
   MyStreamSocket myDataSocket;
String users[] = new String[10];

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
case "login" : 
 if (!findUser(message.trim()) ){
 
                myDataSocket.sendMessage("fail/Wrong credentials");
System.out.println("Wrong access attempt");
                myDataSocket.close( );
 done = true;

 }else {
 
                myDataSocket.sendMessage("logedin/You are logged in"); 
System.out.println("Correct access");
} // end else
break;

case "logout" : 
                myDataSocket.sendMessage("Bye!!");
                myDataSocket.close( );
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
int indexOfSlash = message.indexOf('/');

return message.substring(0, indexOfSlash);
} // end get header 


private boolean findUser(String message){
String credentials = message.substring(6);
  
for (String user : users){
if ( credentials.equals(user))
return true;
 
} // end for 
 
 return false;
} // end find user 


} //end class 
