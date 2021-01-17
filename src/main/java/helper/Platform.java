package helper;

public enum Platform {
	ANDROID("android"),
	IOS("iOS"),
	BROWSERSTACK("browserstacks"),
	CHROME("chrome"),
	REMOTE("remote"),
	FIREFOX("firefox");

    public final String platformName;

	Platform(String platformName) {
		this.platformName = platformName;
	}
}
