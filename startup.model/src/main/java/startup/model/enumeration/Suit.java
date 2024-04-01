package startup.model.enumeration;

public enum Suit {

    CLUBS("c"),
    DIAMONDS("d"),
    HEARTS("h"),
    SPADES("s");

    private final String code;

    Suit(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}
