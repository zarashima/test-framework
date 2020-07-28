package keywords;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.ElementOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class MobileActions {

	@Inject
	AppiumDriver driver;

	@Inject
	public MobileActions(AppiumDriver driver) {
		this.driver = driver;
	}

	public void tapOnElement(MobileElement element) {
		new TouchAction<>(driver)
				.tap(tapOptions().withElement(element(element)))
				.waitAction(waitOptions(ofMillis(250))).perform();
	}

	public void pressByElement(MobileElement element, long seconds) {
		new TouchAction<>(driver)
				.press(element(element))
				.waitAction(waitOptions(ofSeconds(seconds)))
				.release().perform();
	}

	public void horizonSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
		Dimension windowSize = driver.manage().window().getSize();
		int anchor = (int) (windowSize.height * anchorPercentage);
		int startPoint = (int) (windowSize.width * startPercentage);
		int endPoint = (int) (windowSize.width * endPercentage);

		new TouchAction<>(driver)
				.press(point(anchor, startPoint))
				.waitAction(waitOptions(ofMillis(1000)))
				.moveTo(point(anchor, endPoint))
				.release().perform();
	}

	public void executeScript(String script, Object... objects) {
		driver.executeScript(script, objects);
	}
}
