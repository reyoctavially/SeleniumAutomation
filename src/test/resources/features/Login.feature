Feature: Login feature
  @login
  Scenario: Valid login
    Given user is on login page
    When user enters valid username and password
    Then user should be redirected to homepage
