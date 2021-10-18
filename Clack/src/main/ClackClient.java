package main;
import java.io.File;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import data.*;

/**
 * A class that represents a client user.
 *
 * @author Chris Hickman
 */
public class ClackClient{
    private String userName;
    private String hostName;
    private int port;
    private Scanner inFromStd;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;


    public static final int defaultPort = 7000;
    public static final String KEY = "KwxhSHH";

    /**
     * Constructor for ClackClient that takes in a user-defined user name, host name and port number.
     * Sets closed connection to true, and the data sent/received from and to the server to null.
     *
     * @throws IllegalArgumentException
     * 
     * @param userName The user name of the object.
     * @param hostName The name of the computer representing the server.
     * @param port The port number of the server connected to.
     */
    public ClackClient(String userName, String hostName, int port ) throws IllegalArgumentException{
    if(port < 1024 ) throw new IllegalArgumentException("Port number can not be less than 1024");
    if(userName == null || userName == "") throw new IllegalArgumentException("Username can not be null or empty");
    if(hostName == null || hostName == "") throw new IllegalArgumentException("Host name can not be null or empty");
    this.userName = userName;
    this.hostName = hostName;
    this.port = port;
    this.closeConnection = true;
    this.dataToSendToServer = null;
    this.dataToReceiveFromServer = null;
}

    /**
     * Constructor that takes in a user-defined user name and host name, sets the port number to defaultPort.
     *
     * @throws IllegalArgumentException
     * 
     * @param userName The user name of the client.
     * @param hostName The name of the computer representing the server.
     */
    public ClackClient(String userName, String hostName) throws IllegalArgumentException{
    this(userName, hostName, defaultPort);
}

    /**
     * Constructor that receives a user defined user name.
     *
     * @throws IllegalArgumentException
     * 
     * @param userName The user name of the client.
     */
    public ClackClient(String userName) throws IllegalArgumentException{
    this(userName, "localhost");
}

    /**
     * Constructor for ClackClient that takes no values.
     */
    public ClackClient() {
    this("anon");
}

    /**
     * @throws IOException
     *
     */
    public void start() throws IOException{
        this.closeConnection = false;
        this.inFromStd = new Scanner(System.in);
        while(!this.closeConnection){
            readClientData();
            printData();
            this.dataToReceiveFromServer = this.dataToSendToServer;
        }
    }

    /**
     * Recieves data from the client.
     * User can input:
     * DONE - to stop the connection
     * SENDFILE <filename> - to send file contents
     * LISTUSERS - to list all users
     * @throws IOException
     */
    public void readClientData() throws IOException{
        while(this.closeConnection == false && this.inFromStd.hasNext()){
            try{
                String input = this.inFromStd.nextLine();
                if(input.equals("DONE")){
                    this.closeConnection = true;
                }
                else if(input.contains("SENDFILE")){
                    // TODO get filename use regex
                    String filename = input.replace("SENDFILE", "").replace(" ", "");
                    try{   
                        this.dataToSendToServer = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);
                        ((FileClackData)this.dataToSendToServer).readFileContents();
                    }catch(FileNotFoundException fnfe){
                        this.dataToSendToServer = null;
                        System.err.println("The file: " + filename +  " is not available: " + fnfe.getMessage());
                    }
                }
                else if(input.equals("LISTUSERS")){
                        System.out.println("Implementation coming soon");
                        //TODO WILL IMPLEMENT IN PART 3
                }
                else{
                    this.dataToSendToServer = new MessageClackData(this.userName, input, ClackData.CONSTANT_SENDMESSAGE);
                }
            }catch (IOException ioe){
                System.err.println(ioe.getMessage());
            } 
            
        }
        this.inFromStd.close();
    }

    /**
     * Sends data to the server.
     */
    public void sendData(){}

    /**
     * Receives data from the server.
     */
    public void receiveData(){}

    /**
     * Prints the received data to the standard output
     */
    public void printData(){
        if(this.dataToSendToServer != null)
            System.out.println(this.dataToSendToServer.toString());
        else   
            System.out.println("Data is null");
    }

    /**
     * Accessor for user name.
     *
     * @return The name of the client.
     */
    public String getUserName(){
    return this.userName;
}

    /**
     * Accessor for the host name.
     *
     * @return Name of the computer representing the server.
     */
    public String getHostName(){
    return this.hostName;
}

    /**
     * Accessor for the port number.
     *
     * @return POrt number on server connected to.
     */
    public int getPort(){
    return this.port;
}

    /**
     * Generates a hash code for the object.
     *
     * @return a hash code representing the object.
     */
    public int hashCode(){
    int result = 13;
    result = 31*result + this.port;
    result = 31*result + this.userName.hashCode();
    result = 31*result + this.hostName.hashCode();
    if(this.dataToReceiveFromServer != null)
        result = 31*result + this.dataToSendToServer.hashCode();
    if(this.dataToSendToServer != null)
        result = 31*result + this.dataToReceiveFromServer.hashCode();
    if(closeConnection == true) result += 1;
    return result;
}

    /**
     * Checks to see if two ClackClients are equal.
     *
     * @param toBeSet Object being compared to ClackClient.
     * @return A boolean representing whether the objects are equal to each other.
     */
    public boolean equals(Object toBeSet){

        if (toBeSet == null) return false;
        else if(toBeSet instanceof ClackClient) {
            ClackClient dummy = (ClackClient) toBeSet; // <-- needs to be tweaked
            return this.hashCode() == dummy.hashCode();
    }
        else return false;

}

    /**
     * Returns a string representing a full description of the class.
     *
     * @return A String representing a full description of the class.
     */
    public String toString() {
    return "user name: " + this.userName + ", host name: " + this.hostName + ", port: " + this.port +
            ", closed connection: " + String.valueOf(closeConnection)
            + " data to send: " + this.dataToSendToServer +
            " data to recieve: " + this.dataToReceiveFromServer;
}
}
