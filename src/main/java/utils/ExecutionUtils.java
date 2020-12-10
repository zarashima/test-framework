package utils;

public class ExecutionUtils {

	private ExecutionUtils() {
	}

	public static synchronized String getParameter(String name) {
		String value = System.getProperty(name);
		if (value == null)
			throw new IllegalArgumentException(name + " is not a parameter!");
		if (value.isEmpty())
			throw new IllegalArgumentException(name + " is empty!");
		return value;
	}

	public static synchronized void setParameter(String key, String name) {
		if (key == null)
			throw new IllegalArgumentException(name + " is not a parameter!");
		if (key.isEmpty())
			throw new IllegalArgumentException(name + " is empty!");
		System.setProperty(key, name);
	}

	public static synchronized Object getEnv(String envName) {
		return System.getenv(envName);
	}
}
