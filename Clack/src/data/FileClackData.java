package data;
import data.ClackData;

/**
 * A Class, inheriting from ClackData, that represents the contents of a file.
 *
 * @author: Chris Hickman
 */
public class FileClackData extends ClackData  {
    private String fileName;
    private String fileContents;

    /**
     * A constructor for FileClackData that initializes the child and parents
     * user name, file name, and type with user given values. Initializes file contents to null.
     *
     * @param userName String representing the users name.
     * @param fileName String representing the name of the file.
     * @param type Integer representing the type of file.
     */
   public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = "null";
    }

    /**
     * Constructor for FileClackData that takes no arguments and initializes file name and user name to "anon"
     * and the type to 0. Calls ClackData's constructor.
     */
   public FileClackData(){
        this("anon", "anon", 0);
        //calls super through above constructor
    }

    /**
     * Mutator for FileClackData's file name.
     *
     * @param fileName String representing the file name.
     */
    public void setFileName(String fileName){
       this.fileName = fileName;
    }

    /**
     * Accessor for FileClackData's file name.
     *
     * @return String representing the file name.
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * Accessor for FileClackData's data
     *
     * @return String representing the object's data.
     */
    public String getData(){ return this.fileContents;}

    /**
     * Reads a file's content.
     */
    public void readFileContents(){
   }

    /**
     * Writes to a file.
     */
    public void writeFileContents(){
   }

    /**
     * Generates a hashcode for a FileClackData object
     *
     * @return A unique integer represnting the object's hashcode.
     */
    public int hashCode(){
       int dummy = 13;
       dummy = 17*dummy + fileName.hashCode();
       dummy = 17*dummy + fileContents.hashCode();
       dummy = 17*dummy + this.getUserName().hashCode();
       dummy = 17*dummy + this.getType();
       dummy = 17*dummy + this.getData().hashCode();
       return dummy;
    }

    /**
     * Checks to see if two FileClackData objects are identical to each other.
     *
     * @param other FileClackData being compared to original.
     *
     * @return A boolean representing whether the two FileClackData objects are equal.
     */
    public boolean equals(Object other){
       if(other instanceof FileClackData) {
           FileClackData f2 = (FileClackData) other;
            return this.hashCode() == f2.hashCode();
       }
        else if (other == null) return false;
        else return false;
   }

    /**
     * Converts a FileClackData object to a string representing the details of the object.
     *
     * @return A string representing the details of a FileClackData object.
     */
    public String toString(){
        return  "file name: " + this.fileName  +
                ", file contents: " + this.fileContents  +
                ", user name: " + this.getUserName()  +
                ", type: " + this.getType()  +
                ", date: " + this.getDate();
   }
}
