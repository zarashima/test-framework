package appiumdriver.local;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import server.AppiumServerManager;
import utils.PropertyUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

import static appiumdriver.local.DesiredCapabilitiesManager.getInstance;

public class AndroidDriverManager {
	public AppiumDriver createDriver() {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		desiredCapabilities.setCapability("platformVersion", getInstance().getDesiredCapabilities().get("platformVersion"));
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		desiredCapabilities.setCapability("app", FileSystems.getDefault().getPath(PropertyUtils.getInstance().getAndroidApk())
				.toAbsolutePath().normalize().toString());
		desiredCapabilities.setCapability("deviceName",
				getInstance().getDesiredCapabilities().get("deviceName"));
		desiredCapabilities.setCapability("udid",
				getInstance().getDesiredCapabilities().get("udid"));
		try {
			driver = new AndroidDriver<>(new URL(AppiumServerManager.getAppiumServerAddress()), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
