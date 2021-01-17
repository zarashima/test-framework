package config;

import helper.Platform;
import utils.YamlUtils;

import java.util.Map;

@SuppressWarnings("unchecked")
public class FirefoxConfig {
	public static final Map<String, Object> firefoxNode;

	private FirefoxConfig() { }

	static {
		firefoxNode = YamlUtils.getInstance().getNodeFromKey(Platform.FIREFOX.platformName);
	}

	public static boolean isEnabled() {
		return ((String) firefoxNode.get("enabled")).equals("true");
	}
}
