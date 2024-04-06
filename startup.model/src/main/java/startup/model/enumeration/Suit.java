package startup.model.enumeration;

public enum Suit {

    CLUBS("c"),
    DIAMONDS("d"),
    HEARTS("h"),
    SPADES("s");

    private final String code;

    Suit(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
