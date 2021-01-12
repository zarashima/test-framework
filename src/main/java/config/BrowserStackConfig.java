package config;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import helper.Platform;
import utils.ExecutionUtils;
import utils.YamlUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class BrowserStackConfig {
	private static final String bsPrefix = "bs.";
	private static final Map<String, Object> browserStackNode;

	private BrowserStackConfig() { }

	static {
		browserStackNode = YamlUtils.getInstance().getNodeFromKey(Platform.BROWSERSTACK.platformName);
	}

	public static boolean isEnabled() {
		return Optional.ofNullable(System.getProperty(bsPrefix + "enabled"))
				.orElse((String) browserStackNode.get("enabled")).equals("true");
	}

	public static List<Map<String, String>> getDevices() {
		Gson gson = new Gson();
		Type resultType = new TypeToken<List<Map<String, String>>>() {}.getType();
		List<Map<String, String>> results = gson.fromJson(System.getProperty("bs.devices"), resultType);
		return (Optional.ofNullable(results).orElse((List<Map<String, String>>)
				browserStackNode.get("devices")));
	}

	public static Map<String, Object> getSettings() {
		return browserStackNode.entrySet()
				.stream().filter(s -> !s.getKey().equals("devices"))
				.collect(Collectors.toMap(Map.Entry::getKey,
						s -> Optional.ofNullable(System.getProperty(bsPrefix + s.getKey())).orElse((String) s.getValue())));
	}

	public static String getUserName() {
		return getFirstFoundValue(ExecutionUtils.getEnv("BROWSERSTACK_USERNAME"),
				System.getProperty("username"), (String) browserStackNode.get("username"));
	}

	public static String getAccessKey() {
		return getFirstFoundValue(ExecutionUtils.getEnv("BROWSERSTACK_ACCESS_KEY"),
				System.getProperty("accessKey"), (String) browserStackNode.get("accessKey"));
	}

	private static String getFirstFoundValue(String... values) {
		Optional<String> result = Optional.empty();
		for (String value : values) {
			result = Optional.ofNullable(value);
			if (result.isPresent())
				return result.get();
		}
		return result.get();
	}
}
