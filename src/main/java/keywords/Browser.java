package keywords;

import com.google.inject.Inject;
import ensure.Wait;
import helper.StringConstants;
import io.appium.java_client.AppiumDriver;

public class Browser {

	@Inject
	AppiumDriver driver;

	@Inject
	Context context;

	@Inject
	Application application;

	@Inject
	Wait wait;

	@Inject
	public Browser(AppiumDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public void startBrowser() throws InterruptedException {
		application.startBrowserApplication();
		boolean isWebViewAvailable;
		do {
			isWebViewAvailable = context.getAllContexts()
					.stream()
					.anyMatch(s ->
							!s.contains("NATIVE_APP"));
			if (isWebViewAvailable)
				context.switchContext(context.getWebContext());
			Thread.sleep(1000);
		} while (!isWebViewAvailable);
	}
}
