package tests;

import annotations.TestInfo;
import org.testng.annotations.Test;

@TestInfo(module = "sample",
		priority = TestInfo.Priority.MEDIUM,
		createdBy = "vinh.nguyen")
public class SampleTest extends BaseTest {
	@Test(description = "Verify application is installed")
	public void verifyApplicationIsInstalled() {
		verificationKw.verifyEqual(applicationKw.isAppInstalled(
				applicationKw.getCurrentPackage()), true);
		verificationKw.verifyEqual(applicationKw.isAppInstalled(applicationKw.getCurrentPackage()), true);
	}
}

