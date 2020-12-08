package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

@SuppressWarnings("unchecked")
public class YamlUtils {

	private static YamlUtils instance;
	private static final Yaml yaml = new Yaml();
	private Map<String, Object> results;

	private YamlUtils(){}

	public static YamlUtils getInstance() {
		if (instance == null) {
			instance = new YamlUtils();
			instance.loadData();
		}
		return instance;
	}

	private void loadData() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(FileUtils.getCapabilitiesFilePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		results = yaml.load(inputStream);
	}

	public Map<String, Object> getNodeFromKey(String key) {
		return (Map<String, Object>) results.get(key);
	}
}
