package listeners;

import appiumdriver.local.DesiredCapabilitiesManager;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlterSuiteListener implements IAlterSuiteListener {

	@Override
	public void alter(List<XmlSuite> list) {
		int threadCount = 0;
		XmlSuite suite = list.get(0);
		List<XmlTest> xmlTests = new ArrayList<>();
		DeviceInfo deviceInfo = new DeviceInfoImpl(DeviceType.ALL);
		String[] platforms = suite.getParameter("browserFlavors").split(",");
		XmlTest xmlTest = new XmlTest(suite);
		Map<String, String> browserName = new HashMap<>();
		for (String platform: platforms) {
			browserName.put("browserName", platform);
			try {
				if (deviceInfo.anyDeviceConnected()) {
					threadCount++;
					List<Device> devices = deviceInfo.getDevices();
					for (Device device : devices) {
						xmlTest.setParameters(browserName);
						DesiredCapabilitiesManager.getInstance().addDesiredCapabilities("platformName", device.getDeviceProductName());
						DesiredCapabilitiesManager.getInstance().addDesiredCapabilities("platformVersion", device.getProductVersion());
						DesiredCapabilitiesManager.getInstance().addDesiredCapabilities("deviceName", device.getModelNumber());
						DesiredCapabilitiesManager.getInstance().addDesiredCapabilities("udid", device.getUniqueDeviceID());
						xmlTest.setName(device.getDeviceProductName() + "_test");
						xmlTests = suite.getTests();
						xmlTests.forEach(s -> {
							xmlTest.setXmlClasses(s.getClasses());
						});
						xmlTests.add(xmlTest);
					}
				}
			} catch (IOException | DeviceNotFoundException e) {
				e.printStackTrace();
			}
		}
		suite.setParallel(XmlSuite.ParallelMode.TESTS);
		suite.setThreadCount(threadCount);
		suite.setTests(xmlTests.stream().skip(1).collect(Collectors.toList()));
	}
}
