package test;

import data.*;

import java.io.File;

public class TestClackData {

    public static void main(String[] args){
/* have to test ->
    public int getType(){ |
    public String getUserName(){ |
    public Date getDate(){ |
    abstract public String getData(); |
    public boolean equals(Object obj){ |

   public FileClackData(String userName, String fileName, int type){ |
   public FileClackData(){ |
    public void setFileName(String fileName){
    public String getFileName(){ |
    public String getData(){ return this.fileContents;} |
    public void readFileContents(){ |
    public void writeFileContents(){ |
    public int hashCode(){
    public boolean equals(Object other){
    public String toString(){

 */
 FileClackData cd1 = new FileClackData("Chris", "this.txt", 1); //constructors work
 FileClackData fcd1 = new FileClackData();
 System.out.println("fcd1 String: " + fcd1.toString()); // toString works
 System.out.println("cd1 String: " + cd1.toString());
 System.out.println("fcd1's type: " + fcd1.getType()); //accessor for type works
 System.out.println("cd1's type: " + cd1.getType());
 System.out.println(fcd1.getUserName());
 System.out.println(cd1.getUserName()); //accessor for name works
 System.out.println(fcd1.getDate()); //accessor for date works
 System.out.println(cd1.getDate());
 System.out.println(fcd1.getData()); //accessor for data works
 System.out.println(cd1.getData());
 System.out.println(fcd1.getData()); //accessor for data work
 System.out.println(cd1.getData());
 System.out.println(fcd1.getFileName()); //accessor for file name works
 System.out.println(cd1.getFileName());

 FileClackData cd2 = new FileClackData("Chris", "e.txt", 1);
        System.out.println(cd1.hashCode());
        System.out.println(cd2.hashCode());
        System.out.println("cd2 = cd1? " + cd2.equals(cd1));
        System.out.println(cd2.hashCode() + " = " + cd1.hashCode());
        cd2.setFileName("this.txt"); //mutator works

        System.out.println("Changed fileName from cd2 to the same as cd1.");
        System.out.println(cd2.hashCode() + " = " + cd1.hashCode());
        System.out.println("cd2 = cd1? " + cd2.equals(cd1));


    };
}
