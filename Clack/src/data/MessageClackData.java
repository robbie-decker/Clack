package data;

/**
 * A Class, inheriting from ClackData, for messages being sent
 *
 * @author: Robbie Decker
 */

public class MessageClackData extends ClackData {
    private String message;

    /**
     * A constructor for MessageClackData that initializes the username,
     * message being sent, and type with given values
     * @param userName String representing the users name
     * @param message String representing the message
     * @param type Integer representing the type of message
     */
    public MessageClackData(String userName, String message, int type){
        super(userName, type);
        this.message = message;
        // need to figure out type
    }
    /**
     * Constructor for MessageClackData that encrypts the message using the key param
     * @param userName String representing the users name
     * @param message String representing the message
     * @param key String used to encrypt message
     * @param type Integer representing the type of message
     */
    public MessageClackData(String userName, String message, String key, int type){
        //ecrypt message using key
    }
    /**
     * A default constructor for MessageClackData when no inputs
     * are given. Sets username and message to "anon"
     */
    public MessageClackData(){
        this("anon", "anon", 0);
    }
    /**
     * Accessor for returning an instant message
     * 
     * @return String representing an instant message
     */
    public String getData(){
        return this.message;
    }

    /**
     * Generates a hashcode for a MessageClackData object
     * 
     * @return Int representing the object's hashcode
     */
    public int hashCode(){
        int dummy = 13;
        dummy = 17*dummy + this.message.hashCode();
        dummy = 17*dummy + this.getUserName().hashCode();
        dummy = 17*dummy + this.getType();
        dummy = 17*dummy + this.getData().hashCode();
        return dummy;    }

    /**
     * Checks to see if two MessageClackData objects are identical to each other
     * 
     * @param other MessageClackData object being compared to original
     * 
     * @return boolean representing whether the two MessageClackData object are equal
     */
    public boolean equals(Object other){
        if(other instanceof MessageClackData) {
            MessageClackData dummy = (MessageClackData) other;
             return this.hashCode() == dummy.hashCode();
        }
         else if (other == null) return false;
         else return false;
    }


    /**
     * Converts MessageClackData to a string representing the details of the object
     * 
     * @return String representing the details of MessageClackData object
     */
    public String toString(){
        return "message: " + this.message +
        ", user name: " + this.getUserName() +
        ", type:" + this.getType()+ 
        ", date: " + this.getDate();
    }
}
