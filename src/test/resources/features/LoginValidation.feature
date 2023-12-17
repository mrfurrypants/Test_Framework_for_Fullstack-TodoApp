Feature: Login Validation

  Background: The user is on the 'Login' page
    Given The user navigated to the 'Main' page
    When The user clicks 'Get started' button

  Scenario: Login with valid credentials
    When The user enters a valid email and password
    And The user clicks the 'Login' button
    Then The user should be redirected to the 'Tasks' page

  Scenario: Login with invalid credentials
    When The user enters an invalid email "admin1@gma.com" and password "dfgh12345"
    And The user clicks the 'Login' button
    Then The user should see the pop-up validation message