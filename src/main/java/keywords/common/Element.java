package keywords.common;

import com.google.inject.Inject;
import ensure.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.LogUtils;
import utils.WebElementUtils;

public class Element {

	@Inject
	Wait wait;

	@Inject
	RemoteWebDriver driver;

	@Inject
	public Element(RemoteWebDriver driver) {
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
