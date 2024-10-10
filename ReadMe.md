Amazon-Test-Automation Framework
This repository contains an automation framework designed to test the functionality of the Amazon application using the
Page Object Model (POM). The framework integrates various tools such as TestNG for test execution, TestRail for managing
test results, and Extent Reports for reporting.

Key Features:

Java + TestNG: The framework is built using Java with TestNG for test execution, making it easy to create and manage
test cases.

TestRail Integration: Test results are automatically pushed to TestRail for comprehensive test case management.
Page Object Model (POM): All web elements (locators) are stored in a property file, and page actions are defined within
page classes to ensure a modular and maintainable structure.

Log File Management: Logs are generated to monitor test execution and maintain a history of activities, errors, and
issues encountered during the tests.

Extent Reports: Beautiful, detailed test reports are generated after every test execution, providing insights into test
results, pass/fail status, and error details.
Properties File for Configuration: All locators, URLs, and environment configurations are stored in a .properties file
for easy maintenance and flexibility.

Prerequisites:
To use this framework, ensure the following software and tools are installed:

Java (JDK 8 or higher): The framework is built using Java, so JDK is necessary for compiling and running the tests.

Maven: For managing dependencies and running tests.

TestNG: Testing framework used for managing test execution.

TestRail: For pushing test results and managing test cases (ensure TestRail credentials and API configurations are
provided in the property file).

Selenium WebDriver: The automation tool used for interacting with the Amazon website.
