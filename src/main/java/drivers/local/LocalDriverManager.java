package drivers.local;

import drivers.Device;
import drivers.IDriver;
import drivers.remote.BrowserStackDriverManager;
import helper.Platform;
import io.appium.java_client.AppiumDriver;

public class LocalDriverManager implements IDriver {
	@Override
	public AppiumDriver createInstance(Device device) {
		AppiumDriver driver = null;
		switch (device.getPlatformName().toLowerCase()) {
			case "android":
				driver = new AndroidDriverManager().createDriver(device);
				break;
			case "ios":
				driver = new iOSDriverManager().createDriver(device);
				break;
			case "browserstack":
				driver = new BrowserStackDriverManager().createDriver(device);
				break;
			default:
				throw new IllegalArgumentException("Not supported platforms");
		}
		return driver;
	}
}
