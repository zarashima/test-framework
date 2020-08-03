package utils;

import freemarker.template.Template;

public class SuiteTemplate {

	private Template template;
	private Object templateData;

	public SuiteTemplate(Template template, Object templateData) {
		this.template = template;
		this.templateData = templateData;
	}

	public Template getSuiteTemplate() {
		return template;
	}

	public void setSuiteTemplate(Template template) {
		this.template = template;
	}

	public Object getTemplateData() {
		return templateData;
	}

	public void setTemplateData(Object templateData) {
		this.templateData = templateData;
	}

}
