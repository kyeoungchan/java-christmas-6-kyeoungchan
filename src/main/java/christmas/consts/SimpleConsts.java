package christmas.consts;

public enum SimpleConsts {
    NO_COUNT(0),
    MAXIMUM_COUNT(20),
    MEMORY_LIMIT(100_000);

    private final int value;

    SimpleConsts(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
