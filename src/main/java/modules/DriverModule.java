package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import drivers.DriverManager;
import ensure.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverModule extends AbstractModule {

	@Provides
	public RemoteWebDriver getDriver() {
		return DriverManager.getDriver();
	}

	@Provides
	public Wait getWait() {
		return new Wait(DriverManager.getDriver());
	}

	@Provides
	public JavascriptExecutor getJsExecutor() {
		return DriverManager.getDriver();
	}

}
