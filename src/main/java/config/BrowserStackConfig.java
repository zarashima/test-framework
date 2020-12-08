package config;

import helper.Platform;
import utils.YamlUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class BrowserStackConfig {

	private BrowserStackConfig(){}

	private static final Map<String, Object> browserStacksNode;

	static {
		browserStacksNode = YamlUtils.getInstance().getNodeFromKey(Platform.BROWSERSTACKS.platformName);
	}

	public static boolean isEnabled() {
		return Optional.ofNullable(System.getProperty("enabled"))
				.orElse((String) browserStacksNode.get("enabled")).equals("true");
	}

	public static List<Map<String, String>> getDevices() {
		return (List<Map<String, String>>) browserStacksNode.get("devices");
	}

	public static Map<String, Object> getSettings() {
		return browserStacksNode.entrySet()
				.stream().filter(s -> !s.getKey().equals("devices"))
				.collect(Collectors.toMap(Map.Entry::getKey,
						s -> Optional.ofNullable(System.getProperty(s.getKey())).orElse((String) s.getValue())));
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
