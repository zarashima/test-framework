package keywords;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.LogUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class Verification {

	protected WebElement element;

	@Inject
	AppiumDriver driver;

	@Inject
	public Verification(AppiumDriver driver) {
		this.driver = driver;
	}

	public void verifyEqual(Object actual, Object expect) {
		LogUtils.info("Verify " + actual + " equal to " + expect);
		assertThat(actual).isEqualTo(expect);
	}

}
