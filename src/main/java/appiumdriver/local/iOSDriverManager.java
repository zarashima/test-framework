package appiumdriver.local;

import helper.StringConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import server.AppiumServerManager;
import utils.PropertyUtils;
import utils.YamlUtils;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class iOSDriverManager {
	public AppiumDriver createDriver(String platformName, String deviceId, String deviceName) {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(PLATFORM_NAME, platformName);
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceId);
		desiredCapabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, ThreadLocalRandom.current().nextInt(8100, 8299));
		try {
			for (Map.Entry<String, Object> entry: YamlUtils.loadiOSCapabilities().entrySet())
				if (entry.getKey().equals("app")) {
					desiredCapabilities.setCapability(entry.getKey(), FileSystems.getDefault()
							.getPath((String) entry.getValue())
							.toAbsolutePath().normalize().toString());
				}
				else
					desiredCapabilities.setCapability(entry.getKey(), entry.getValue());
				driver = new AndroidDriver<>(new URL(AppiumServerManager.getAppiumServerAddress()), desiredCapabilities);
		} catch (FileNotFoundException | MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
