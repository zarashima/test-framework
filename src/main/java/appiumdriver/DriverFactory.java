package appiumdriver;

import appiumdriver.local.LocalDriverManager;
import io.appium.java_client.AppiumDriver;

public class DriverFactory {

	private DriverFactory() {
	}

	public static AppiumDriver createInstance(String platformName, String udid, String deviceName) {
		AppiumDriver driver;
		driver = new LocalDriverManager().createInstance(platformName, udid, deviceName);
		return driver;
	}
}
