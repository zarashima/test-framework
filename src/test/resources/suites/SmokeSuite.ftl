<?xml version="1.0" encoding="UTF-8"?>
<suite name="Sample Test Suite" parallel="tests" verbose="10" thread-count="${threadCount}">
	<listeners>
		<listener class-name="listeners.TestListener"/>
		<listener class-name="com.epam.reportportal.testng.ReportPortalTestNGListener"/>
	</listeners>
	<#list devicesInformation as device>
		<test name="Tests on ${device.deviceName}" preserve-order="true">
			<parameter name="platformName" value="${device.platformName}"/>
			<parameter name="deviceId" value="${device.deviceId}"/>
			<parameter name="deviceName" value="${device.deviceName}"/>
			<parameter name="deviceVersion" value="${device.deviceVersion}"/>
			<classes>
				<class name="tests.SignInTest">
				</class>
			</classes>
		</test>
	</#list>
</suite>
