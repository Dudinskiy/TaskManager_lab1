package ua.edu.sumdu.j2se.dudynskyi.ui.exceptions;

public class InputDataException extends Exception{

    public InputDataException() {
        super();
    }

    public InputDataException(String message) {
        super(message);
    }

    public InputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputDataException(Throwable cause) {
        super(cause);
    }
}
