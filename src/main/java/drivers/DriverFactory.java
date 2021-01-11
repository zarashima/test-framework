package drivers;

import drivers.local.LocalDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	private DriverFactory() {
	}

	public static RemoteWebDriver createInstance(Device device) {
		RemoteWebDriver driver;
		driver = new LocalDriverManager().createInstance(device);
		return driver;
	}
}
