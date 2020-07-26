package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	private static PropertyUtils instance;
	private final Properties props = new Properties();
	private Integer timeout;
	private String autHomePage;
	private String androidApk;

	public static PropertyUtils getInstance() {
		if (instance == null) {
			instance = new PropertyUtils();
			instance.loadData();
		}
		return instance;
	}

	private void loadData() {
		String settingsFilePath = "src/test/resources/settings.properties";
		try {
			props.load(new FileInputStream(settingsFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		timeout = Integer.valueOf(props.getProperty("timeout"));
		androidApk = props.getProperty("android.apk");
	}

	public Integer getTimeout() {
		return timeout;
	}

	public String getAndroidApk() {
		return androidApk;
	}

}
