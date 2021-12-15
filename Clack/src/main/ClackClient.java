package main;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import data.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.xml.soap.Text;


/**
 * A class that represents a client user.
 *
 * @author Chris Hickman
 */

public class ClackClient extends Application {
    private String userName;
    private String hostName;
    private int port;
    private Scanner inFromStd;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private Socket serverConnect;




    // items for javafx
    private Button sendButton = new Button("Send");
    private TextField userInput = new TextField();
    private ScrollPane messages = new ScrollPane();
    private VBox messagesInner = new VBox();
    private TextArea displayUsers = new TextArea();
    private final Button ISDONE = new Button("Exit");
    private ImageView fileImage = new ImageView(new Image("/main/img/txt.png", 50, 50, true, true));
    private ImageView media1Image = new ImageView(new Image("/main/img/file.png", 50, 50, true, true));
    private ImageView media2Image = new ImageView(new Image("/main/img/file.png", 50, 50, true, true));


    public static final int defaultPort = 7000;
    public static final String KEY = "KwxhSHH";

    /**
     * Main method for running a ClackClient object
     *
     * @param args
     */

    /**
     * Main method for running a ClackClient object that takes a parameter, given a:
     * <username> or a
     *   <username>@<hostname>
     *     or a <username>@<hostname>:<port>
     * port must be an integer.
     *
     * @param args
     */
    public static void main(String[] args) {
                launch(args);
    }




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
    this.outToServer = null;
    this.inFromServer = null;
    this.serverConnect = null;
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
    public void start(Stage primaryStage) {
        List<String> s;
        s = getParameters().getUnnamed();
        if (!s.isEmpty()) {
            String args = s.get(0);
            try {
                String username = args;
                String hostname = "";
                String[] l = username.split("@", 2);
                if (l.length == 2) {
                    username = l[0];
                    hostname = l[1];
                    String[] m = hostname.split(":", 2);
                    if (m.length == 2) {
                        hostName = m[0];
                        userName = username;
                        port = Integer.parseInt(m[1]);
                    } else {
                        userName = username;
                        hostName = m[0];
                    }
                } else {
                    userName = username;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("illegal port number");
            }
        }

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(sendButton, userInput, messages, ISDONE, fileImage, media1Image, media2Image, displayUsers);
        AnchorPane.setBottomAnchor(userInput, 15.0);
        AnchorPane.setLeftAnchor(userInput, 50.0);
        userInput.setMinWidth(370);

        messages.setContent(messagesInner);
        messages.setMinHeight(500);
        messages.setMaxWidth(300);
        messages.setMaxHeight(500);
        messages.setMinWidth(300);
        AnchorPane.setBottomAnchor(messages, 45.0);
        AnchorPane.setRightAnchor(messages, 20.0);
        messagesInner.setPadding(new Insets(10, 10, 10, 10));


        AnchorPane.setBottomAnchor(sendButton, 14.0);
        AnchorPane.setLeftAnchor(sendButton, 420.0);
        sendButton.setMinWidth(60);


        AnchorPane.setTopAnchor(ISDONE, 25.0);
        AnchorPane.setRightAnchor(ISDONE, 20.0);

         AnchorPane.setLeftAnchor(fileImage, 50.0);
         AnchorPane.setBottomAnchor(fileImage, 75.0);
         fileImage.setPickOnBounds(true);



        AnchorPane.setLeftAnchor(media1Image, 50.0);
        AnchorPane.setBottomAnchor(media1Image, 150.0);

        AnchorPane.setLeftAnchor(media2Image, 50.0);
        AnchorPane.setBottomAnchor(media2Image, 225.0);

        AnchorPane.setLeftAnchor(displayUsers, 50.0);
        AnchorPane.setTopAnchor(displayUsers, 55.0);
        displayUsers.setMinHeight(200);
        displayUsers.setMaxHeight(200);
        displayUsers.setMinWidth(100);
        displayUsers.setMaxWidth(100);
        displayUsers.setStyle("-fx-background-color:red");

        this.closeConnection = false;
        try {
            this.serverConnect = new Socket(this.hostName, this.port);
            this.outToServer = new ObjectOutputStream(serverConnect.getOutputStream());
            this.inFromServer = new ObjectInputStream(serverConnect.getInputStream());
            this.inFromStd = new Scanner(System.in);
            ClientSideServerListener listener = new ClientSideServerListener(this);
            Thread current = new Thread(listener);
            current.start();



            ISDONE.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    exit();
                    try {
                        serverConnect.close();
                    } catch (IOException ioe) {
                        System.err.println("Issue with IO");
                    }
                }
            });
            fileImage.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    TextInputDialog a = new TextInputDialog();
                    a.setContentText("Enter File Name");
                    a.showAndWait();
                    a.getEditor().getText();
                    sendFile(a.getEditor().getText());
                }
            });


//
        } catch (IOException ioe){ System.err.println("Issue with IO.");
        }
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!userInput.getText().isEmpty()) {
                        readClientData();
                        sendData();
                    }
                }catch(IOException ioe) {
                    System.err.println("issue with IO");
                     }
            }
        });

        root.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER){
                sendButton.fire();
                event.consume();
            }

        });

        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add( getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    /**
     * Starts a connection with a server at the port number defaultPort.
     * Opens a connection to a server, opens ObjectInputStreams and ObjectOutputStreams and a scanner, and reads in and out through
     * these objects.
     *
     * @throws IOException if there is an issue with the IO.
     *
     */
    public void start() throws IOException{
        this.closeConnection = false;
        try {
            Socket serverConnect = new Socket(this.hostName, this.port);
            this.outToServer = new ObjectOutputStream(serverConnect.getOutputStream());
            this.inFromServer = new ObjectInputStream(serverConnect.getInputStream());
            this.inFromStd = new Scanner(System.in);
            ClientSideServerListener listener = new ClientSideServerListener(this);
            Thread current = new Thread(listener);
            current.start();

            serverConnect.close();
            this.inFromStd.close();
            this.outToServer.close();
            this.inFromServer.close();

         } catch (IOException ioe){ System.err.println("Issue with IO.");
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
    public void readClientData() throws IOException {

        try{
           // String input = this.inFromStd.nextLine();
            String input = userInput.getText();
            if(input.equals("DONE")){
                this.dataToSendToServer = new MessageClackData(this.userName, input, ClackData.CONSTANT_LOGOUT);
                this.closeConnection = true;
                Platform.exit();
            }
          else
                if(input.contains("SENDFILE")){
                String filename = input.replace("SENDFILE", "").replace(" ", "");
                try{   
                    this.dataToSendToServer = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);
                    ((FileClackData)this.dataToSendToServer).readFileContents();

                }catch(FileNotFoundException fnfe){
                    this.dataToSendToServer = null;
                    System.err.println("The file: " + filename +  " is not available: " + fnfe.getMessage());
                }
            }
            else{
                this.dataToSendToServer = new MessageClackData(this.userName, input, ClackData.CONSTANT_SENDMESSAGE);
                userInput.setText("");
            }
        }catch (IOException ioe){
            System.err.println(ioe.getMessage());
        } 

    }

    public void sendFile(String filename){
            filename = filename.replace("SENDFILE", "").replace(" ", "");
            try{
                this.dataToSendToServer = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);
                ((FileClackData)this.dataToSendToServer).readFileContents();
                sendData();
            }catch(FileNotFoundException fnfe){
                this.dataToSendToServer = null;
                System.err.println("The file: " + filename +  " is not available: " + fnfe.getMessage());
            }catch(IOException ioe){System.err.println(ioe.getMessage());};
    }


    /**
     * Sends data to the server.
     */
    public void sendData(){
        try {
            outToServer.writeObject(this.dataToSendToServer);
        } catch (IOException ioe){System.err.println("Error writing data to server.");}
    }

    /**
     * Receives data from the server.
     */
    public void receiveData(){
       try {
        this.dataToReceiveFromServer =  (ClackData)inFromServer.readObject();
        } catch (IOException ioe){System.err.println("Error writing data to server.");
        } catch (ClassNotFoundException cnfe){System.err.println("Class not found");}
    }
    /**
     * Prints the received data to the standard output
     */
    public void printData(){
        if(this.dataToReceiveFromServer != null) {
            if(this.dataToReceiveFromServer.getType() == ClackData.CONSTANT_LISTUSERS)
               updateUsers(dataToReceiveFromServer.getData());
            if(this.dataToReceiveFromServer.getType() == ClackData.CONSTANT_SENDMESSAGE)
                addMessage(dataToReceiveFromServer.getUserName(), this.dataToReceiveFromServer.getData());
            if(this.dataToReceiveFromServer.getType() == ClackData.CONSTANT_SENDFILE)
                addFileContents(dataToReceiveFromServer.getUserName(), this.dataToReceiveFromServer.getData());
            if(this.dataToReceiveFromServer.getType() == ClackData.CONSTANT_LOGOUT){};
        }else
            System.out.println("Data from server is null");
    }

    /**
     * Adds a message to a container in Application
     * @param userName user sending the message
     * @param message contents of the message
     */
    private void addMessage(String userName, String message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label l = new Label(userName + ": " + message);
                l.setMaxWidth(270);
                l.setTextFill(Color.web("#a60222"));
                l.setWrapText(true);
                messagesInner.getChildren().add(l);
            }
        });
    }

    /**
     * adds the contents of a text file to a container in Application
     * @param userName user sending the file
     * @param file file contents
     */
    private void addFileContents(String userName, String file){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label fileContents = new Label(userName + ": " + file);
                fileContents.setStyle(" -fx-border-color:#a60222; -fx-border-width:1px;");
                fileContents.setPrefWidth(270);
                fileContents.setWrapText(true);
                fileContents.setTextFill(Color.web("#a60222"));
                fileContents.setPadding(new Insets(0, 5, 0, 5));
                messagesInner.getChildren().add(fileContents);
            }
        });
    }
    public void updateUsers(String s ){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                displayUsers.setText(s);
            }
        });
    }

    public void listOfUsers(){
        dataToSendToServer = new MessageClackData(userName, "LISTUSERS", ClackData.CONSTANT_LISTUSERS);
        sendData();
    }

    /**
     * function that exits a Client
     */

    private void exit(){
                this.dataToSendToServer = new MessageClackData(this.userName, (String)"DONE", ClackData.CONSTANT_LOGOUT);
                this.sendData();
                this.closeConnection = true;

        try {
                     this.inFromStd.close();
                     this.outToServer.close();
                     this.inFromServer.close();
                 }catch(IOException ioe){System.err.println("Issue with IO");}
                 Platform.exit();

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
     * @return Port number on server connected to.
     */
    public int getPort(){
    return this.port;
}

    /**
     * Accessor for a variable determining if the connection is closed.
     *
     * @return boolean representing a closed connection
     */
    public boolean getCloseConnection(){
        return this.closeConnection;
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
