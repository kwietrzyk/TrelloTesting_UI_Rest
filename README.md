# Test framework for Trello application 
- **GUI tests in _Selenide_ (Object Page Pattern, Allure reports)**
- **REST API tests in _Rest Assured_ (CRUD, POJO, Requests Supplier)**

## How to use this repository:
- **install Java 17 (mandatory)**
- **create account on https://trello.com/ (mandatory)**
- generate **API key** and **token** for your Trello account [(how-to)](https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/) **(mandatory)**
- insert your credentials to **configuration.properties** file (replace 'xxx') **(mandatory)**
- install **Allure** if you want to get html reports
- Selenide uses **Google Chrome** by default and this framework is used in this project

## What can you learn from this project?
- Design patterns in real use
- How can you improve classic Selenium project with Selenide and Rest Assured
  - no WebDriver configuration needed - default Google Chrome setting
  - CSS as default locator - short syntax
  - no PageFactory action (like @FindBy or WebDriver passing) needed  - auto generated
  - no Allure annotations needed (like @Step with description) - auto generated
  - simple conditions for elements visibility (and other)
  - many built-in functions for WebElement
  - integration with Rest Api tests to speed up repetitive actions like creating/removing board

## How to generate Allure report:
- run tests (one, several or all)
- run "allure serve" in IDE terminal
- open report from provided link. It is also saved in your local folder

