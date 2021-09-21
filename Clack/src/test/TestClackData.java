package test;

import data.*;

public class TestClackData {

    public static void main(String[] args){

    //FileClackData testing
    ClackData x_x = new FileClackData();
    FileClackData x_y = new FileClackData("UserName", "file.txt", 2);
    System.out.println( "this is x_x user name: " + x_x.getUserName());
    System.out.println( "this is x_y user name: " + x_y.getUserName());
    System.out.println( "this is x_x type: " + x_x.getType());
    System.out.println( "this is x_y type: " + x_y.getType());
    System.out.println( "this is x_x date: " + x_x.getDate());
    System.out.println( "this is x_y date: " + x_y.getDate());
    System.out.println( "this is x_x data: " + x_x.getData());
    System.out.println( "this is x_y data: " + x_y.getData());
    System.out.println(x_y == x_x);


        FileClackData x_a = new FileClackData("UserName", "file.txt", 2);
        FileClackData x_b = new FileClackData("UserName", "file.txt", 2);
    System.out.println(x_a.equals(x_b));

        System.out.println(x_y);
        System.out.println(x_x.toString());




    };
}
