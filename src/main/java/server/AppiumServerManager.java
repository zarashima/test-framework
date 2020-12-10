package server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerManager {
	private static AppiumDriverLocalService appiumDriverLocalService;

	private AppiumServerManager() {}

	public static void startAppiumServer() {
		AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
		appiumServiceBuilder.usingAnyFreePort()
				.withArgument(GeneralServerFlag.LOG_TIMESTAMP)
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withArgument(GeneralServerFlag.LOG_LEVEL,"info")
				.withArgument(GeneralServerFlag.RELAXED_SECURITY);
		appiumDriverLocalService = appiumServiceBuilder.build();
		appiumDriverLocalService.start();
	}

	public static String getAppiumServerAddress() {
		return appiumDriverLocalService.getUrl().toString();
	}

	public static void stopAppiumServer() {
		appiumDriverLocalService.stop();
	}
}
