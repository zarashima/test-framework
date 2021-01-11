package drivers.local;

import drivers.Device;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;

public class FirefoxDriverManager {
	public RemoteWebDriver createDriver(Device device) {
		WebDriverManager.getInstance(FIREFOX).setup();
		return new FirefoxDriver();
	}
}
