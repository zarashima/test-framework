package drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	private DriverManager() {
	}

	public static RemoteWebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(RemoteWebDriver driver) {
		DriverManager.driver.set(driver);
	}

	public static void quit() {
		DriverManager.driver.get().quit();
		driver.remove();
	}

	public static String getDeviceName() {
		Capabilities cap = DriverManager.getDriver().getCapabilities();
		String deviceName = cap.getCapability("deviceName").toString();
		String deviceVersion = cap.getCapability("platformVersion").toString();
		return String.format("%s_%s", deviceName, deviceVersion);
	}

	public static String getPlatformName() {
		return (String) DriverManager.getDriver().getCapabilities().getCapability("platformName");
	}

	public static String getVersion() {
		return (String) DriverManager.getDriver().getCapabilities().getCapability("platformVersion");
	}

	public static String getSessionID() {
		return DriverManager.getDriver().getSessionId().toString();
	}
}
