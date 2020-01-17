Feature: Creation of users

  Background:
    Given there is a Users server

  Scenario: create a user with admin token
    Given I have a user payload
    Given I have an admin credential
    Given I have an admin token
    When I POST it to the /users endpoint
    Then I receive a 201 status code

  Scenario: create a user with user token
    Given I have a user payload
    Given I have an user credential
    Given I have a user token
    When I POST it to the /users endpoint
    Then I receive a 403 status code

  Scenario: create a user with bad token
    Given I have a user payload
    Given I have a bad token
    When I POST it to the /users endpoint
    Then I receive a 401 status code

  Scenario: create a user with existing email
    Given I have a user payload with user@user.ch
    Given I have an admin credential
    Given I have an admin token
    When I POST it to the /users endpoint
    Then I receive a 409 status code

  Scenario: create a user
    Given I have a user payload
    Given I have an admin credential
    Given I have an admin token
    When I POST it to the /users endpoint
    Then I added successfully a user

