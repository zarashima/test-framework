package drivers.local;

import drivers.Device;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromeDriverManager {
	public RemoteWebDriver createDriver(Device device) {
		WebDriverManager.getInstance(CHROME).setup();
		return new ChromeDriver();
	}
}
