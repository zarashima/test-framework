package appiumdriver.local;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;
import server.AppiumServerManager;
import utils.PropertyUtils;
import utils.YamlUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;


public class AndroidDriverManager {
	public AppiumDriver createDriver(String platformName, String udid, String deviceName) {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(PLATFORM_NAME, platformName);
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, ThreadLocalRandom.current().nextInt(8200, 8299));
		try {
			for (Map.Entry<String, Object> entry:YamlUtils.loadAndroidCapabilities().entrySet()) {
				if (entry.getKey().equals("app")) {
					desiredCapabilities.setCapability(entry.getKey(), FileSystems.getDefault()
							.getPath((String) entry.getValue())
							.toAbsolutePath().normalize().toString());
				}
				else
					desiredCapabilities.setCapability(entry.getKey(), entry.getValue());
			}
			driver = new AndroidDriver<>(new URL(AppiumServerManager.getAppiumServerAddress()), desiredCapabilities);
		} catch (FileNotFoundException | MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
