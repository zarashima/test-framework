package config;

import helper.Platform;
import utils.YamlUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class BrowserStackConfig {

	private BrowserStackConfig(){}

	private static final Map<String, Object> browserStacksNode;

	static {
		browserStacksNode = YamlUtils.getInstance().getNodeFromKey(Platform.BROWSERSTACKS.platformName);
	}

	public static boolean isEnabled() {
		return (boolean) browserStacksNode.get("enabled");
	}

	public static List<Map<String, String>> getDevices() {
		return (List<Map<String, String>>) browserStacksNode.get("devices");
	}

	public static Map<String, Object> getSettings() {
		return browserStacksNode.entrySet()
				.stream().filter(s -> !s.getKey().equals("devices"))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public static String getUserName() {
		return (String) browserStacksNode.get("username");
	}

	public static String getAccessKey() {
		return (String) browserStacksNode.get("accessKey");
	}

}
