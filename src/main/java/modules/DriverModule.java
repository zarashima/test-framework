package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import ensure.Wait;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import appiumdriver.DriverManager;

public class DriverModule extends AbstractModule {

	@Provides
	public AppiumDriver getDriver() {
		return DriverManager.getDriver();
	}

	@Provides
	public Wait getWait() {
		return new Wait(DriverManager.getDriver());
	}

	@Provides
	public JavascriptExecutor getJsExecutor() {
		return (JavascriptExecutor) DriverManager.getDriver();
	}

}
