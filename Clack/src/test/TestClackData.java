package test;

/**
 * A class to test instances of ClackData instantiated in MessageClackData and FileClackData
 *
 * @author Chris Hickman and Robbie Decker
 */

import data.*;

import java.io.File;

public class TestClackData {

    public static void main(String[] args){

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
        System.out.println("cd2 = cd1? " + cd2.equals(cd1));


    };
}
