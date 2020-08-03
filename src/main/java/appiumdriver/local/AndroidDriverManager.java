package appiumdriver.local;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import server.AppiumServerManager;
import utils.PropertyUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.concurrent.ThreadLocalRandom;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;


public class AndroidDriverManager {
	public AppiumDriver createDriver(String platformName, String udid, String deviceName) {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(PLATFORM_NAME, platformName);
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "600");
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		desiredCapabilities.setCapability("app", FileSystems.getDefault().getPath(PropertyUtils.getInstance().getAndroidApkPath())
				.toAbsolutePath().normalize().toString());
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, ThreadLocalRandom.current().nextInt(8200, 8299));
		try {
			driver = new AndroidDriver<>(new URL(AppiumServerManager.getAppiumServerAddress()), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
