package screens;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen extends BaseScreen {

	@Inject
	public HomeScreen(RemoteWebDriver driver) {
		super(driver);
		if (driver instanceof AppiumDriver)
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		else
			PageFactory.initElements(driver, this);
	}
}
