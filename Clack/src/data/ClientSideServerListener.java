package data;

import main.ClackClient;

/**
 * A class responsible for receiving data from the server and printing it parallel to the ClackClient Class.
 *
 */
public class ClientSideServerListener implements Runnable{
    private ClackClient client;

    /**
     * Constructor that initializes ClientSideServerListener with a ClackClient data
      * @param client ClackClient object representing a user.
     */
    ClientSideServerListener(ClackClient client){
        this.client = client;
    }
    @Override
    public void run() {
        while(!this.client.getCloseConnection()){
            this.client.receiveData();
            this.client.printData();
        }

    }
}
