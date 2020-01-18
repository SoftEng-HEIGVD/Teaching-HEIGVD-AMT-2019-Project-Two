Feature: Update the user password

  Background:
    Given there is a Users server

  Scenario: Update password of another account with admin token
    Given I have an admin credential
    Given I have an admin token
    Given I have a new password Toto
    When I POST it to the /users/test@test.ch endpoint
    Then I receive a 202 status code

  Scenario: Update password of another account with user token
    Given I have an user credential
    Given I have a user token
    Given I have a new password Toto
    When I POST it to the /users/test@test.ch endpoint
    Then I receive a 403 status code

  Scenario: Update a my password
    Given I have an user credential
    Given I have a user token
    Given I have a new password user
    When I POST it to the /users/user@user.ch endpoint
    Then I receive a 202 status code