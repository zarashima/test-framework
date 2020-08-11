# Overview
A mobile automation framework to aid in testers. It provides a basic skeleton for Appium-based framework implementations.

# Features
* Auto detect plugged in real Android/iOS devices and make corresponding executions
* Support both Android and iOS
* Define custom desired capabilities through YML files
* Commonplace used keywords
* Parallel executions
* ReportPortal integration

# References
* Appium
* TestNG
* Extent Report
* Maven
* Freemaker
* [MobileDeviceInfo](https://github.com/Testinium/MobileDeviceInfo)

# Setup
* Install Appium 1.16+
* Install Maven
* Install adb (Android), XCode(iOS).
* Setup Android and iOS devices

# Test Suite structure
Test suite in this project is as .ftl (Freemaker template) format. As seen on SmokeSuite.ftl, there will be a list of placeholder parameters.

# Usage
* Update capabilities in resources/capabilities.yml file. Capabilities must follow Appium desired capabilities conventions
* DO NOT change Runner.xml suite file
* Execute test suite through maven command
```
mvn clean test -Dsuite=Runner -DtestSuiteTemplate=<SuiteName>
# Example: Execute SmokeSuite
mvn clean test -Dsuite=Runner -DtestSuiteTemplate=SmokeSuite
```
