package startup.common.enumeration;

public enum HandState {

    PREFLOP("Pre-Flop", true),
    FLOP("Flop", true),
    TURN("Turn", true),
    RIVER("River", true);

    static {
        PREFLOP.fromState = RIVER;
        FLOP.fromState = PREFLOP;
        TURN.fromState = FLOP;
        RIVER.fromState = TURN;
    }

    private final String description;

    private final boolean isActive;

    private HandState fromState;

    HandState(final String description, final boolean isActive) {
        this.description = description;
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }

    public HandState getFromState() {
        return fromState;
    }

}
