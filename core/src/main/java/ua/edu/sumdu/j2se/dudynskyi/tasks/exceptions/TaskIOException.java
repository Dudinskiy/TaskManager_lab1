package ua.edu.sumdu.j2se.dudynskyi.tasks.exceptions;

public class TaskIOException extends Exception{
    public TaskIOException() {
    }

    public TaskIOException(String message) {
        super(message);
    }

    public TaskIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskIOException(Throwable cause) {
        super(cause);
    }
}
