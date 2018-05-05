package exception;

public class NotValidInputException extends Exception {
    public NotValidInputException() {

    }

    public NotValidInputException(String message) {
        super(message);
    }
}
