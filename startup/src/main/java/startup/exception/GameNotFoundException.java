package startup.exception;

public class GameNotFoundException extends Exception {

    private static final String DEFAULT_MESSAGE = "Game not found.";

    public GameNotFoundException(final String message) {
        super(message);
    }

    public GameNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
