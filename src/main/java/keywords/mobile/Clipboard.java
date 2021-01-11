package keywords.mobile;

import com.google.inject.Inject;
import ensure.Wait;
import io.appium.java_client.clipboard.HasClipboard;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Clipboard {

	@Inject
	RemoteWebDriver driver;

	@Inject
	DeviceKW deviceKW;

	@Inject
	MobileActions mobileActions;

	@Inject
	Wait wait;

	@Inject
	public Clipboard(RemoteWebDriver driver) {
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
