# Test framework for Trello application 
- **gui tests in _Selenide_ (Page Object Pattern, Allure reports)**
- **REST API tests in _Rest Assured_ (CRUD, Request Object Pattern, POJO, Allure reports)**

## How to use this repository:
- **install Java 17 (mandatory)**
- **create account on https://trello.com/ (mandatory)**
- generate **API key** and **token** for your Trello account [(how-to)](https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/) **(mandatory)**
- insert your credentials to **configuration.properties** file (replace 'xxx') **(mandatory)**
- install **Allure** if you want to get html reports
- Selenide uses **Google Chrome** by default and this browser is used in this project

## What can you learn from this project?
- Design patterns in real use
- How can you improve classic Selenium project with Selenide and Rest Assured
  - no WebDriver configuration needed - default Google Chrome setting
  - CSS as default locator - short syntax
  - no PageFactory action (like @FindBy or WebDriver passing) needed  - auto generated
  - no Allure annotations needed (like @Step with description) - auto generated
  - simple conditions for elements visibility (and other)
  - many built-in functions for WebElement, also assertions ".should..."
  - integration with Rest Api tests to speed up repetitive actions like creating/removing board

## How to run tests by Maven
- your test classes names should match one these patterns: `*Test`, `Test*`
- all tests should have jUnit annotation `@Test` or `@RepeatedTest`
- in the terminal run `mvn clean test`
- if you want to run only tests with specific tag annotation run: `mvn clean test "-Dgroups=tag1,tag2,tag3"`
- you can mark every test with several tags and combine your set of running tests:  
  `mvn clean test -Dgroups=tag1 -DexcludedGroups=tag2`

## How to generate Allure report:
- run tests (one, several or all)
- run `mvn allure:serve` in terminal to generate online report
- run `mvn allure:report` in terminal to generate report in folder
- open report via link (report is also saved in your local folder)

Selenide tests have built-in Allure steps support, but you have to write annotations @Step() manually for REST API test.

![Allure_report](https://github.com/kwietrzyk/TrelloTesting_UI_Rest/assets/25009144/fd012e58-6b86-455c-a1e4-5dae4019ac48)

