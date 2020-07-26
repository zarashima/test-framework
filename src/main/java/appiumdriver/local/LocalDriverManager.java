package appiumdriver.local;

import io.appium.java_client.AppiumDriver;
import appiumdriver.IDriver;

public class LocalDriverManager implements IDriver {
    @Override
    public AppiumDriver createInstance(String platform) {
        AppiumDriver driver = null;
		switch(platform) {
			case "android":
				driver = new AndroidDriverManager().createDriver();
				break;
			case "iOS":
				driver = new iOSDriverManager().createDriver();
				break;
			default:
				throw new IllegalArgumentException("Not supported browser");
		}
		return driver;
    }
}
