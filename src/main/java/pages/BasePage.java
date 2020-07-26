package pages;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import keywords.Browser;
import keywords.Element;
import org.openqa.selenium.WebDriver;

public class BasePage {

	@Inject
	protected Browser browserKeywords;

	@Inject
	protected Element elementKeywords;

	@Inject
	protected AppiumDriver driver;

	@Inject
	public BasePage(AppiumDriver driver) {
		this.driver = driver;
	}
}
