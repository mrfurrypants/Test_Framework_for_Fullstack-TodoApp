Feature: Projects Management

  Background:
    Given The user is logged in and on the 'Projects' page

  Scenario: Creating a new project
    When The user clicks 'New Project' button
    And The user enters Project Name
    And The user enters Project Description
    And The user clicks 'Save' button
    Then The user should see the created project

  Scenario: Deleting created project
    When The user clicks 'New Project' button
    And The user enters Project Name
    And The user enters Project Description
    And The user clicks 'Save' button
    And The user clicks 'Delete' button and confirms deletion
    Then The user should see the project removed