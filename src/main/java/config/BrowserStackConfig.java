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
public class BrowserStackConfig {
	private static final String bsPrefix = "bs.";
	private static final Map<String, Object> browserStacksNode;

	private BrowserStackConfig(){}

	static {
		browserStacksNode = YamlUtils.getInstance().getNodeFromKey(Platform.BROWSERSTACKS.platformName);
	}

	public static boolean isEnabled() {
		return Optional.ofNullable(System.getProperty(bsPrefix + "enabled"))
				.orElse((String) browserStacksNode.get("enabled")).equals("true");
	}

	public static List<Map<String, String>> getDevices() {
		Gson gson = new Gson();
		Type resultType = new TypeToken<List<Map<String, String>>>(){}.getType();
		List<Map<String, String>> results = gson.fromJson(System.getProperty("bs.devices"), resultType);
		return (Optional.ofNullable(results).orElse((List<Map<String, String>>)
				browserStacksNode.get("devices")));
	}

	public static Map<String, Object> getSettings() {
		return browserStacksNode.entrySet()
				.stream().filter(s -> !s.getKey().equals("devices"))
				.collect(Collectors.toMap(Map.Entry::getKey,
						s -> Optional.ofNullable(System.getProperty(bsPrefix + s.getKey())).orElse((String) s.getValue())));
	}

	public static String getUserName() {
		return Optional.ofNullable(System.getProperty("username"))
				.orElse((String) browserStacksNode.get("username"));
	}

	public static String getAccessKey() {
		return Optional.ofNullable(System.getProperty("accessKey"))
				.orElse((String) browserStacksNode.get("accessKey"));
	}
}
