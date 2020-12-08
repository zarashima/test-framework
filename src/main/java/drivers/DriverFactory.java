package drivers;

import drivers.local.LocalDriverManager;
import io.appium.java_client.AppiumDriver;

public class DriverFactory {

	private DriverFactory() {
	}

	public static AppiumDriver createInstance(Device device) {
		AppiumDriver driver;
		driver = new LocalDriverManager().createInstance(device);
		return driver;
	}
}
