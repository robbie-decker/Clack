package main;
import data.*;

/**
 * A class that represents a client user.
 *
 * @author Chris Hickman
 */
public class ClackClient {
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    public static final int defaultPort = 7000;

    /**
     * Constructor for ClackClient that takes in a user-defined user name, host name and port number.
     * Sets closed connection to true, and the data sent/received from and to the server to null.
     *
     * @param userName The user name of the object.
     * @param hostName The name of the computer representing the server.
     * @param port The port number of the server connected to.
     */
    public ClackClient(String userName, String hostName, int port ){
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
     * @param userName The user name of the client.
     * @param hostName The name of the computer representing the server.
     */
    public ClackClient(String userName, String hostName) {
    this(userName, hostName, defaultPort);
}

    /**
     * Constructor that receives a user defined user name.
     *
     * @param userName The user name of the client.
     */
    public ClackClient(String userName) {
    this(userName, "localhost");
}

    /**
     * Constructor for ClackClient that takes no values.
     */
    public ClackClient() {
    this("anon");
}

    /**
     *
     */
    public void start(){}

    /**
     * Recieves data from the client.
     */
    public void readClientData(){}

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
    public void printData(){}

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
    result = 31*result + port;
    result = 31*result + userName.hashCode();
    result = 31*result + hostName.hashCode();
    result = 31*result + dataToSendToServer.hashCode();
    result = 31*result + dataToReceiveFromServer.hashCode();
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
