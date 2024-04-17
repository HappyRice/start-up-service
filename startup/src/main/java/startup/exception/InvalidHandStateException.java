package startup.exception;

public class InvalidHandStateException extends Exception {

    private static final String DEFAULT_MESSAGE = "Hand is not in the right state.";

    public InvalidHandStateException(final String message) {
        super(message);
    }

    public InvalidHandStateException() {
        super(DEFAULT_MESSAGE);
    }

}
