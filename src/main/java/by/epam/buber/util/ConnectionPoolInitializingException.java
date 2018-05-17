package by.epam.buber.util;

public class ConnectionPoolInitializingException extends RuntimeException{

    public ConnectionPoolInitializingException(String message) {
        super(message);
    }

    public ConnectionPoolInitializingException(String message, Throwable cause) {
        super(message, cause);
    }
}
