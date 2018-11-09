package ua.nure.koshova.finalProject.db.exception;

public class CloseResourcesException extends RuntimeException {
    public CloseResourcesException() {
        super();
    }

    public CloseResourcesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloseResourcesException(String message) {
        super(message);
    }
}
