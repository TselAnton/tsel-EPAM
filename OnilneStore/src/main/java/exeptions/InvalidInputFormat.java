package exeptions;

public class InvalidInputFormat extends Exception {

    public InvalidInputFormat(String message) {
        super(message);
    }

    public InvalidInputFormat(Exception e) {super(e);}
}
