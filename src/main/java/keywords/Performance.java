package keywords;

import com.google.inject.Inject;
import ensure.Wait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.util.HashMap;
import java.util.List;

public class Performance {

	@Inject
	AppiumDriver driver;

	@Inject
	Device device;

	@Inject
	Element element;

	@Inject
	MobileActions mobileActions;

	@Inject
	Wait wait;

	@Inject
	public Performance(AppiumDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public List<List<Object>> getPerformanceData(String packageName, String dataType, int dataReadTimeout) {
		if (device.isAndroid())
			return ((AndroidDriver) driver).getPerformanceData(packageName, dataType, dataReadTimeout);
		else
			throw new UnsupportedOperationException("iOS is not supported");
	}

	public HashMap<String, Integer> getMemoryInfo(String packageName) {
		List<List<Object>> data = getPerformanceData(packageName, "memoryinfo", 10);
		HashMap<String, Integer> readableData = new HashMap<>();
		for (int i = 0; i < data.get(0).size(); i++) {
			int val;
			if (data.get(1).get(i) == null) {
				val = 0;
			} else {
				val = Integer.parseInt((String) data.get(1).get(i));
			}
			readableData.put((String) data.get(0).get(i), val);
		}
		return readableData;
	}
}
