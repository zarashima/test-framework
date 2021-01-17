package runner;

import com.google.inject.Inject;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import config.BrowserStackConfig;
import config.ChromeConfig;
import config.RemoteConfig;
import drivers.Device;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helper.Platform;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Guice(modules = {
		SuiteModule.class
})

public class TestRunner {

	@Inject
	private SuiteGeneration suiteGeneration;

	@Inject
	private RunnerHelpers runnerHelpers;
	@Inject
	private Map<String, Object> templateData;

	@Test(description = "Wrap up test to execute template test suite with dynamic desired capabilities")
	public void generateTestSuiteXml() throws IOException, TemplateException, DeviceNotFoundException {
		TemplateProcessor templateProcessor = new TemplateProcessor(new File(FileUtils.getTestSuiteFolder()));
		templateProcessor.setUp();
		Template template = templateProcessor.getTemplate(ExecutionUtils.getParameter("testSuiteTemplate") + ".ftl");
		SuiteTemplate suiteTemplate = new SuiteTemplate(template, templateData);

		List<Device> devices = new ArrayList<>();

		// Add local devices
		if (System.getProperty("local").equals("true")) {
			DeviceInfo deviceInfo = new DeviceInfoImpl(DeviceType.ALL);
			if (deviceInfo.anyDeviceConnected()) {
				for (com.testinium.deviceinformation.model.Device device : deviceInfo.getDevices()) {
					devices.add(new Device(device.getDeviceProductName(), device.getModelNumber(),
							device.getUniqueDeviceID(), device.getProductVersion()));
				}
			}
		}

		// Add browsers
		if (ChromeConfig.isEnabled())
			devices.add(new Device("chrome", "", "", ""));

		// Add devices
		if (RemoteConfig.isEnabled())
			devices.add(new Device("remote", "", "", ""));

		// Add remote devices
		if (BrowserStackConfig.isEnabled()) {
			for (Map<String, String> device : BrowserStackConfig.getDevices()) {
				devices.add(new Device(Platform.BROWSERSTACK.name(), device.get("device"), "", device.get("os_version")));
			}
		}

		templateData.put("threadCount", devices.size());
		templateData.put("devices", devices);
		String generatedTestNgSuite = FileUtils.getTestSuiteFolder() + ExecutionUtils.getParameter("testSuiteTemplate") + ".xml";
		Suite suite = new Suite("Test Suite", generatedTestNgSuite);
		suiteGeneration = new SuiteGeneration(suite);
		suiteGeneration.applyTemplate(suiteTemplate);
		runnerHelpers.runSuite(generatedTestNgSuite);
	}
}

