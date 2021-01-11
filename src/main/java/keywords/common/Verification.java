package keywords.common;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.LogUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class Verification {

	protected WebElement element;

	@Inject
	RemoteWebDriver driver;

	@Inject
	public Verification(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public void verifyEqual(Object actual, Object expect) {
		LogUtils.info("Verify " + actual + " equal to " + expect);
		assertThat(actual).isEqualTo(expect);
	}

}
