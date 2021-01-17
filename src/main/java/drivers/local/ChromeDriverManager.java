package drivers.local;

import config.ChromeConfig;
import drivers.Device;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.util.Optional;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromeDriverManager {
	public RemoteWebDriver createDriver(Device device) {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		ChromeOptions chromeOptions = new ChromeOptions();
		Optional.ofNullable(ChromeConfig.getArguments())
				.ifPresent(s -> s.forEach(chromeOptions::addArguments));
		chromeOptions.setCapability(ChromeOptions.CAPABILITY, desiredCapabilities);
		WebDriverManager.getInstance(CHROME).setup();
		return new ChromeDriver(chromeOptions);
	}
}
