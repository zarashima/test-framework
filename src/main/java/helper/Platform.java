package helper;

public enum Platform {
	ANDROID("android"),
	IOS("iOS"),
	BROWSERSTACK("browserstacks"),
	CHROME("chrome"),
	FIREFOX("firefox");

	public final String platformName;

	Platform(String platformName) {
		this.platformName = platformName;
	}
}
