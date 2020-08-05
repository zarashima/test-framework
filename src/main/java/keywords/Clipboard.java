package keywords;

import com.google.inject.Inject;
import ensure.Wait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.clipboard.HasClipboard;

public class Clipboard {

	@Inject
	AppiumDriver driver;

	@Inject
	Device device;

	@Inject
	MobileActions mobileActions;

	@Inject
	Wait wait;

	@Inject
	public Clipboard(AppiumDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public String getClipboardText() {
		return ((HasClipboard) driver).getClipboardText();
	}

	public void setClipboardText(String clipboard) {
		((HasClipboard) driver).setClipboardText(clipboard);
	}
}
