package keywords;

import com.google.inject.Inject;
import ensure.Wait;
import helper.StringConstants;
import io.appium.java_client.AppiumDriver;

import java.util.HashMap;

public class Application {

	@Inject
	AppiumDriver driver;

	@Inject
	Device device;

	@Inject
	MobileActions mobileActions;

	@Inject
	Wait wait;

	@Inject
	public Application(AppiumDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public boolean isAppInstalled(String bundleId) {
		if (device.isAndroid())
			return driver.isAppInstalled(bundleId);
		else
			return (Boolean) driver.executeScript("mobile : isAppInstalled", new HashMap<>().put("bundleId", bundleId));
	}

	public void activateApp(String bundleId) {
		if (device.isAndroid())
			driver.activateApp(bundleId);
		else
			mobileActions.executeScript("mobile : activateApp", new HashMap<>().put("bundleId", bundleId));
	}

	public void installApp(String appPath) {
		if (device.isAndroid())
			driver.activateApp(appPath);
		else
			mobileActions.executeScript("mobile: installApp", new HashMap<>().put("app", appPath));
	}

	public void launchApp(String bundleId) {
		if (device.isAndroid())
			driver.launchApp();
		else
			mobileActions.executeScript("mobile: launchApp", new HashMap<>().put("bundleId", bundleId));
	}

	public void startBrowserApplication() {
		if (device.isAndroid())
			activateApp(StringConstants.ANDROID_CHROME_BUNDLE_ID);
		else
			activateApp(StringConstants.IOS_SAFARI_BUNDLE_ID);
	}
}
