Feature: Get all user
  Background:
    Given there is a Users server

  Scenario: Get all users
    Given I have an admin credential
    Given I have an admin token
    When I GET it to the /users endpoint
    Then I receive a 200 status code

  Scenario:  Get all users
    Given I have an user credential
    Given I have a user token
    When I GET it to the /users endpoint
    Then I receive a 403 status code

  Scenario:  Get all users
    Given I have a bad token
    When I GET it to the /users endpoint
    Then I receive a 401 status code

  Scenario:  Get all users
    Given I have a bad token
    When I GET it to the /users endpoint
    Then I receive a 401 status code