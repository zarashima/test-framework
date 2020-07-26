package appiumdriver.local;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class iOSDriverManager {
	public AppiumDriver createDriver() {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		desiredCapabilities.setCapability("app", "");
		desiredCapabilities.setCapability("deviceName", "");
		desiredCapabilities.setCapability("udid", "");
		try {
			driver = new IOSDriver(new URL(""), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
