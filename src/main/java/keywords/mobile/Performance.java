package keywords.mobile;

import com.google.inject.Inject;
import ensure.Wait;
import helper.PerformanceType;
import io.appium.java_client.android.AndroidDriver;
import keywords.common.Element;
import keywords.mobile.DeviceKW;
import keywords.mobile.MobileActions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.List;

public class Performance {

	@Inject
	RemoteWebDriver driver;

	@Inject
	DeviceKW deviceKW;

	@Inject
	Element element;

	@Inject
	MobileActions mobileActions;

	@Inject
	Wait wait;

	@Inject
	public Performance(RemoteWebDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public List<List<Object>> getPerformanceData(String packageName, String dataType, int dataReadTimeout) {
		if (deviceKW.isAndroid())
			return ((AndroidDriver) driver).getPerformanceData(packageName, dataType, dataReadTimeout);
		else
			throw new UnsupportedOperationException("iOS is not supported");
	}

	public HashMap<String, Integer> getSpecificPerformance(String packageName, PerformanceType type) {
		List<List<Object>> data = getPerformanceData(packageName, type.performanceType, 10);
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
