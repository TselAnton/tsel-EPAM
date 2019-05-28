package exceptions;

import org.springframework.stereotype.Component;

@Component
public class XmlFileNotValid extends Exception {
    public XmlFileNotValid(String message) {
        super(message);
    }
}
