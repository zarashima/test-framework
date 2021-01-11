package keywords.mobile;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import ensure.Wait;
import helper.StringConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import keywords.common.Element;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class Application {

	@Inject
	RemoteWebDriver driver;

	@Inject
	DeviceKW deviceKW;

	@Inject
	Element element;

	@Inject
	MobileActions mobileActions;

	@Inject
	Wait wait;

	@Inject
	public Application(RemoteWebDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public boolean isAppInstalled(String bundleId) {
		if (deviceKW.isAndroid())
			return ((AppiumDriver) driver).isAppInstalled(bundleId);
		else
			return (Boolean) driver.executeScript("mobile : isAppInstalled", new HashMap<>().put("bundleId", bundleId));
	}

	public void activateApp(String bundleId) {
		((AppiumDriver) driver).activateApp(bundleId);
	}

	public void installApp(String appPath) {
		((AppiumDriver) driver).installApp(appPath);
	}

	public void launchApp() {
		((AppiumDriver) driver).launchApp();
	}

	public void runAppInBackground(long seconds) {
		((AppiumDriver) driver).runAppInBackground(Duration.ofSeconds(seconds));
	}

	public void startBrowser() {
		if (deviceKW.isAndroid())
			activateApp(StringConstants.ANDROID_CHROME_BUNDLE_ID);
		else
			activateApp(StringConstants.IOS_SAFARI_BUNDLE_ID);
	}

	public String getWebContext() {
		ArrayList<String> contexts = new ArrayList<String>(((AppiumDriver) driver).getContextHandles());
		for (String context : contexts) {
			if (!context.equals("NATIVE_APP")) {
				return context;
			}
		}
		return null;
	}

	public void switchContext() {
		((AppiumDriver) driver).context(getWebContext());
	}

	public void openDeepLink(URL url, String packageName) {
		ImmutableMap<String, String> args = ImmutableMap.of(
				"url", url.toString(),
				"bundle", packageName
		);
		if (deviceKW.isIOS()) {
			activateApp(StringConstants.IOS_SAFARI_BUNDLE_ID);
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

	public void startActivity(String appPackage, String appActivity) {
		((AndroidDriver) driver).startActivity(new Activity(appPackage, appActivity));
	}
}
