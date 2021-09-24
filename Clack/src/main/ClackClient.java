package main;
import data.*;

public class ClackClient {
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    public static final int defaultPort = 7000;


public ClackClient(String userName, String hostName, int port ){
    this.userName = userName;
    this.hostName = hostName;
    this.port = port;
    this.closeConnection = true;
    this.dataToSendToServer = null;
    this.dataToReceiveFromServer = null;
}
public ClackClient(String userName, String hostName) {
    this(userName, hostName, defaultPort);
}
public ClackClient(String userName) {
    this(userName, "localhost");
}
public ClackClient() {
    this("anon");
}
public void start(){}
public void readClientData(){}
public void sendData(){}
public void receiveData(){}
public void printData(){}
public String getUserName(){
    return this.userName;
}
public String getHostName(){
    return this.hostName;
}
public int getPort(){
    return this.port;
}
public int hashCode(){
    int result = 13;
    result = 31*result + port;
    result = 31*result + userName.hashCode();
    result = 31*result + hostName.hashCode();
    return result;

}


public boolean equals(Object toBeSet){

if (toBeSet == null) return false;

    else if(toBeSet instanceof ClackClient) {

        ClackClient dummy = (ClackClient) toBeSet; // <-- needs to be tweaked
        return this.hashCode() == dummy.hashCode();

    }
    else return false;

}
public String toString() {
    return "user name: " + this.userName + ", host name: " + this.hostName + ", port: " + this.port +
            ", closed connection: " + String.valueOf(closeConnection);
}
}
