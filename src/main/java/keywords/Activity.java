package keywords;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;

public class Activity {
	@Inject
	AppiumDriver driver;

	@Inject
	MobileActions mobileActions;

	@Inject
	public Activity(AppiumDriver driver) {
		this.driver = driver;
	}

	public void startActivity() {
	}

}
