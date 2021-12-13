package main;
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;

import data.*;
/**
 * Class for hosting clack service
 * 
 * @author Robbie Decker
 */

public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ArrayList<ServerSideClientIO> serverSideClientIOList;
    public static final int defaultPort = 7000;

    /**
     * Main method for running a ClackServer object with correct arguments
     * 
     * @param args
     */
    public static void main(String[] args){
        if(args.length == 0)
            main();
        else if(args.length == 1)
            main(Integer.parseInt(args[0]));
        else 
            System.out.println("Invalid input of arguments");

    }
    /**
     * Overloaded main method for running a ClackServer object on the default port (7000)
     */
    public static void main(){
        try{
            ClackServer clackServer = new ClackServer();
            clackServer.start();

        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
    /**
     * Overloaded main method for running a ClackServer object with a given portnumber
     * 
     * @param portnum Port number the user gives when running the program
     */
    public static void main(int portnum){
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
        this.serverSideClientIOList = new ArrayList<ServerSideClientIO>();
    }

    /**
     * Constructor for ClackServer that takes no values
     */
    public ClackServer(){
        this.port = defaultPort;
        this.closeConnection = false;
        this.serverSideClientIOList = new ArrayList<ServerSideClientIO>();
    }
    /**
     * Start server session
     * Read data from client and echo message sent back to client
     * Closes connection when DONE is read
     */
    public void start() throws IOException{
        this.closeConnection = false;
        try{
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Server now running on port: " + this.port);

            while(!this.closeConnection){
                Socket clientSocket = server.accept();
                System.out.println("New Connection from: " + clientSocket.getInetAddress());
                ServerSideClientIO clientIO = new ServerSideClientIO(this, clientSocket);
                serverSideClientIOList.add(clientIO);
                Thread clientThread = new Thread(clientIO);
                clientThread.start();
            }
            server.close();
            for(int i = 0; i < serverSideClientIOList.size(); i++){
                serverSideClientIOList.get(i).clientSocket.close();
            }
        }
        catch(UnknownHostException uhe){System.err.println(uhe.getMessage());}
        catch(NoRouteToHostException nrhe){System.err.println(nrhe.getMessage());}
        catch(ConnectException ce){System.err.println(ce.getMessage());}
        catch(IOException ioe){System.err.println(ioe.getMessage());}
    }
    
    /**
     * Broadcast a message to all clients
     * 
     * @param dataToBroadcastToClients the message to send to all clients
     */
    public synchronized void broadcast(ClackData dataToBroadcastToClients){
        for(int i = 0; i < serverSideClientIOList.size(); i++){
            serverSideClientIOList.get(i).setDataToSendToClient(dataToBroadcastToClients);
            serverSideClientIOList.get(i).sendData();
        }
    }

    /**
     * Remove a specific object from the Array List serverSideClientIOList
     * 
     * @param clientIO The object you wish to remove from serverSideClientIOList
     */
    public synchronized void remove(ServerSideClientIO clientIO){
        serverSideClientIOList.remove(clientIO);
    }
    /**
     * List all the current users connected to the server
     * 
     * @param clientIO The object asking for the LISTUSERS info
     */
  //  public String listUsers(){

    public void listUsers(ServerSideClientIO clientIO){
        String users = "";
        for(int i = 0; i < serverSideClientIOList.size(); i++){
            if(serverSideClientIOList.get(i).getDataFromClient() == null){
                users = users + ", Unknown";
            }
            else{
                users = serverSideClientIOList.get(i).getDataFromClient().getUserName() + "\n" + users;
            }
        }
        clientIO.setDataToSendToClient(new MessageClackData("anon", users, 0));
        clientIO.sendData();
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
