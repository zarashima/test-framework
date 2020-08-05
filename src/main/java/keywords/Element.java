package keywords;

import com.google.inject.Inject;
import ensure.Wait;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.LogUtils;
import utils.WebElementUtils;

public class Element {

	@Inject
	Wait wait;

	@Inject
	AppiumDriver driver;

	@Inject
	public Element(AppiumDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public String getText(WebElement element) {
		wait.waitForElementDisplay(element);
		return element.getText();
	}

	public void setText(WebElement element, String inputText) {
		wait.waitForElementDisplay(element);
		element.clear();
		element.sendKeys(inputText);
	}

	public void click(WebElement element) {
		LogUtils.info("Click on element: " + WebElementUtils.getElementXpathInfo(element));
		wait.waitForElementDisplay(element);
		wait.waitForElementClickable(element);
		element.click();
	}

	public boolean verifyElementDisplayed(WebElement element) {
		LogUtils.info("Verify " + WebElementUtils.getElementXpathInfo(element) + " is displayed");
		return element.isDisplayed();
	}

}
