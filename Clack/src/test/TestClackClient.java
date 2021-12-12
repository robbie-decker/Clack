package test;

import java.io.IOException;

import javafx.stage.Stage;
import main.ClackClient;
/**
 * A class to test ClackClient.
 *
 * @author Chris Hickman
 */

public class TestClackClient {

    public static void main(String[] args) throws IOException {

        ClackClient cc1 = new ClackClient("Chris", "my computer", 10000);
        System.out.println("cc1 String: " + cc1.toString());
        System.out.println("cc1's user name: " + cc1.getUserName());
        System.out.println("cc1's host name: " + cc1.getHostName());
        System.out.println("cc1's Port: " + cc1.getPort());
        ClackClient cc2= new ClackClient("Chris", "my computer", 10000);
        System.out.println(cc1.hashCode() + " = " + cc2.hashCode());
        System.out.println("cc2 String: " + cc2.toString());
        System.out.println("cc1 = cc2?: " + cc1.equals(cc2));
        System.out.println(cc1.hashCode() + " = " + cc2.hashCode());
        ClackClient cc3 = new ClackClient();
        System.out.println("cc3 String: " + cc3.toString());
        System.out.println("cc3's user name: " + cc3.getUserName());
        System.out.println("cc3's host name: " + cc3.getHostName());
        System.out.println("cc3's Port: " + cc3.getPort());
        System.out.println("cc2 = cc3?: " + cc2.equals(cc3));
        System.out.println(cc2.hashCode() + " = " + cc3.hashCode());
        ClackClient cc4 = new ClackClient("Jimbo");
        System.out.println("cc4 String: " + cc4.toString());
        System.out.println("cc4's user name: " + cc4.getUserName());
        System.out.println("cc4's host name: " + cc4.getHostName());
        System.out.println("cc4's Port: " + cc4.getPort());
        System.out.println("cc2 = cc4?: " + cc2.equals(cc4));
        System.out.println(cc2.hashCode() + " = " + cc4.hashCode());
        ClackClient cc5 = new ClackClient("Jimbo", "Office");
        System.out.println("cc5 String: " + cc5.toString());
        System.out.println("cc5's user name: " + cc5.getUserName());
        System.out.println("cc5's host name: " + cc5.getHostName());
        System.out.println("cc5's Port: " + cc5.getPort());
        System.out.println("cc2 = cc5?: " + cc2.equals(cc5));
        System.out.println(cc2.hashCode() + " = " + cc5.hashCode());

        System.out.println("\n\n\n");
        // cc1.start(new Stage()); // Type in SENDFILE, DONE LISTUSERS, and other to test readClientData logic
        // LISTUSERS
        // Implementation coming soon
        // SENDFILE Clack/src/test/Part2_document.txt
        // DONE
        // file name: Clack/src/test/Part2_document.txt, file contents: A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information..., user name: Chris, type: 3, date: Thu Oct 14 05:52:51 EDT 2021
        // If you run these command you will see that this creatse a FileClackObject with the content
        // from Part2_document.txt
        
        // ~new session
        //WRONG INPUT 
        // DONE
        // message: WRONG INPUT , user name: Chris, type:2, date: Thu Oct 14 05:54:14 EDT 2021
        // will create MessageClackData object with the input being the typed in input 

        System.out.println("cc1 String: " + cc1.toString());

        // ClackClient wrong = new ClackClient("Jimbo", "Office", 500);
        // will throw exception since port is less than 800 

        // ClackClient wrong = new ClackClient("", "Office");
        // will throw exception since username is empty

        // ClackClient wrong = new ClackClient("Jimbo", "");
        // will throw exception since host name is empty
        

    }
}