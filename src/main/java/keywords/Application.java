package keywords;

import com.google.inject.Inject;
import ensure.Wait;
import io.appium.java_client.AppiumDriver;

import java.util.HashMap;

public class Application {

	@Inject
	AppiumDriver driver;

	@Inject
	Wait wait;

	@Inject
	public Application(AppiumDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public boolean isAppInstalled(String bundleId) {
		if (Common.isAndroid())
			return driver.isAppInstalled(bundleId);
		else
			return (Boolean) driver.executeScript("mobile : isAppInstalled", new HashMap<>().put("bundleId", bundleId));
	}

	public void activateApp(String bundleId) {
		if (Common.isAndroid())
			driver.activateApp(bundleId);
		else
			Common.executeScript("mobile : activateApp", new HashMap<>().put("bundleId", bundleId));
	}

	public void installApp(String appPath) {
		if (Common.isAndroid())
			driver.activateApp(appPath);
		else
			Common.executeScript("mobile: installApp", new HashMap<>().put("app", appPath));
	}

	public void launchApp(String bundleId) {
		if (Common.isAndroid())
			driver.launchApp();
		else
			Common.executeScript("mobile: launchApp", new HashMap<>().put("bundleId", bundleId));
	}
}
