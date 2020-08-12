package appiumdriver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;

public class DriverManager {

	private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

	private DriverManager() {
	}

	public static AppiumDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(AppiumDriver driver) {
		DriverManager.driver.set(driver);
	}

	public static void quit() {
		DriverManager.driver.get().quit();
		driver.remove();
	}

	public static String getDeviceName() {
		Capabilities cap = DriverManager.getDriver().getCapabilities();
		String deviceName = cap.getCapability("deviceModel").toString();
		String deviceVersion = cap.getCapability("platformVersion").toString();
		return String.format("%s-%s", deviceName, deviceVersion);
	}

	public static String getPlatformName() {
		return (String) DriverManager.getDriver().getCapabilities().getCapability("platformName");
	}
}
