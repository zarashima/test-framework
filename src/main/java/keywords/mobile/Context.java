package keywords.mobile;

import com.google.inject.Inject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Set;

public class Context {

	@Inject
	RemoteWebDriver driver;

	@Inject
	public Context(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public Set<String> getAllContexts() {
		return ((AndroidDriver) driver).getContextHandles();
	}

	public String getWebContext() {
		ArrayList<String> contexts = new ArrayList<>(((AndroidDriver) driver).getContextHandles());
		for (String context : contexts) {
			if (context.contains("WEBVIEW"))
				return context;
		}
		return null;
	}

	public void switchContext(String contextName) {
		((AndroidDriver) driver).context(contextName);
	}

	public boolean isWebViewAvailable() {
		return getAllContexts()
				.stream()
				.anyMatch(s -> s.contains("WEBVIEW"));
	}
}
