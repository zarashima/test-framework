package pages;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keywords.*;
import org.openqa.selenium.WebDriver;

import java.awt.datatransfer.Clipboard;

public class BaseScreen {

	@Inject
	protected Context context;

	@Inject
	protected Device device;

	@Inject
	protected Browser browser;

	@Inject
	protected MobileActions mobileActions;

	@Inject
	protected Clipboard clipboard;

	@Inject
	protected AppiumDriver driver;

	@Inject
	public BaseScreen(AppiumDriver driver) {
		this.driver = driver;
	}
}
