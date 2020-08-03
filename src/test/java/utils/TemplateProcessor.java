package utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class TemplateProcessor {
	private final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
	private final File templateDirectory;

	public TemplateProcessor(File templateDirectory) {
		this.templateDirectory = templateDirectory;
	}

	public Configuration setUp() throws IOException {
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setDirectoryForTemplateLoading(templateDirectory);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		return cfg;
	}

	public Template getTemplate(String templateFile) throws IOException {
		return cfg.getTemplate(templateFile);
	}
}
