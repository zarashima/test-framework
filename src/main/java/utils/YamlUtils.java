package utils;

import helper.StringConstants;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

@SuppressWarnings("unchecked")
public class YamlUtils {
	static Yaml yaml = new Yaml();

	public static synchronized Map<String,Object> loadCapabilitiesFile() throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(new File(FileUtils.getCapabilitiesFilePath()));
		return yaml.load(inputStream);
	}

	public static synchronized Map<String,Object> loadAndroidCapabilities() throws FileNotFoundException {
		return (Map<String, Object>) loadCapabilitiesFile().get(StringConstants.ANDROID_PLATFORM);
	}

	public static synchronized Map<String,Object> loadiOSCapabilities() throws FileNotFoundException {
		return (Map<String, Object>) loadCapabilitiesFile().get(StringConstants.IOS_PLATFORM);
	}
}
