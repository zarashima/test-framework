package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	private static PropertyUtils instance;
	private final Properties props = new Properties();
	private Integer timeout;
	private String autHomePage;
	private String androidApkPath;
	private String iOSAppPath;

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
		androidApkPath = props.getProperty("android.apk");
		iOSAppPath = props.getProperty("iOS.ipa");
	}

	public Integer getTimeout() {
		return timeout;
	}

	public String getAndroidApkPath() {
		return androidApkPath;
	}

	public String getiOSAppPath() {
		return iOSAppPath;
	}

}
