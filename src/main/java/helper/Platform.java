package helper;

public enum Platform {
	ANDROID("android"), IOS("iOS"), BROWSERSTACKS("browserstacks");

	public final String platformName;

	Platform(String platformName) {
		this.platformName = platformName;
	}
}
