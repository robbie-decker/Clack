package test;

import main.ClackClient;
/**
 * A class to test ClackClient.
 *
 * @author Chris Hickman
 */

public class TestClackClient {

    public static void main(String[] args) {

        ClackClient cc1 = new ClackClient("Chris", "my computer", 800);
        System.out.println("cc1 String: " + cc1.toString());
        System.out.println("cc1's user name: " + cc1.getUserName());
        System.out.println("cc1's host name: " + cc1.getHostName());
        System.out.println("cc1's Port: " + cc1.getPort());
        ClackClient cc2= new ClackClient("Chris", "my computer", 800);
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
    }
}