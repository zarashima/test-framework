package keywords.mobile;

import com.google.inject.Inject;
import drivers.DriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DeviceKW {

	@Inject
	RemoteWebDriver driver;

	@Inject
	public DeviceKW(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public String getPlatformName() {
		return driver.getCapabilities().getCapability("platformName").toString();
	}

	public String getVersion() {
		return (String) DriverManager.getDriver().getCapabilities().getCapability("platformVersion");
	}

	public boolean isMobile() {
		return isAndroid() || isIOS();
	}

	public boolean isAndroid() {
		return getPlatformName().equalsIgnoreCase("android");
	}

	public boolean isIOS() {
		return getPlatformName().equalsIgnoreCase("iOS");
	}

}
