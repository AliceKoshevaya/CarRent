package ua.nure.koshova.finalProject.db.exception;

public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException() {
        super();
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable clause) {
        super(message, clause);
    }
}
