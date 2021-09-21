package data;
import data.ClackData;

/**
 *
 *
 * @author: Chris Hickman
 *
 *
 * 
 */
public class FileClackData extends ClackData  {


    private String fileName;
    private String fileContents;

   public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;

    }
   public FileClackData(){
        this("anon", "anon", 0); // <-- calls super how???
        //calls super through above constructor
    }
    public void setFileName(String fileName){
       this.fileName = fileName;
    }
    public String getFileName(){
        return this.fileName;
    }
    public String getData(){ return this.fileContents;}
    public void readFileContents(){
   }
    public void writeFileContents(){
   }

    public int hashCode(){
       return this.fileName.hashCode(); //           <-- this???
    }


    public boolean equals(Object other){
       if(other instanceof FileClackData) {
           FileClackData f2 = (FileClackData) other;
            return super.equals(f2) &&
                    this.fileName == f2.fileName &&
                    this.fileContents == f2.fileContents;
       }
        else if (other == null) return false;
        else return false;
   }
    public String toString(){
        return  "file name: " + this.fileName  +
                ", file contents: " + this.fileContents  +
                ", user name: " + this.getUserName()  +
                ", type: " + this.getType()  +
                ", date: " + this.getDate();
   }


}
