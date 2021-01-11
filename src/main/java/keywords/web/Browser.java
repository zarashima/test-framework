package keywords.web;

import com.google.inject.Inject;
import ensure.Wait;
import keywords.mobile.Application;
import keywords.mobile.Context;
import keywords.mobile.DeviceKW;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {

	@Inject
	RemoteWebDriver driver;

	@Inject
	Context context;

	@Inject
	Application application;

	@Inject
	Wait wait;

	@Inject
	DeviceKW deviceKw;

	@Inject
	public Browser(RemoteWebDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public void startBrowser() {
		application.startBrowser();
		boolean isWebViewAvailable;
		do {
			isWebViewAvailable = context.getAllContexts()
					.stream()
					.anyMatch(s -> !s.contains("NATIVE_APP"));
			if (isWebViewAvailable)
				context.switchContext(application.getWebContext());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(isWebViewAvailable);
		} while (!isWebViewAvailable);
	}

	public void goToURL(String url) {
		if (deviceKw.isMobile())
			startBrowser();
		driver.get(url);
	}
}
