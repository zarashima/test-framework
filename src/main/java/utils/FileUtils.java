package utils;

public class FileUtils {
	public static String getTestSuiteFolder() {
		return ExecutionUtils.getParameter("user.dir") + "/src/test/resources/suites/";
	}
}
