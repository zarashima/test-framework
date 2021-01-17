package config;

import helper.Platform;
import utils.YamlUtils;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RemoteConfig {

	private RemoteConfig() {}

	public static final Map<String, Object> remoteNode;
	private static final String remotePrefix = "remote.";

	static {
		remoteNode = YamlUtils.getInstance().getNodeFromKey(Platform.REMOTE.platformName);
	}

	public static boolean isEnabled() {
		return Optional.ofNullable(System.getProperty(remotePrefix + "enabled"))
				.orElse((String) remoteNode.get("enabled")).equals("true");
	}

	public static Map<String, Object> getSettings() {
		return remoteNode.entrySet().stream()
				.collect(Collectors
				.toMap(Map.Entry::getKey, s -> Optional.ofNullable(System.getProperty(remotePrefix + s.getKey())).orElse((String) s.getValue())));
	}

}
