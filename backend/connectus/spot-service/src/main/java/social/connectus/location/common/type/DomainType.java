package social.connectus.location.common.type;

public enum DomainType {

	EVENT("event");

	private String value;

	DomainType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
