Feature: Authentication of user

  Background:
    Given there is a Users server

  Scenario: authentication a user
    Given I have an admin credential
    When I POST it to the /authentication endpoint
    Then I receive a 200 status code

  Scenario: authentication a user
    Given I have credential with wrong password
    When I POST it to the /authentication endpoint
    Then I receive a 401 status code

  Scenario: authentication a user
    Given I have credential with no store email
    When I POST it to the /authentication endpoint
    Then I receive a 404 status code