package appiumdriver.local;

import helper.StringConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import server.AppiumServerManager;
import utils.PropertyUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.concurrent.ThreadLocalRandom;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class iOSDriverManager {
	public AppiumDriver createDriver(String platformName, String deviceId, String deviceName) {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(PLATFORM_NAME, StringConstants.IOS_PLATFORM);
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "600");
		desiredCapabilities.setCapability("app", FileSystems.getDefault().getPath(PropertyUtils.getInstance().getiOSAppPath())
				.toAbsolutePath().normalize().toString());
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceId);
		desiredCapabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, ThreadLocalRandom.current().nextInt(8100, 8299));
		try {
			driver = new IOSDriver(new URL(AppiumServerManager.getAppiumServerAddress()), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
