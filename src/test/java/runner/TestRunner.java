package runner;

import com.google.inject.Inject;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import devices.DeviceInformation;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gherkin.lexer.Fi;
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

	private Suite suite;
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
		DeviceInfo deviceInfo = new DeviceInfoImpl(DeviceType.ALL);
		List<DeviceInformation> devicesInformation = new ArrayList<>();
		if (deviceInfo.anyDeviceConnected()) {
			for (Device device : deviceInfo.getDevices()) {
				devicesInformation.add(new DeviceInformation(device.getDeviceProductName(),
						device.getModelNumber(), device.getUniqueDeviceID(), device.getProductVersion()));
			}
		}
		templateData.put("threadCount", deviceInfo.getDevices().size());
		templateData.put("devicesInformation", devicesInformation);
		String generatedTestNgSuite = FileUtils.getTestSuiteFolder() + ExecutionUtils.getParameter("testSuiteTemplate") + ".xml";
		suite = new Suite("Test Suite", generatedTestNgSuite);
		suiteGeneration = new SuiteGeneration(suite);
		suiteGeneration.applyTemplate(suiteTemplate);
		runnerHelpers.runSuite(generatedTestNgSuite);
	}
}

