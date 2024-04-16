package startup.exception;

public class InvalidHandStateTransitionException extends Exception {

    private static final String DEFAULT_MESSAGE = "Hand state transition is invalid.";

    public InvalidHandStateTransitionException(final String message) {
        super(message);
    }

    public InvalidHandStateTransitionException() {
        super(DEFAULT_MESSAGE);
    }

}
