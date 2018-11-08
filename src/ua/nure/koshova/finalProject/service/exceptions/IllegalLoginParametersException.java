package ua.nure.koshova.finalProject.service.exceptions;

public class IllegalLoginParametersException extends RuntimeException {

    public IllegalLoginParametersException() {
        super();
    }

    public IllegalLoginParametersException(Throwable cause) {
        super(cause);
    }

    public IllegalLoginParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalLoginParametersException(String message) {
        super(message);
    }
}
