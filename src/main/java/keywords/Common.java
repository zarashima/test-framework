package keywords;

import appiumdriver.DriverManager;

public class Common {

	public static synchronized void executeScript(String script, Object... objects) {
		DriverManager.getDriver().executeScript(script, objects);
	}

	public static synchronized boolean isAndroid() {
		return DriverManager.getDriver().getPlatformName().toLowerCase().equals("android");
	}

	public synchronized boolean isIOS() {
		return DriverManager.getDriver().getPlatformName().toLowerCase().equals("iOS");
	}

	public synchronized String getPlatformName() {
		return DriverManager.getDriver().getPlatformName();
	}
}
