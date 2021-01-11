package keywords.mobile;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.ElementOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class MobileActions {

	@Inject
	RemoteWebDriver driver;

	@Inject
	public MobileActions(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public void tapOnElement(MobileElement element) {
		new TouchAction((AppiumDriver) driver)
				.tap(tapOptions().withElement(element(element)))
				.waitAction(waitOptions(ofMillis(250))).perform();
	}

	public void pressByElement(MobileElement element, long seconds) {
		new TouchAction<>((AppiumDriver) driver)
				.press(element(element))
				.waitAction(waitOptions(ofSeconds(seconds)))
				.release().perform();
	}

	public void horizonSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
		Dimension windowSize = driver.manage().window().getSize();
		int anchor = (int) (windowSize.height * anchorPercentage);
		int startPoint = (int) (windowSize.width * startPercentage);
		int endPoint = (int) (windowSize.width * endPercentage);

		new TouchAction<>((AppiumDriver) driver)
				.press(point(anchor, startPoint))
				.waitAction(waitOptions(ofMillis(1000)))
				.moveTo(point(anchor, endPoint))
				.release().perform();
	}

	public void executeScript(String script, Object... objects) {
		driver.executeScript(script, objects);
	}
}
