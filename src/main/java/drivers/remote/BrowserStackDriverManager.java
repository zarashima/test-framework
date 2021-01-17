package drivers.remote;

import config.BrowserStackConfig;
import drivers.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class BrowserStackDriverManager {
	public RemoteWebDriver createDriver(Device device) {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("device", device.getDeviceName());
		desiredCapabilities.setCapability("os_version", device.getDeviceVersion());
		for (Map.Entry<String, Object> setting : BrowserStackConfig.getSettings().entrySet()) {
			desiredCapabilities.setCapability(setting.getKey(), setting.getValue());
		}
		try {
			driver = new AndroidDriver<>(new URL("https://" +
					BrowserStackConfig.getUserName() + ":" +
					BrowserStackConfig.getAccessKey() + "@hub-cloud.browserstack.com/wd/hub"), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
