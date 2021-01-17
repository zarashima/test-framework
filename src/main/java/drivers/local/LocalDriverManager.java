package drivers.local;

import drivers.Device;
import drivers.IDriver;
import drivers.remote.BrowserStackDriverManager;
import drivers.remote.RemoteDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalDriverManager implements IDriver {
	@Override
	public RemoteWebDriver createInstance(Device device) {
		RemoteWebDriver driver = null;
		switch (device.getPlatformName().toLowerCase()) {
			case "android":
				driver = new AndroidDriverManager().createDriver(device);
				break;
			case "ios":
				driver = new iOSDriverManager().createDriver(device);
				break;
			case "chrome":
				driver = new ChromeDriverManager().createDriver(device);
				break;
			case "firefox":
				driver = new FirefoxDriverManager().createDriver(device);
				break;
			case "remote":
				driver = new RemoteDriverManager().createDriver(device);
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
