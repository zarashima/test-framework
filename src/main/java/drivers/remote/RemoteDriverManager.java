package drivers.remote;

import config.*;
import drivers.Device;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RemoteDriverManager {
	public RemoteWebDriver createDriver(Device device) {
		RemoteWebDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		for (Map.Entry<String, Object> setting : RemoteConfig.getSettings().entrySet()) {
			desiredCapabilities.setCapability(setting.getKey(), setting.getValue());
		}
		try {
			System.out.println((String) RemoteConfig.getSettings().get("url"));
			driver = new RemoteWebDriver(new URL((String) RemoteConfig.getSettings().get("url")), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
