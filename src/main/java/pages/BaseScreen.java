package pages;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import keywords.Browser;
import keywords.Context;
import keywords.Device;
import keywords.MobileActions;
import keywords.Clipboard;

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
