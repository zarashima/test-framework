package keywords;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;

public class Device {

	@Inject
	AppiumDriver driver;

	@Inject
	public Device(AppiumDriver driver) {
		this.driver = driver;
	}

	public boolean isAndroid() {
		return driver.getPlatformName().toLowerCase().equals("android");
	}

	public boolean isIOS() {
		return driver.getPlatformName().toLowerCase().equals("iOS");
	}
}
