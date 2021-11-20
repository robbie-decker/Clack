package data;

import main.ClackServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *A class that handles sending and receiving data, as long as the connection is open.
 *
 *
 * @author Chris Hickman
 */

public class ServerSideClientIO implements Runnable{
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    /**
     * Constructor for ServerSideClient, takes in the following parameters:
     *
     * @param server a ClackServer object
     * @param clientSocket a Socket representing a client's connection
     */

    public ServerSideClientIO(ClackServer server, Socket clientSocket){
        this.server = server;
        this.clientSocket = clientSocket;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    /**
     *
     */

    @Override
    public void run() {
        try{
            inFromClient =  new ObjectInputStream(this.clientSocket.getInputStream());
            outToClient =  new ObjectOutputStream(this.clientSocket.getOutputStream());
            while(!this.closeConnection){
                this.server.receiveData();
                //this.server.broadcast();
            }

        }catch(UnknownHostException uhe){System.err.println(uhe.getMessage());}
         catch(NoRouteToHostException nrhe){System.err.println(nrhe.getMessage());}
           catch(ConnectException ce){System.err.println(ce.getMessage());}
           catch(IOException ioe){System.err.println(ioe.getMessage());}

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
     * @param dataToSendToClient a ClackData object representing the data to send to the client.
     */
    public void setDataToSendToClient(ClackData dataToSendToClient){
        this.dataToSendToClient = dataToSendToClient;
    }

    /**
     * Receives data from the client
     */
    public void receiveData(){
        try{
            this.dataToReceiveFromClient = (ClackData)inFromClient.readObject();
            System.out.println("Data from client: " + dataToReceiveFromClient.getData());
            if(dataToReceiveFromClient.getData().equals("DONE")) {
               // this.server.remove();
                this.closeConnection = true;
            }
        }catch (IOException ioe){System.err.println("Error reading data from client");
        }catch (ClassNotFoundException cnfe){System.err.println("Class not found");}
    }
}
