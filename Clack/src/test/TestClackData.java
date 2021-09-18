package test;

import data.*;

public class TestClackData {

    public static void main(String[] args){

    //FileClackData testing
    ClackData x_x = new FileClackData();
    ClackData x_y = new FileClackData("TestTesting", "test.txt", 2);
    System.out.println( "this is x_x user name: " + x_x.getUserName());
    System.out.println( "this is x_y user name: " + x_y.getUserName());

    };
}
