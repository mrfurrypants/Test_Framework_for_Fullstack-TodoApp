## Project Overview

This JAVA based project is a testing framework for [Fullstack-TodoApp](https://github.com/naputami/Fullstack-TodoApp).

Essential modules: _Apache Maven_, _JUnit 5_, _Selenide_, _Cucumber_, _REST-Assured_, _Allure Report_

Key features of the project:

#### - E2E Testing:
Standard __JUnit 5__ __Selenide__ UI tests covering the main app functionality.
#### - Page Object Model Design Pattern:
Each web page is represented by separate Page Class which encapsulates WebElements and operations on them.
#### - BDD (Behavior-driven development):
__Cucumber__ framework layer is added on top of existing __JUnit 5__ __Selenide__ UI tests by creating separate __Cucumber__ test runner class for __Cucumber__ feature files.
#### - Test environment preparation:
Mechanism written in __REST-Assured__ to achieve an empty application state by interaction with API before each subsequent UI test.
#### - JWT token-based authentication handling:
Tokens retrieved using __REST-Assured__ are passed to a browser’s local storage to speed up the login process and accelerate UI tests.
#### - DDT (Data-driven testing):
Input data from XLSX file for parametrized test by using POIJI library.
#### - Configuration Management:
Loading and managing log-in data in configuration files by using the Typesafe Config library.
#### - Test Reporting:
__Allure Report__ is integrated with __JUnit 5__, __Selenide__ and __Cucumber__ project modules.

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
SQLALCHEMY_DATABASE_URI=postgresql+psycopg2://postgres:defined_password@postgres:5432/postgres
#As Docker provides a DNS resolver for containers, hostname "postgres" must remain unchanged because it serves as name for db service in docker-compose.yml file
JWT_SECRET_KEY=define_any_word
POSTGRES_USER=postgres
POSTGRES_PASSWORD=define_password
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
