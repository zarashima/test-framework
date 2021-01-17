package config;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import helper.Platform;
import utils.YamlUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ChromeConfig {
	private ChromeConfig() {}

	public static final Map<String, Object> chromeNode;
	private static final String chromePrefix = "chrome.";

	static {
		chromeNode = YamlUtils.getInstance().getNodeFromKey(Platform.CHROME.platformName);
	}

	public static boolean isEnabled() {
		return Optional.ofNullable(System.getProperty(chromePrefix + "enabled"))
				.orElse((String) chromeNode.get("enabled")).equals("true");
	}

	public static Map<String, Object> getSettings() {
		return chromeNode.entrySet()
				.stream()
				.filter(s -> !s.getKey().equals("arguments"))
				.collect(Collectors.toMap(Map.Entry::getKey, s -> Optional.ofNullable(System.getProperty(chromePrefix + s.getKey())).orElse((String) s.getValue())));
	}

	public static List<String> getArguments() {
		Gson gson = new Gson();
		Type resultType = new TypeToken<List<String>>() {}.getType();
		List<String> results = gson.fromJson(System.getProperty("chrome.arguments"), resultType);
		return (Optional.ofNullable(results)
				.orElse((List<String>) chromeNode.get("arguments")));
	}

}
