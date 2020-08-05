package keywords;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import ensure.Wait;
import helper.StringConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Application {

	@Inject
	AppiumDriver driver;

	@Inject
	Device device;

	@Inject
	Element element;

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

	public void runAppInBackground(long seconds) {
		driver.runAppInBackground(Duration.ofSeconds(seconds));
	}

	public void openDeepLink(URL url, String packageName) {
		ImmutableMap<String, String> args = ImmutableMap.of(
				"url", url.toString(),
				"bundle", packageName
		);
		if (device.isIOS()) {
			launchApp(StringConstants.IOS_SAFARI_BUNDLE_ID);
			if (((IOSDriver) driver).isKeyboardShown()) {
				element.click(((IOSDriver) driver).findElementByIosNsPredicate("type == 'XCUIElementTypeButton' && name CONTAINS 'URL'"));
			}
			element.setText(((IOSDriver) driver).findElementByIosNsPredicate("type == 'XCUIElementTypeTextField' && name CONTAINS 'URL'"), url.toString());
			element.click(((IOSDriver) driver).findElementByIosNsPredicate("type == 'XCUIElementTypeButton' && name CONTAINS 'Open'"));
		}
		mobileActions.executeScript("mobile: deeplink", args);
	}

	public String getCurrentPackage() {
		return driver.getCapabilities().getCapability("appPackage").toString();
	}

	public String getCurrentActivity() {
		return driver.getCapabilities().getCapability("appActivity").toString();
	}

	public List<List<Object>> getPerformanceData(String packageName, String dataType, int dataReadTimeout) {
		if (device.isAndroid())
			return ((AndroidDriver) driver).getPerformanceData(packageName, dataType, dataReadTimeout);
		else
			throw new UnsupportedOperationException("iOS is not supported");
	}
}
