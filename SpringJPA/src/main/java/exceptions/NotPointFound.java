package exceptions;

public class NotPointFound extends Exception {

    public NotPointFound(String message) {
        super(message);
    }
    public NotPointFound(int point) {
        super("Menu item " + point + " does not exist");
    }
    public NotPointFound(Exception e) {
        super(e);
    }
}
