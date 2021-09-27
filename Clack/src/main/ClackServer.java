package main;
import data.*;
/**
 * Class for hosting clack service
 * 
 * @author Robbie Decker
 */

public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToRecieveFromClient;
    private ClackData dataToSendToClient;
    public static final int defaultPort = 7000;

    /** 
     * Constructor that receives a port number for the server to use
     * 
     * @param port Port number for the server to use
    */

    public ClackServer(int port){
        this.port = port;
        this.closeConnection = false;
        this.dataToRecieveFromClient = null;
        this.dataToSendToClient = null;
    }

    /**
     * Constructor for ClackServer that takes no values
     */
    public ClackServer(){
        this.port = defaultPort;
        this.closeConnection = false;
        this.dataToRecieveFromClient = null;
        this.dataToSendToClient = null;
    }
    /**
     * Start server session
     */
    public void start(){

    }
    /**
     * Receives data from the client
     */
    public void receiveData(){

    }
    /**
     * Sends data to client
     */
    public void sendData(){

    }
    /**
     * Accessor for the port number
     * 
     * @return Port number on server
     */
    public int getPort(){
        return this.port;
    }
    /**
     * Generates a hashCode for the object
     * 
     * @return a hash code representing the object
     */
    public int hashCode(){
        int result = 13;
        result = 31*result + port;
        return result;
    }

    /**
     * Checks to see if two ClackSevers are equal
     */
    public boolean equals(Object toBeSet){
        System.out.println(toBeSet);
        if (toBeSet == null) return false;
        else if(toBeSet instanceof ClackServer) {
            ClackServer dummy = (ClackServer) toBeSet;
            return this.hashCode() == dummy.hashCode();
       }
        else return false;
    }
    /**
     * Returns a string representing a full description of the class
     *
     * @returns a string representing a full description of the class
     */
    public String toString(){
        return "port: " + this.port + ", close connection: " + String.valueOf(this.closeConnection);
    }
}
