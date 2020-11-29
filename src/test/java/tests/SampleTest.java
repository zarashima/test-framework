package tests;

import annotations.TestInfo;
import org.testng.annotations.Test;


@TestInfo(module = "signin",
		priority = TestInfo.Priority.MEDIUM,
		createdBy = "vinh.nguyen")
public class SampleTest extends BaseTest {
	@Test(description = "Verify invalid message is displayed when using invalid email and password")
	public void verifySignIn_invalidEmailPassword_shouldPromptInvalidMessage() {
		System.out.println("Sample Test");
		System.out.println(applicationKeywords.isAppInstalled(applicationKeywords.getCurrentPackage()));
	}
}
