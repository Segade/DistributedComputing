import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient2 {
   static final String endMessage = ".";
   public static void main(String[] args) {
         String message, echo;
boolean done = false;

      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      try {
         System.out.println("Welcome to the client.\n" );
          EchoClientHelper2 helper =             new EchoClientHelper2("localhost", "7");

System.out.println("Enter your user name: ");
         String username = br.readLine();

          System.out.println("Enter your password: ");
         String password = br.readLine();
message = "login/username:" + username + "password:" + password ;
 
echo = helper.getEcho(message);
String header = getHeader(echo);
System.out.println(getMessage(echo)) ;

if (header.equals("loggedin/")){
while(!done){
String option =displayMenu();

switch (option){
case "message/" :
System.out.println("Enter your message: ");
message = "message/" + br.readLine();
echo = helper.getEcho(message); 
System.out.println(getMessage(echo));
break;

case "logout/" :
System.out.println("You are logged out");
helper.done();
done = true;
} // end switch

} // end while 
  
}  // end if logged in
else {
System.out.println("Wrong user name or password");
helper.done();
} // end else logged in

  



      } // end try  
      catch (Exception ex) {
         ex.printStackTrace( );
      } //end catch
   } //end main


private static String getHeader(String message){
int indexOfSlash = message.indexOf('/') + 1;

return message.substring(0, indexOfSlash);
} // end get header 

private static String displayMenu() throws IOException{
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);


System.out.println("Press 1 to send a message." +
"\nPress 2 to retrieve all the messages.\nPress 3 to log out.");
 
String option = br.readLine();

switch (option){
case "1" : option = "message/";
break;

case "2" : option = "retrieve/";
break;

case "3" : option = "logout/";
} // end switch
 
return option;
} // end display menu


private static String getMessage(String message){
return message.substring(message.indexOf("/") + 1);
} // end get message 

} // end class
