package by.ladyka.club;

public enum EventStatus {
	PENDING(10), IN_PROGRESS(20), DONE(30), CANCEL(40), DELETE(-1), DRAFT(0);

	private final int code;

	EventStatus(int i) {
		this.code = i;
	}

	public int getCode() {
		return code;
	}
}
