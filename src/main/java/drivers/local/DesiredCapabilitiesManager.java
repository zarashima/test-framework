package drivers.local;

import java.util.HashMap;
import java.util.Map;

public class DesiredCapabilitiesManager {

	private final Map<Object, Object> desiredCapabilities = new HashMap<>();

	private static DesiredCapabilitiesManager instance;

	private DesiredCapabilitiesManager() {
	}

	public static synchronized DesiredCapabilitiesManager getInstance() {
		if (instance == null) {
			instance = new DesiredCapabilitiesManager();
		}
		return instance;
	}

	public void addDesiredCapabilities(Object key, Object value) {
		this.desiredCapabilities.put(key, value);
	}

	public Map<Object, Object> getDesiredCapabilities() {
		return this.desiredCapabilities;
	}

}
