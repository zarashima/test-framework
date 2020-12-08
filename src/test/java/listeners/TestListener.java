package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import drivers.DriverManager;
import extentreports.ExtentManager;
import extentreports.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.influxdb.dto.Point;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LogUtils;
import utils.UpdateResults;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

	@Override
	public synchronized void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		ExtentTestManager.getTest().info(result.getMethod().getMethodName() + " test executions started");
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS,
				MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		try {
			String base64StringOfScreenshots;
			TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(src);
			base64StringOfScreenshots = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
			ExtentTestManager.getTest().fail("Test Case Failed screenshot: ",
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64StringOfScreenshots).build());
			LogUtils.info("RP_MESSAGE#FILE#{}#{}", src.getAbsoluteFile(), "Screenshot on Failure");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		ExtentManager.getInstance();
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extentreports.ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	private void postTestMethodStatus(ITestResult iTestResult, String status) {
		Point point = Point.measurement("testmethod").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.tag("testclass", iTestResult.getTestClass().getName()).tag("name", iTestResult.getName())
				.tag("description", iTestResult.getMethod().getDescription()).tag("result", status)
				.addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis())).build();
		UpdateResults.post(point);
	}

	private void postTestClassStatus(ITestContext iTestContext) {
		Point point = Point.measurement("testclass").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.tag("name", iTestContext.getAllTestMethods()[0].getTestClass().getName())
				.addField("duration", (iTestContext.getEndDate().getTime() - iTestContext.getStartDate().getTime()))
				.build();
		UpdateResults.post(point);
	}
}
