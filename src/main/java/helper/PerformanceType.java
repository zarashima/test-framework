package helper;

public enum PerformanceType {
	MEMORY("memoryinfo"),
	CPU("cpuinfo"),
	BATTERY("batteryinfo"),
	NETWORK("networkinfo");

	public String performanceType;

	PerformanceType(String performanceType) {
		this.performanceType = performanceType;
	}
}
