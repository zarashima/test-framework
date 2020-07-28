package pages;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import keywords.Browser;
import keywords.Context;
import keywords.Device;
import keywords.Element;
import org.openqa.selenium.WebDriver;

public class BasePage {

	@Inject
	protected Context context;

	@Inject
	protected Device device;

	@Inject
	protected Browser browser;

	@Inject
	protected AppiumDriver driver;

	@Inject
	public BasePage(AppiumDriver driver) {
		this.driver = driver;
	}
}
