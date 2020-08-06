package reportportal;

import helper.StringConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import utils.RestUtils;
import utils.RestUtils.HttpMethod;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class LaunchHandler {

	private static JSONObject requestParams = new JSONObject();

	private LaunchHandler() { }

	static {
		RestUtils.setHeader("Authorization", "Bearer " + SessionContext.getUUID());
		RestUtils.setBaseURI(StringConstants.RP_API_ENDPOINT);
		RestUtils.setContentType(ContentType.JSON);
	}

	public static String getLaunchId() {
		RestUtils.setBasePath(String.format("%s/launch/%s", SessionContext.getProject(), System.getProperty("rp.launch.id")));
		return RestUtils.send(HttpMethod.GET, null).jsonPath().getString("id");
	}

	public static void updateLaunch(List<Map<String, String>> attributes, String description) {
		requestParams.put("attributes", attributes);
		requestParams.put("description", description);
		RestUtils.addJsonBody(requestParams);
		RestUtils.setBasePath(String.format("%s/launch/%s/update", SessionContext.getProject(), getLaunchId()));
		RestUtils.send(HttpMethod.PUT, requestParams);
		requestParams.clear();
	}
}
