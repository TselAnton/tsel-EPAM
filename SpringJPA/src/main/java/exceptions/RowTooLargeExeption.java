package exceptions;

import org.springframework.stereotype.Component;

@Component
public class RowTooLargeExeption extends Exception {
    public RowTooLargeExeption(String message) {
        super(message);
    }
}
