Feature: Creation of users

  Background:
    Given there is a Users server

  Scenario: create a user
    Given I have a user payload
    When I POST it to the /users endpoint with admin token
    Then I receive a 201 status code

  Scenario: create a user
    Given I have a user payload
    When I POST it to the /users endpoint with user token
    Then I receive a 403 status code

  Scenario: create a user
    Given I have a user payload
    When I POST it to the /users endpoint with bad token
    Then I receive a 401 status code

  Scenario: create a user
    Given I have a user payload
    When I POST it to the /users endpoint with admin token
    Then I added successfully a user

