package pl.sprint.dialer.model;

public enum ContentType {
    JSON("application/json"),
    URL("application/x-www-form-urlencoded");
    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
