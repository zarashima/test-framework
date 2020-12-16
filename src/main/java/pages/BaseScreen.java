package pages;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import keywords.*;

public class BaseScreen {

	@Inject
	protected Context context;

	@Inject
	protected Device device;

	@Inject
	protected Browser browserKw;

	@Inject
	protected MobileActions mobileActions;

	@Inject
	protected Clipboard clipboardKw;

	@Inject
	protected Performance performanceKw;

	@Inject
	protected AppiumDriver driver;

	@Inject
	public BaseScreen(AppiumDriver driver) {
		this.driver = driver;
	}
}
