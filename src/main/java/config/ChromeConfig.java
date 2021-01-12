package config;

import helper.Platform;
import utils.YamlUtils;

import java.util.Map;

@SuppressWarnings("unchecked")
public class ChromeConfig {
	private static final Map<String, Object> chromeNode;

	private ChromeConfig() {
	}

	static {
		chromeNode = YamlUtils.getInstance().getNodeFromKey(Platform.CHROME.platformName);
	}

	public static boolean isEnabled() {
		return ((String) chromeNode.get("enabled")).equals("true");
	}
}
