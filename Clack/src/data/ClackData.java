package data;

import java.util.Date;
/**
 *
 * @author Chris Hickman and Robbie Decker
 */
public class ClackData {
    private String userName;
    private int type;
    private Date date;                           // <-- do we have to implement class Date?
    private static int CONSTANT_LISTUSERS = 0;
    private static int CONSTANT_LOGOUT = 1;
    private static int CONSTANT_SENDMESSAGE = 2; // <-- how we declare constants????
    private static int CONSTANT_SENDFILE = 3;


    public ClackData(String userName, int type){
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }
    public ClackData(int type) {
        this("anon", type);
    }
    public ClackData(){
        this("anon", CONSTANT_LISTUSERS);// <-- discuss whether this is the best constant to use
    }
    public int getType(){
        return this.type;
    }
    public String getUserName(){
        return this.userName;
    }
    public Date getDate(){
        return this.date;
    }
    public int getData(){
        return 0; // <-- not sure what to do here
    }

}
