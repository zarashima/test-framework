package tests;

import appiumdriver.DriverFactory;
import appiumdriver.DriverManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import keywords.*;
import modules.DriverModule;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomeScreen;
import reportportal.Launch;
import reportportal.LaunchHandler;
import reportportal.SessionContext;
import server.AppiumServerManager;
public class BaseTest {

	protected AppiumDriver<?> driver;
	protected Browser browserKeywords;
	protected Element elementKeywords;
	protected Verification verificationKeywords;
	protected Clipboard clipboardKeywords;
	protected HomeScreen homeScreen;
	protected Logs logsKeyword;
	protected Application applicationKeywords;
	protected Launch launch;
	protected static String devices = "";

	@BeforeSuite
	public void beforeSuite() {
		AppiumServerManager.startAppiumServer();
	}

	@BeforeTest
	@Parameters({"platformName", "deviceId", "deviceName"})
	public void beforeTest(String platformName, String deviceId, String deviceName) {
		Injector injector = Guice.createInjector(new DriverModule());
		driver = DriverFactory.createInstance(platformName, deviceId, deviceName);
		DriverManager.setDriver(driver);
		homeScreen = injector.getInstance(HomeScreen.class);
		browserKeywords = injector.getInstance(Browser.class);
		elementKeywords = injector.getInstance(Element.class);
		logsKeyword = injector.getInstance(Logs.class);
		verificationKeywords = injector.getInstance(Verification.class);
		applicationKeywords = injector.getInstance(Application.class);
		clipboardKeywords = injector.getInstance(Clipboard.class);
		launch = injector.getInstance(Launch.class);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest(ITestResult iTestResult) {
		ExtentTestManager.getTest().assignCategory(DriverManager.getDeviceName());
	}

	@AfterTest
	public void afterTest(ITestContext context) {
		devices = devices + DriverManager.getDeviceName() + "_";
		if (SessionContext.getRpEnable()) {
			launch.setAttributes("device", devices);
			LaunchHandler.updateLaunch(launch.getAttributes(), context.getCurrentXmlTest().getName());
		}
		DriverManager.quit();
	}

	@AfterSuite
	public void afterSuite() {
		AppiumServerManager.stopAppiumServer();
	}
}
