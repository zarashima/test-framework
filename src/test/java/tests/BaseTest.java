package tests;

import com.epam.reportportal.service.tree.ItemTreeReporter;
import com.epam.reportportal.service.tree.TestItemTree;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.testng.util.ItemTreeUtils;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.google.inject.Guice;
import com.google.inject.Injector;
import drivers.Device;
import drivers.DriverFactory;
import drivers.DriverManager;
import extentreports.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import keywords.*;
import modules.DriverModule;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.collections.Sets;
import pages.HomeScreen;
import reportportal.SessionContext;
import server.AppiumServerManager;

import java.util.Calendar;
import java.util.Set;

import static com.epam.reportportal.testng.TestNGService.ITEM_TREE;

public class BaseTest {

	protected AppiumDriver<?> driver;
	protected Browser browserKeywords;
	protected Element elementKeywords;
	protected Verification verificationKeywords;
	protected Clipboard clipboardKeywords;
	protected HomeScreen homeScreen;
	protected Logs logsKeyword;
	protected Application applicationKeywords;
	protected Device device;

	@BeforeSuite
	public void beforeSuite() {
		AppiumServerManager.startAppiumServer();
	}

	@BeforeTest
	@Parameters({"platformName", "deviceId", "deviceName", "deviceVersion"})
	public void beforeTest(String platformName, String deviceId, String deviceName, String deviceVersion) {
		Injector injector = Guice.createInjector(new DriverModule());
		device = new Device(platformName, deviceName, deviceId, deviceVersion);
		driver = DriverFactory.createInstance(device);
		DriverManager.setDriver(driver);
		homeScreen = injector.getInstance(HomeScreen.class);
		browserKeywords = injector.getInstance(Browser.class);
		elementKeywords = injector.getInstance(Element.class);
		logsKeyword = injector.getInstance(Logs.class);
		verificationKeywords = injector.getInstance(Verification.class);
		applicationKeywords = injector.getInstance(Application.class);
		clipboardKeywords = injector.getInstance(Clipboard.class);
	}

	@AfterMethod()
	public void afterMethod(ITestResult testResult) {
		if (SessionContext.getRpEnable()) {
			ItemTreeUtils.retrieveLeaf(testResult, ITEM_TREE).ifPresent(testResultLeaf -> sendFinishRequest(testResultLeaf, testResult));
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

	private void sendFinishRequest(TestItemTree.TestItemLeaf testResultLeaf, ITestResult testResult) {
		FinishTestItemRQ finishTestItemRQ = new FinishTestItemRQ();
		Set<ItemAttributesRQ> testItemAttributes =  Sets.newHashSet(new ItemAttributesRQ("device",
				testResult.getTestContext().getCurrentXmlTest().getParameter("deviceName")));
		testItemAttributes.add(new ItemAttributesRQ("session_id", DriverManager.getSessionID()));
		testItemAttributes.add(new ItemAttributesRQ("platform", DriverManager.getPlatformName()));
		testItemAttributes.add(new ItemAttributesRQ("version", DriverManager.getVersion()));
		finishTestItemRQ.setAttributes(testItemAttributes);
		finishTestItemRQ.setStatus(testResult.isSuccess() ? "PASSED" : "FAILED");
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		ItemTreeReporter.finishItem(TestNGService.getReportPortal().getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testResultLeaf)
				.cache()
				.blockingGet();
	}
}
