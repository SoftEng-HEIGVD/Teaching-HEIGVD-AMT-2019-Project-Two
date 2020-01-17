Feature: Update the user password

  Background:
    Given there is a Users server

  Scenario: authentication a user
    Given I have an admin credential
    When I POST it to the /users/{email} endpoint
    Then I receive a 200 status code