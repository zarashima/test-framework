package drivers.local;

import drivers.Device;
import helper.Platform;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import server.AppiumServerManager;
import utils.YamlUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.SYSTEM_PORT;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.VERSION;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class AndroidDriverManager {
	public RemoteWebDriver createDriver(Device device) {
		RemoteWebDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(PLATFORM_NAME, device.getPlatformName());
		desiredCapabilities.setCapability(DEVICE_NAME, device.getDeviceName());
		desiredCapabilities.setCapability(VERSION, device.getDeviceVersion());
		desiredCapabilities.setCapability(UDID, device.getDeviceId());
		desiredCapabilities.setCapability(SYSTEM_PORT, ThreadLocalRandom.current().nextInt(8200, 8299));
		try {
			for (Map.Entry<String, Object> entry : YamlUtils.getInstance().getNodeFromKey(Platform.ANDROID.platformName).entrySet()) {
				if (entry.getKey().equals("app")) {
					desiredCapabilities.setCapability(entry.getKey(), FileSystems.getDefault()
							.getPath((String) entry.getValue())
							.toAbsolutePath().normalize().toString());
				} else
					desiredCapabilities.setCapability(entry.getKey(), entry.getValue());
			}
			driver = new AndroidDriver<>(new URL(AppiumServerManager.getAppiumServerAddress()), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
