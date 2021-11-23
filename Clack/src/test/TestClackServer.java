package test;

import main.ClackServer;

/**
 * A Class for testing ClackServer
 *
 * @author: Robbie Decker
 */
public class TestClackServer {
    public static void main(String ars[]){
    /**
     * Test ClackServer object and its functions
     */
        ClackServer test1 = new ClackServer(300);

        // test1.start();          //This call does nothing since there is only a declaration

       // test1.receiveData();    //This call does nothing since there is only a declaration
        // test1.sendData();       //This call does nothing since there is only a declaration
        System.out.println("test1 port: " + test1.getPort()); 
        System.out.println("test1 hash: " + test1.hashCode());
        System.out.println("test1 string: " + test1.toString());

        ClackServer test2 = new ClackServer();
        System.out.println("test2 port: " + test2.getPort()); 
        System.out.println("test2 hash: " + test2.hashCode());
        System.out.println("test2 string: " + test2.toString());
        
        ClackServer test3 = new ClackServer(-40);
        System.out.println("test3 port: " + test3.getPort()); 
        System.out.println("test3 hash: " + test3.hashCode());
        System.out.println("test3 string: " + test3.toString());

        ClackServer test1_again = new ClackServer(300); // used to check comparison
        System.out.println("test1_again string: " + test1_again.toString());
        System.out.println("test1_again string: " + test1_again.hashCode());
        System.out.println("test1 = test2? " + test1.equals(test2)); //Should be false
        
        // Returns true since the hashes for both instances are equal
        System.out.println("test1 = test1_again? " + test1.equals(test1_again));
    }
}
