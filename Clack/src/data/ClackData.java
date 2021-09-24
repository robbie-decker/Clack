package data;

import java.util.Date;
/**
 * An abstract Class representing data sent between a client and a server
 *6
 * @author Chris Hickman
 */
abstract public class ClackData {
    private String userName;
    private int type;
    private Date date;
    public static final int CONSTANT_LISTUSERS = 0;
    public static final int CONSTANT_LOGOUT = 1;
    public static final int CONSTANT_SENDMESSAGE = 2; // <-- how we declare constants????
    public static final int CONSTANT_SENDFILE = 3;

    /**
     * This CLackData constructor initializes the user name and type with
     * user provided values, automatically initializes date
     *
     * @param userName Username representing name of client user
     * @param type Integer representing the kind of data being transferred between
     *             the client and the server
     */

    public ClackData(String userName, int type){
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }

    /**
     * This ClackData constructor initializes the object's type, automatically
     * initializing the date, and the user name to "Anon".
     *
     * @param type Integer representing the kind of data being transferred between
     *             the client and the server
     */
    public ClackData(int type) {
        this("Anon", type);
    }
    /**
     * This CLackData constructor automatically initializes a ClackData object
     * with an automatic date, the username with "Anon", and the type to be 0.
     */
    public ClackData(){
        this("Anon", CONSTANT_LOGOUT);// <-- discuss whether this is the best constant to use
    }
    /**
     * This is the accessor for the type.
     *
     * @return Value of ClackData's type.
     */
    public int getType(){
        return this.type;
    }
    /**
     * This is the accessor for the user name.
     *
     * @return ClackData's user name.
     */
    public String getUserName(){
        return this.userName;
    }
    /**
     * This is the accessor for the date.
     *
     * @return ClackData's date.
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * abstract method for returning a child class's data
     *
     * @return A string describing an object's data
     */
    abstract public String getData();

    /**
     * Checks to see if two ClackData objects are equal
     *
     * @param obj The object you are comparing with.
     * @return A boolean describing whether the objects are identical.
     */
    //just adding an equals for better scalability
    @Override
    public boolean equals(Object obj){
        if(obj instanceof ClackData) {
        ClackData cd = (ClackData)obj;
            return this.userName == cd.userName &&
                    this.type == cd.type &&
                    this.equals(cd.date);
        }
        else if (obj == null) return false;
        else return false;
    }



}
