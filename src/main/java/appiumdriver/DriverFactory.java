package appiumdriver;

import io.appium.java_client.AppiumDriver;
import appiumdriver.local.LocalDriverManager;

public class DriverFactory {

    private DriverFactory() {}

    public static AppiumDriver createInstance(String browser) {
        AppiumDriver driver;
        driver = new LocalDriverManager().createInstance(browser);
        return driver;
    }
}
