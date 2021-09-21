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
return 0;    //     <-- needs to be implemented
}


public boolean equals(Object toBeSet){

if (toBeSet == null) return false;

    else if(toBeSet instanceof ClackClient) {

        ClackClient dummy = (ClackClient) toBeSet;
        return true;

    }
    else return false;
    /*




     */


    //     <-- needs to be implemented
}
public String toString(){
return "not implemented yet";    //     <-- needs to be implemented
}


}
