package startup.common.enumeration;

public enum GameType {

    TEXAS_HOLDEM("Texas Hold'em"),
    CRAZY_PINEAPPLE("Crazy Pineapple");

    private final String description;

    GameType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
