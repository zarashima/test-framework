package drivers;

public class Device {
	private String platformName;
	private String deviceName;
	private String deviceId;
	private String deviceVersion;

	public Device(String platformName, String deviceName, String deviceId, String deviceVersion) {
		this.platformName = platformName;
		this.deviceName = deviceName;
		this.deviceId = deviceId;
		this.deviceVersion = deviceVersion;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

}
