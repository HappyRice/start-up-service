package startup.exception;

public class HandNotFoundException extends Exception {

    private static final String DEFAULT_MESSAGE = "Hand not found.";

    public HandNotFoundException(final String message) {
        super(message);
    }

    public HandNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
