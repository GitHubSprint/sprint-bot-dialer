package pl.sprint.dialer.model;

public record ErrorResponse(
	String errorCode,
	String message,
	int status
) {
}
