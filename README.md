📱 Mobile Automation Framework – Appium
🚀 Project Overview

This project is a Mobile Automation Testing Framework built using Appium, Selenium, Java, and TestNG following the Hybrid Framework (POM + Data-Driven) design pattern.

It supports Android automation (Emulator & Real Device) and is designed for scalability, maintainability, and CI/CD integration.

🛠 Tech Stack

Java

Appium

Selenium

TestNG

Maven

Android Emulator / Real Device

🏗 Framework Design
✅ Design Pattern

Page Object Model (POM)

Hybrid Framework (Modular + Data-Driven)

✅ Key Features

Reusable utility methods

Explicit waits for stability

Retry Analyzer for flaky test handling

Centralized Driver Initialization

Configurable capabilities

Clean reporting via TestNG

📂 Project Structure
src/test/java
│
├── pageObjects       → Page classes with locators & methods
├── testCases         → Test classes
├── testBase          → Driver setup & configuration
├── utilities         → Common reusable functions
│
pom.xml               → Maven dependencies
testng.xml            → Test execution configuration

📱 Supported Platforms

Android Emulator

Android Real Device

▶️ How to Run the Tests
Prerequisites

Install Java

Install Maven

Install Android Studio

Setup Android SDK

Install & start Appium Server

Execution Steps

Start Appium Server

Connect Emulator or Real Device

Run testng.xml
OR
Run using Maven:

mvn test

🔄 Handling Flaky Tests

Implemented RetryAnalyzer

Used Explicit Waits

Avoided hardcoded sleeps

Handled dynamic elements

🔁 CI/CD Ready

Framework can be integrated with:

Jenkins

GitHub Actions

Azure DevOps

🧪 Sample Automated Scenarios

Form Submission

Product Selection

End-to-End Order Flow

Hybrid App Context Switching (Native ↔ WebView)

📌 Why This Framework?

Scalable architecture

Easy maintenance

Industry best practices

Interview-ready design

👩‍💻 Author

Josena Thomas
