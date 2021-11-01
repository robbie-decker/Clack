package data;

import java.util.Date;
import java.lang.*;
import java.io.Serializable;
/**
 * An abstract Class representing data sent between a client and a server
 *
 * @author Chris Hickman
 */
abstract public class ClackData implements Serializable {
    private String userName;
    private int type;
    private Date date;
    public static final int CONSTANT_LISTUSERS = 0;
    public static final int CONSTANT_LOGOUT = 1;
    public static final int CONSTANT_SENDMESSAGE = 2;
    public static final int CONSTANT_SENDFILE = 3;

    /**
     * This ClackData constructor initializes the user name and type with
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
    /**-
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
     * @param key Key used to decrypt string of object
     * @return A string describing an object's data
     */
    abstract public String getData(String key);

    /**
     * abstract method for returning a child class's data
     *
     * @return A string describing an object's data
     */
    abstract public String getData();

    /**
     * A method for encrypting a key
     *
     * @param inputStringToEncrypt A string to encrypt.
     * @param key The key associated with the encryption.
     * @return A string encrypted with the key.
     */

    protected String encrypt(String inputStringToEncrypt, String key) {

        char[] dummy = new char[inputStringToEncrypt.length()]; // toUpperCase, isUpperCase
        int track = 0;
        for (int i = 0; i < inputStringToEncrypt.length(); i++) {
            if (Character.isUpperCase(inputStringToEncrypt.charAt(i))) {
                if(Character.isUpperCase(key.charAt(track))) {
                    dummy[i] = (char)(((inputStringToEncrypt.charAt(i) + (key.charAt(track)-65))%26)+'A');
                }
                else {
                    dummy[i] = (char)(((inputStringToEncrypt.charAt(i) + (key.charAt(track)-97))%26)+'A');
                }
                track++;
            }
            else if (Character.isLowerCase(inputStringToEncrypt.charAt(i))) {
                if(Character.isUpperCase(key.charAt(track))) {
                    dummy[i] = (char)(((inputStringToEncrypt.charAt(i) + (key.charAt(track)-65))%26)+'a');
                }
                else {
                    dummy[i] = (char)(((inputStringToEncrypt.charAt(i) + (key.charAt(track)-97))%26)+'a');
                }
                track++;
            }
            else dummy[i] = inputStringToEncrypt.charAt(i);
            if(track == key.length()) track = 0;
        }
        return new String(dummy);
    }

    /**
     * A method for decrypting an encrypted String.
     *
     * @param inputStringToDecrypt The string needed to be decrypted.
     * @param key The key the string is encrypted with.
     * @return The decrypted String.
     */
    protected String decrypt(String inputStringToDecrypt, String key){
        char[] dummy = new char[inputStringToDecrypt.length()]; // toUpperCase, isUpperCase
        int track = 0;
        for (int i = 0; i < inputStringToDecrypt.length(); i++) {
            if (Character.isUpperCase(inputStringToDecrypt.charAt(i))) {
                if(Character.isUpperCase(key.charAt(track))) {
                    dummy[i] = (char)(((inputStringToDecrypt.charAt(i) - (key.charAt(track)-65))%26)+'A');
                }
                else {
                    dummy[i] = (char)(((inputStringToDecrypt.charAt(i) - (key.charAt(track)-97))%26)+'A');
                }
                track++;
            }
            else if (Character.isLowerCase(inputStringToDecrypt.charAt(i))) {
                if(Character.isUpperCase(key.charAt(track))) {
                    dummy[i] = (char)(((inputStringToDecrypt.charAt(i) - (key.charAt(track)-65 + 12))%26)+'a');
                }
                else {
                    dummy[i] = (char)(((inputStringToDecrypt.charAt(i) - (key.charAt(track)-97 + 12))%26)+'a');
                }
                track++;
            }
            else dummy[i] = inputStringToDecrypt.charAt(i);
            if(track == key.length()) track = 0;
        }
        return new String(dummy);
    }
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
