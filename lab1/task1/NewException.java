package lab1.task1;

public class NewException extends Exception {
    public NewException(String message){
        // Do not change this method!
        super("This a new Exception:"+message);
    }
}
