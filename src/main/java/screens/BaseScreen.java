package screens;

import com.google.inject.Inject;
import keywords.mobile.Performance;
import keywords.mobile.Clipboard;
import keywords.mobile.Context;
import keywords.mobile.DeviceKW;
import keywords.mobile.MobileActions;
import keywords.web.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseScreen {

	@Inject
	protected Context context;

	@Inject
	protected DeviceKW deviceKW;

	@Inject
	protected Browser browserKw;

	@Inject
	protected MobileActions mobileActions;

	@Inject
	protected Clipboard clipboardKw;

	@Inject
	protected Performance performanceKw;

	@Inject
	protected RemoteWebDriver driver;

	@Inject
	public BaseScreen(RemoteWebDriver driver) {
		this.driver = driver;
	}
}
