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
import keywords.mobile.*;
import keywords.common.Element;
import keywords.common.Verification;
import keywords.web.Browser;
import modules.DriverModule;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.collections.Sets;
import reportportal.SessionContext;
import server.AppiumServerManager;

import java.util.Calendar;
import java.util.Set;

import static com.epam.reportportal.testng.TestNGService.ITEM_TREE;

@TestPropertySource("classpath:env/${env}.properties")
@ContextConfiguration("classpath*:application-context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {

	protected RemoteWebDriver driver;
	protected Browser browserKw;
	protected Element elementKw;
	public Verification verificationKw;
	protected Clipboard clipboardKw;
	public Application applicationKw;
	protected Performance performanceKw;
	protected Scroll scrollKw;
	protected Device device;
	protected DeviceKW deviceKw;

	@BeforeSuite
	public void beforeSuite() {
		if (System.getProperty("local").equals("true"))
			AppiumServerManager.startAppiumServer();
	}

	@BeforeTest
	@Parameters({"platformName", "deviceId", "deviceName", "deviceVersion"})
	public void beforeTest(String platformName, String deviceId, String deviceName, String deviceVersion) {
		Injector injector = Guice.createInjector(new DriverModule());
		device = new Device(platformName, deviceName, deviceId, deviceVersion);
		driver = DriverFactory.createInstance(device);
		DriverManager.setDriver(driver);
		deviceKw = injector.getInstance(DeviceKW.class);
		browserKw = injector.getInstance(Browser.class);
		elementKw = injector.getInstance(Element.class);
		verificationKw = injector.getInstance(Verification.class);
		applicationKw = injector.getInstance(Application.class);
		clipboardKw = injector.getInstance(Clipboard.class);
		performanceKw = injector.getInstance(Performance.class);
		scrollKw = injector.getInstance(Scroll.class);
	}

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
		Set<ItemAttributesRQ> testItemAttributes = Sets.newHashSet(new ItemAttributesRQ("deviceKW",
				testResult.getTestContext().getCurrentXmlTest().getParameter("deviceName")));
		testItemAttributes.add(new ItemAttributesRQ("session_id", DriverManager.getSessionID()));
		testItemAttributes.add(new ItemAttributesRQ("platform", deviceKw.getPlatformName()));
		testItemAttributes.add(new ItemAttributesRQ("version", deviceKw.getVersion()));
		finishTestItemRQ.setAttributes(testItemAttributes);
		finishTestItemRQ.setStatus(testResult.isSuccess() ? "PASSED" : "FAILED");
		finishTestItemRQ.setEndTime(Calendar.getInstance().getTime());
		ItemTreeReporter.finishItem(TestNGService.getReportPortal().getClient(), finishTestItemRQ, ITEM_TREE.getLaunchId(), testResultLeaf)
				.cache()
				.blockingGet();
	}
}
