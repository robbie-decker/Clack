package test;
/**
 * Testing class for instances of FileClackData and MessageClackData
 *
 * @author Chris Hickman and Robbie Decker
 */

/**
 * A class to test instances of ClackData instantiated in MessageClackData and FileClackData
 *
 * @author Chris Hickman and Robbie Decker
 */

import data.*;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class TestClackData {

    public static void main(String[] args) throws IOException {

    ClackData cd1 = new FileClackData("Chris", "this.txt", 1);

        System.out.println("cd1 String: " + cd1.toString());
        System.out.println("cd1's type: " + cd1.getType());
        System.out.println("cd1's user name: " + cd1.getUserName());
        System.out.println("cd1's Date: " + cd1.getDate());
        System.out.println("cd1's Data: " + cd1.getData());
        System.out.println("cd1's file name: " + ((FileClackData)cd1).getFileName());
        ((FileClackData)cd1).setFileName("that.txt");
        System.out.println("Changed the file name.");
        System.out.println("cd1 String: " + cd1.toString());
        System.out.println("cd1's type: " + cd1.getType());
        System.out.println("cd1's user name: " + cd1.getUserName());
        System.out.println("cd1's Date: " + cd1.getDate());
        System.out.println("cd1's Data: " + cd1.getData());
        System.out.println("cd1's file name: " + ((FileClackData)cd1).getFileName() + "\n");

    ClackData fcd1 = new FileClackData();
        System.out.println("fcd1 String: " + fcd1.toString()); // toString works
        System.out.println("fcd1's type: " + fcd1.getType()); //accessor for type works
        System.out.println("fcd1's user name: " + fcd1.getUserName());//accessor for user name works
        System.out.println("fcd1's Date: " + fcd1.getDate()); //accessor for date works
        System.out.println("fcd1's Data: " + fcd1.getData()); //accessor for data works
        System.out.println("fcd1's file name: " + ((FileClackData)fcd1).getFileName()); //accessor for file name works
        ((FileClackData)fcd1).setFileName("notanon"); //set file name works
        System.out.println("Changed the file name.");
        System.out.println("fcd1 String: " + fcd1.toString());
        System.out.println("fcd1's type: " + fcd1.getType());
        System.out.println("fcd1's user name: " + fcd1.getUserName());
        System.out.println("fcd1's Date: " + fcd1.getDate());
        System.out.println("fcd1's Data: " + fcd1.getData());
        System.out.println("fcd1's file name: " + ((FileClackData)fcd1).getFileName() + "\n");

        ClackData cd2 = new FileClackData("Chris", "e.txt", 1);
        System.out.println(cd1.hashCode());
        System.out.println(cd2.hashCode());
        System.out.println("cd2 = cd1? " + cd2.equals(cd1));
        ((FileClackData)cd2).setFileName("that.txt"); //mutator works
        System.out.println("Changed fileName from cd2 to the same as cd1.");
        System.out.println(cd2.hashCode() + " = " + cd1.hashCode());
        System.out.println("cd2 = cd1? " + cd2.equals(cd1) + "\n\n\n");

        MessageClackData mcd1 = new MessageClackData("Robbie", "This is my message", 0);
        System.out.println("mcd1 String: " + mcd1.toString());
        System.out.println("mcd1's type: " + mcd1.getType());
        System.out.println("mcd1's user name: " + mcd1.getUserName());
        System.out.println("mcd1's Date: " + mcd1.getDate());
        System.out.println("mcd1's Data: " + mcd1.getData());
        System.out.println("mcd1's hashcode: " + mcd1.hashCode() + "\n");

        MessageClackData mcd2 = new MessageClackData();
        System.out.println("mcd2 String: " + mcd2.toString());
        System.out.println("mcd2's type: " + mcd2.getType());
        System.out.println("mcd2's user name: " + mcd2.getUserName());
        System.out.println("mcd2's Date: " + mcd2.getDate());
        System.out.println("mcd2's Data: " + mcd2.getData());
        System.out.println("mcd2's hashcode: " + mcd2.hashCode() + "\n");

        // Testing equals function
        MessageClackData mcd1_again = new MessageClackData("Robbie", "This is my message", 0);
        System.out.println("mcd1 = mcd2? " + mcd1.equals(mcd2));             // Should be false
        System.out.println("mcd1 = mcd1_again? " + mcd1.equals(mcd1_again)); // Should be true

        //testing FileClackData IO and encryption
        ((FileClackData) cd1).setFileName("Clack\\src\\test\\input.txt");
        ((FileClackData)cd1).readFileContents();
        System.out.println("cd1's data: " + cd1.getData());
        ((FileClackData) cd1).writeFileContents();
        ((FileClackData) cd1).readFileContents("keyALPHA");
        System.out.println("cd1's data encrypted: " + cd1.getData());
        ((FileClackData) cd1).writeFileContents("keyALPHA");
        System.out.println("cd1's data encrypted then decrypted onto this file: " + ((FileClackData) cd1).getFileName());

        //testing the creation of FileClackData for Part2_document.txt
        ClackData ptd = new FileClackData("Chris", "Part2_document.txt", 1);
        System.out.println("ptd's Data: " + ptd.getData());
        System.out.println("ptd's file name: " + ((FileClackData)ptd).getFileName());

    };


}
