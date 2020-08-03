package utils;

public class Suite {

	private String suiteName;
	private String suiteFilePath;

	public Suite(String suiteName, String suiteFilePath) {
		this.suiteName = suiteName;
		this.suiteFilePath = suiteFilePath;
	}

	public static Suite getSuite(String suiteName, String suiteFilePath) {
		return new Suite(suiteName, suiteFilePath);
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public String getSuiteFilePath() {
		return suiteFilePath;
	}

	public void setSuiteFilePath(String suiteFilePath) {
		this.suiteFilePath = suiteFilePath;
	}

}
