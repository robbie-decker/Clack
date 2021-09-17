package data;
import data.ClackData;

public class FileClackData extends ClackData  {
    private String fileName;
    private String fileContents;

   public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = "null";

    }
   public FileClackData(){
        this("anon", "anon", 0);
        //calls super through above constructor
    }
    public void setFileName(String fileName){
       this.fileName = fileName;
    }
    public String getFileName(){
        return this.fileName;
    }
    public int getData(){
        return this.getData();
    }
    public void readFileContents(){

    }
    public void writeFileContents(){

    }

    //hashCode <-- say what??
    public FileClackData equals(){
        return new FileClackData(); // <-- implementation needed
    }
    public String toString(){
        return "void"; // <-- implementation needed
    }


}
