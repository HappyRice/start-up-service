package startup.model.enumeration;

public enum Rank {

    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String code;

    Rank(String code) {
        this.code = code;
    }

    /**
     * Returns the name of the rank.
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return code;
    }

}
