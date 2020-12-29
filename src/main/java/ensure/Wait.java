package ensure;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtils;
import utils.PropertyUtils;
import utils.WebElementUtils;

import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.await;

public class Wait {

	final WebDriverWait webDriverWait;
	private final String waitPrefix = "Wait for ";

	@Inject
	AppiumDriver driver;

	@Inject
	public Wait(AppiumDriver driver) {
		this.webDriverWait = new WebDriverWait(driver, PropertyUtils.getInstance().getTimeout());
		this.driver = driver;
	}

	public void waitForElementDisplay(WebElement element) {
		LogUtils.info(waitPrefix + WebElementUtils.getElementXpathInfo(element) + " to display");
		await().atMost(Duration.ofSeconds(PropertyUtils.getInstance().getTimeout()))
				.until(element::isDisplayed);
	}

	public void waitForElementEnabled(WebElement element) {
		LogUtils.info(waitPrefix + WebElementUtils.getElementXpathInfo(element) + " to be enabled");
		await().atMost(Duration.ofSeconds(PropertyUtils.getInstance().getTimeout()))
				.until(element::isEnabled);
	}

	public void waitForElementClickable(WebElement element) {
		LogUtils.info(waitPrefix + WebElementUtils.getElementXpathInfo(element) + " to be clickable");
		await().atMost(Duration.ofSeconds(PropertyUtils.getInstance().getTimeout())).until(() -> {
			element.click();
			return true;
		});
	}

	public void waitForPageLoad() {
		LogUtils.info("Wait for page load");
		await().atMost(Duration.ofSeconds(PropertyUtils.getInstance().getTimeout())).until(() -> {
			return ((JavascriptExecutor)this.driver).executeScript("return document.readyState").equals("complete");
		});
	}

	public void waitUntilDisplayIsNone(WebElement element) {
		LogUtils.info("Wait until display is none");
		webDriverWait.until(ExpectedConditions.attributeContains(element, "style", "display: none;"));
	}
}
