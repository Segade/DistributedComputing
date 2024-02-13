import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient2 {
   static final String endMessage = ".";
   public static void main(String[] args) {
         String message, echo;

      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      try {
         System.out.println("Welcome to the client.\n" );
          EchoClientHelper2 helper =             new EchoClientHelper2("localhost", "1981");

System.out.println("Enter your user name: ");
         String username = br.readLine();

          System.out.println("Enter your password: ");
         String password = br.readLine();
message = "login/username:" + username + "password:" + password ;
echo = helper.getEcho(message);
System.out.println(echo);
         boolean done = true;
         while (!done) {
            System.out.println("Enter a line to receive an echo "
               + "from the server, or a single period to quit.");
            message = br.readLine( );
            if ((message.trim()).equals (endMessage)){
               done = true;
               helper.done( );
            }
            else {
               echo = helper.getEcho( message);
               System.out.println(echo);
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace( );
      } //end catch
   } //end main
} // end class
