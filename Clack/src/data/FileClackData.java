package data;
import data.ClackData;

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
    public void equals(FileClackData toBeSet){
        this.fileContents = toBeSet.fileContents;
        this.fileName = toBeSet.fileName;//       <-- I am confusion

    }
    public String toString(){
        return  "file name: " + this.fileName +
                "file contents: " + this.fileContents +
                "user name: " + this.getUserName() +
                "type: " + this.getType() +
                "date: " + this.getDate();
   }


}
