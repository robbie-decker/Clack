package data;

public class MessageClackData extends ClackData {
    private String message;

    public MessageClackData(String userName, String message, int type){

    }

    public MessageClackData(){
        this("anon", "anon", 0);
    }
    
    public void getData(){

    }

    public int hashCode(){
        return 0;
    }

    public boolean equals(Object other){
        return false;
    }

    public String toString(){
        return "hello";
    }
}
