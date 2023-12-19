## Project Overview

This JAVA based project is a testing framework for [Fullstack-TodoApp](https://github.com/naputami/Fullstack-TodoApp).

- E2E Testing: Standard JUnit 5 Selenide UI tests covering the main app functionality.
- Page Object Model Design Pattern: Each web page is represented by separate Page Class which encapsulates WebElements and operations on them.
- BDD (Behavior-driven development): Cucumber framework layer is added on top of existing JUnit 5 Selenide UI tests by creating separate Cucumber test runner class for running Cucumber feature files.
- Test environment preparation: mechanism written in REST-assured to achieve an empty application state by interaction with API before each subsequent UI test.
- JWT token-based authentication handling: tokens retrieved using REST-Assured are passed to a browser’s local storage to speed up the login process and accelerate UI tests.
- DDT (Data-driven testing): input data from XLSX file for parametrized test by using POIJI library.
- Configuration Management: loading and managing log-in data in configuration files by using the Typesafe Config library.
- Test Reporting: Allure Report is integrated with JUnit 5, Selenide and Cucumber project modules.

## Table of Contents

1. [Essential Modules](#essential-modules)
2. [Prerequisites](#prerequisites)
3. [Installation & Setup](#installation-&-setup)
4. [Running & Reporting Tests](#running-&-reporting-tests)

## Essential Modules

- Selenide: wrapper for Selenium WebDriver (browser automation framework for performing web tests).
- Maven: build tool to resolve dependencies.
- JUnit 5: testing framework providing annotations, assertions and test runners to specify, arrange and execute tests.
- Cucumber: A BDD (Behavior-Driven Development) framework that uses Gherkin language to describe test scenarios in plain text.
- REST-Assured: Java library for testing and validating RESTful APIs.
- Allure Report: test automation report tool to create fancy and clear HTML-based reports.

## Prerequisites

Reqiured components to be installed on your machine:

- Java JDK 19 or [higher](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)
- Apache Maven 3.6.0 or [higher](https://maven.apache.org/download.cgi)
- [Chrome browser](https://www.google.com/chrome/)
- [Git](https://git-scm.com/downloads)

## Installation & Setup

1. Follow the installation instructions of [Fullstack-TodoApp](https://github.com/naputami/Fullstack-TodoApp?tab=readme-ov-file#how-to-run-this-app)

\* Small clarification to the installation process!

Create ".env" file in the root directory:
```
#Set the value for configuration variable in the following format: "postgresql+db_adapter://postgres_user:postgres_password@host:port/postgres_db"
SQLALCHEMY_DATABASE_URI=postgresql+psycopg2://postgres:password_defined_during_postgres_installation@postgres:5432/postgres
#As Docker provides a DNS resolver for containers, hostname "postgres" must remain unchanged because it serves as name for db service in docker-compose.yml file
JWT_SECRET_KEY=any_word
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password_defined_during_postgres_installation
POSTGRES_DB=postgres
```

2. Clone the repository using the following command:
```
git clone https://github.com/mrfurrypants/Test_Framework_for_Fullstack-TodoApp.git
```

## Running & Reporting Tests

Navigate to the root project directory in the terminal and execute the following Maven commands:

- to run all tests:
```
mvn clean test
```
- to run specific test class:
```
mvn clean test -Dtest=CucumberUITestsRunner
```
```
mvn clean test -Dtest=MainFunctionalityTests
```
```
mvn clean test -Dtest=DataDrivenTests
```
- to create Allure report after tests passes or failed:
```
mvn allure:serve
```
