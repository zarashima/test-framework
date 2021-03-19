package tests;

import annotations.TestInfo;
import businessintents.Home;
import data.Token;
import data.TokenFactory;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;
import utils.LogUtils;

@TestInfo(module = "sample",
		priority = TestInfo.Priority.MEDIUM,
		createdBy = "vinh.nguyen")

public class SampleTest extends BaseTest {
	@Value("${url}")
	private String url;

	@Test(description = "Verify application is installed")
	public void verifyApplicationIsInstalled() {
		LogUtils.info(url);
		Token token = TokenFactory.createPkiToken();
		Home.dontSetTiAlert();
		Home.provisionToken(token.getTokenSerial(), token.getaPin());
	}
}
