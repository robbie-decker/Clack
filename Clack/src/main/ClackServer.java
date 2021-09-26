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


    public ClackServer(int port){
        this.port = port;
        this.closeConnection = false;
        this.dataToRecieveFromClient = null;
        this.dataToSendToClient = null;
    }

    public ClackServer(){
        this.port = defaultPort;
        this.closeConnection = false;
        this.dataToRecieveFromClient = null;
        this.dataToSendToClient = null;
    }

    public void start(){
    // Just a declaration

    }

    public void receiveData(){

    }

    public void sendData(){

    }
    
    public int getPort(){
        return this.port;
    }

    public void getData(){

    }

    public int hashCode(){
        return 0;
    }

    public boolean equals(Object other){
        return false;
    }

    public String toString(){
        return "hello";
    }
}
