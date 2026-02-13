package pl.sprint.dialer;

public enum StatusPhone {
    UNKNOWN(0),
    NEW(1),
    ANSWER(2),
    BUSY(3),
    NO_ANSWER(4),
    FAX(5),
    VOICEMAIL(6),
    ERROR(7),
    DNC(8),
    EMPTY(9);

    private final int value;

    StatusPhone(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
