package utils;

import com.google.inject.Inject;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SuiteGeneration {

	private Suite suite;
	private SuiteTemplate suiteTemplate;

	@Inject
	public SuiteGeneration(Suite suite) {
		this.suite = suite;
	}

	public void applyTemplate(SuiteTemplate suiteTemplate) throws IOException, TemplateException {
		Writer suiteFile = new FileWriter(suite.getSuiteFilePath());
		suiteTemplate.getSuiteTemplate().process(suiteTemplate.getTemplateData(), suiteFile);
		suiteFile.flush();
		suiteFile.close();
	}
}
