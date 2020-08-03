package appiumdriver.local;

import appiumdriver.IDriver;
import io.appium.java_client.AppiumDriver;

public class LocalDriverManager implements IDriver {
	@Override
	public AppiumDriver createInstance(String platformName, String udid, String deviceName) {
		AppiumDriver driver = null;
		switch (platformName.toLowerCase()) {
			case "android":
				driver = new AndroidDriverManager().createDriver(platformName, udid, deviceName);
				break;
			case "iOS":
				driver = new iOSDriverManager().createDriver(platformName, udid, deviceName);
				break;
			default:
				throw new IllegalArgumentException("Not supported browser");
		}
		return driver;
	}
}
