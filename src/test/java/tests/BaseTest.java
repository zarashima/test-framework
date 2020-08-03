package tests;

import appiumdriver.DriverFactory;
import appiumdriver.DriverManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import keywords.Browser;
import keywords.Element;
import keywords.Verification;
import modules.DriverModule;
import modules.TestParameters;
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
	protected HomeScreen homeScreen;
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
		verificationKeywords = injector.getInstance(Verification.class);
		launch = injector.getInstance(Launch.class);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult iTestResult) {
		devices = devices + DriverManager.getDeviceName() + "_";
		Class<?> clazz = iTestResult.getTestClass().getRealClass();
		if (SessionContext.getRpEnable()) {
			launch.setAttributes("browser", devices);
			launch.setAttributes("module", TestParameters.getModule(clazz));
			launch.setAttributes("priority", TestParameters.getPriority(clazz).name());
			launch.setAttributes("createdBy", TestParameters.getCreatedBy(clazz));
			LaunchHandler.updateLaunch(launch.getAttributes(), iTestResult.getMethod().getDescription());
		}
		ExtentTestManager.getTest().assignCategory(DriverManager.getDeviceName());
	}

	@AfterTest
	public void afterTest() {
		DriverManager.quit();
	}

	@AfterSuite
	public void afterSuite() {
		AppiumServerManager.stopAppiumServer();
	}
}
