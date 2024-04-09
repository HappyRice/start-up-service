package startup.common.enumeration;

public enum HandState {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String description;

    HandState(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
