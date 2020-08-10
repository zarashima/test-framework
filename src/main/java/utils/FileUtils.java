package utils;

import java.nio.file.Paths;

public class FileUtils {

	private FileUtils() { }

	public static synchronized String getTestSuiteFolder() {
		return ExecutionUtils.getParameter("user.dir") + "/src/test/resources/suites/";
	}

	public static synchronized String getCapabilitiesFilePath() {
		return String.valueOf(Paths.get("src/test/resources/capabilities.yml"));
	}
}
