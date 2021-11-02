package main;
import java.io.*;
import java.net.*;

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
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    public static final int defaultPort = 7000;

    public static void main(String[] args){
        try{
            ClackServer clackServer = new ClackServer();
            clackServer.start();

        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    public static void main(String[] args, int portnum){
        try {
            ClackServer clackServer = new ClackServer(portnum);
            clackServer.start();
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }catch(NumberFormatException nfe){System.err.println("illegal port number");
        }
    }
    /** 
     * Constructor that receives a port number for the server to use
     * 
     * @param port Port number for the server to use
    */
    public ClackServer(int port) throws IllegalArgumentException{
        this.port = port;
        this.closeConnection = false;
        this.dataToRecieveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
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
    public void start() throws IOException{
        this.closeConnection = false;
        try{
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Server now running on port: " + this.port);
            Socket clientSocket = server.accept();
            System.out.println("New Connection from: " + clientSocket.getInetAddress());

            this.outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            this.inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            while(!this.closeConnection){
                this.receiveData();
                this.dataToSendToClient = this.dataToRecieveFromClient;
                this.sendData();
            }
            server.close();
            clientSocket.close();
            this.inFromClient.close();
            this.outToClient.close();
        }
        catch(UnknownHostException uhe){System.err.println(uhe.getMessage());}
        catch(NoRouteToHostException nrhe){System.err.println(nrhe.getMessage());}
        catch(ConnectException ce){System.err.println(ce.getMessage());}
        catch(IOException ioe){System.err.println(ioe.getMessage());}
    }
    /**
     * Receives data from the client
     */
    public void receiveData(){
        try{
            this.dataToRecieveFromClient = (ClackData)inFromClient.readObject();
            System.out.println("Data from client: " + dataToRecieveFromClient.getData());
            if(dataToRecieveFromClient.getData().equals("DONE"))
                this.closeConnection = true;
        }catch (IOException ioe){System.err.println("Error reading data from client");
        }catch (ClassNotFoundException cnfe){System.err.println("Class not found");}
     }
    /**
     * Sends data to client
     */
    public void sendData(){
        try{
            outToClient.writeObject(this.dataToSendToClient);
        }catch(IOException ioe){System.err.println(ioe.getMessage());}
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
