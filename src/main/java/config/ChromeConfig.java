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
public class ChromeConfig {
	private static final Map<String, Object> chromeNode;

	private ChromeConfig() { }

	static {
		chromeNode = YamlUtils.getInstance().getNodeFromKey(Platform.CHROME.platformName);
	}

	public static boolean isEnabled() {
		return ((String) chromeNode.get("enabled")).equals("true");
	}
}
