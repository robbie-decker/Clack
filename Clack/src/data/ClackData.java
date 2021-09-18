package data;



import java.util.Date;
/**
 *
 * @author Chris Hickman and Robbie Decker
 */
abstract public class ClackData {
    private String userName;
    private int type;
    private Date date;
    public static final int CONSTANT_LISTUSERS = 0;
    public static final int CONSTANT_LOGOUT = 1;
    public static final int CONSTANT_SENDMESSAGE = 2; // <-- how we declare constants????
    public static final int CONSTANT_SENDFILE = 3;


    public ClackData(String userName, int type){
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }
    public ClackData(int type) {
        this("anon", type);
    }
    public ClackData(){
        this("anon", CONSTANT_LOGOUT);// <-- discuss whether this is the best constant to use
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
    abstract String getData();


}
