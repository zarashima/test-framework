package keywords;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;

import java.util.ArrayList;
import java.util.Set;

public class Context {

	@Inject
	AppiumDriver driver;

	@Inject
	public Context(AppiumDriver driver) {
		this.driver = driver;
	}

	public Set<String> getAllContexts() {
		return driver.getContextHandles();
	}

	public String getWebContext() {
		ArrayList<String> contexts = new ArrayList<>(driver.getContextHandles());
		for (String context : contexts) {
			if (context.contains("WEBVIEW"))
				return context;
		}
		return null;
	}

	public void switchContext(String contextName) {
		driver.context(contextName);
	}

	public boolean isWebViewAvailable() {
		return getAllContexts()
				.stream()
				.anyMatch(s -> s.contains("WEBVIEW"));
	}
}
