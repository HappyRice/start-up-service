package startup.common.enumeration;

public enum GameState {

    CREATED("Created"),
    IN_PROGRESS("In Progress"),
    ENDED("Ended");

    private final String description;

    GameState(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
