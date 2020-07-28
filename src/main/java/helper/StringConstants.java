package helper;

import reportportal.SessionContext;

public class StringConstants {
	public static final int TIMEOUT = 30;
	public static final int SUCCESS_RESPONSE_CODE = 200;
	public static final String RP_API_ENDPOINT = SessionContext.getEndPoint() + "/api/v1";
	public static final String ANDROID_PLATFORM = "android";
	public static final String IOS_PLATFORM = "iOS";
	public static final String ANDROID_CHROME_BUNDLE_ID = "com.android.chrome";
	public static final String IOS_SAFARI_BUNDLE_ID = "com.apple.mobilesafari";
}
