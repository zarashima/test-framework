# Overview
A mobile automation framework to aid in testers for mobile automation

# Features
* Auto detect plugged in real Android/iOS devices and make corresponding executions
* Support BrowserStack integration
* Support both Android and iOS
* Define custom desired capabilities through YML files
* Commonplace used keywords
* Parallel executions
* ReportPortal integration

# Libraries
* Appium
* TestNG
* Extent Report
* Maven
* Freemaker
* [MobileDeviceInfo](https://github.com/Testinium/MobileDeviceInfo)
* ReportPortal
* Snake-YAML

# Prerequistes
* Appium 1.16+
* Maven
* JDK 8+
* adb (Android), XCode(iOS)
* Setup Android and iOS devices

# Usage
## Settings
### Capabilities
You don't need to use identifier capabilities(deviceName, platformName, UDID) for Android and iOS. The framework ultilizes [MobileDeviceInfo](https://github.com/Testinium/MobileDeviceInfo) to get these information and pass in driver's desired capabilities automatically. In the capabilities config file, you should use other capabilities.

Capabilities for both Android, iOS and browserstack are hosted in resources/capabilities.yml file. The structure is:
```
- platformName(android/iOS)
    capabilities
- browserstack
    capabilities
    devices:
        - device:
          version:
```
Android and iOS capabilities compliance follows Appium standards, and so for browserstack. You can find the usage of these desired capabilities on their official website.
The automation test scripts will pick up these capabilities from the file and passed them to driver initialization.

Sample capabilities:
- Android:
```
android:
    app: "app/TheApp-v1.10.0.apk"
    autoGrantPermissions: true
    newCommandTimeout: "3600"
    eventTimings: true
    automationName: "UIAutomator2"
    fullReset: true
    noReset: false
```
- Browserstack:
```
browserstacks:
    enabled: true
    project: "My First Project"
    build: "My First Build"
    name: "Sample Test"
    automationName: "UIAutomator2"
    username: "vinhnguyen5"
    accessKey: "BGXjQCA1xCVc7JKsh2ez"
    browserstack.debug: "true"
    app: "bs://7e34e875691d99ce98ae30ac006dfaaffe3f738a"
    devices:
        - device: "Samsung Galaxy A10"
          os_version: "9.0"
        - device: "Samsung Galaxy S20 Plus"
          os_version: "10.0"
```

### Report Portal
The project used ReportPortal(RP) to centralized execution. It aids in the ability to add meta information regards to the devices under test such as name, platform, session ID after a test method in TestNG is executed (See BaseTest.java for more details)
RP settings are located in resources/reportportal.properties and is RP compliance settings. By default, this integration is disabled.

Update meta information: Once the tests finished, its meta information (at the moment it is device name, version, platform and session ID)

## Test Suite Usage
src/test/resources folder holds Runner TestNG XML file and .ftl files. Runner file execute TestRunner test which will transform .ftl files(FreeMaker) into TestNG XML suite files.
More tests can be added by adding more <test> tags to preserve the parallel executions mechanism and auto retrieve devices information from the capabilities file.

Example: Add another sample test (SampleTest2) to the suite:
```
<?xml version="1.0" encoding="UTF-8"?>
<suite name="Sample Test Suite" parallel="tests" verbose="10" thread-count="${threadCount}">
	<listeners>
		<listener class-name="listeners.TestListener"/>
		<listener class-name="com.epam.reportportal.testng.ReportPortalTestNGListener"/>
	</listeners>
	<#list devices as device>
		<test name="Tests on ${device.deviceName}" preserve-order="true">
			<parameter name="platformName" value="${device.platformName}"/>
			<parameter name="deviceId" value="${device.deviceId}"/>
			<parameter name="deviceName" value="${device.deviceName}"/>
			<parameter name="deviceVersion" value="${device.deviceVersion}"/>
			<classes>
				<class name="tests.SampleTest">
				</class>
			</classes>
		</test>
		<test name="Tests on ${device.deviceName}" preserve-order="true">
			<parameter name="platformName" value="${device.platformName}"/>
			<parameter name="deviceId" value="${device.deviceId}"/>
			<parameter name="deviceName" value="${device.deviceName}"/>
			<parameter name="deviceVersion" value="${device.deviceVersion}"/>
			<classes>
				<class name="tests.SampleTest2">
				</class>
			</classes>
		</test>
	</#list>
</suite>
```

## Execution
The project uses maven for execution. Each capabilities parameters above can be override easily through the command line.

Parameters used in the command:
- suite: Test suite runner name to transform test suite template into executable TestNG compliance XML file. Should always be Runner.
- testSuiteTemplate: Test suite template's name which hold information regard to executed tests.

The execution mechanism supports parameterized parameters through maven command line.

Without override
`mvn clean test -Dsuite=Runner -DtestSuiteTemplate=<SuiteName> -Dlocal=true`

### Local devices
To use local devices , pass `local=true` to maven command, e.g:
`mvn clean test -Dsuite=Runner -DtestSuiteTemplate=<SuiteName> -Dlocal=true`

To override local devices capabilities, pass in capabilities name with its value in the command line. Some examples:
- Change application:
`mvn clean test -Dsuite=Runner -DtestSuiteTemplate=<SuiteName> -Dlocal=true -Dapp="app/TheApp-v2.apk"`

- Change automationName:
`mvn clean test -Dsuite=Runner -DtestSuiteTemplate=<SuiteName> -Dlocal=true -DautomationName="Appium"`

### Browserstack
To override Browserstack(bs) capabilities, add "bs." prefix before the name along with its value in the command line. Some examples:
- Disable browserstack integration

`mvn clean test -Dsuite=Runner -DtestSuiteTemplate=<SuiteName> -Dlocal=true -Dbs.enabled=false`

- Change browserstack project name

`mvn clean test -Dsuite=Runner -DtestSuiteTemplate=<SuiteName> -Dlocal=true -Dbs.project="Automation Project"`

- To change browserstack list, pass the list of devices in JSON format, e.g

`mvn clean test -Dsuite=Runner -DtestSuiteTemplate=SmokeSuite2 -Dlocal=true -Dbs.project="Automation Project" -Dbs.devices="[{'device':'Samsung Galaxy A10', 'os_version':'9.0'}, {'device':'Google Pixel 3', 'os_version':'9.0'}]"`
