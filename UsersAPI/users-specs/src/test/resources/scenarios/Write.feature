# Created by nathanael at 12/30/19
Feature: Creation of users

  Background:
    Given there a User API server

  Scenario: create a user
    Given I have a user payload
    # TODO modify endpoint to /sign-up
    When I POST it to the /users endpoint
    Then I receive a 201 status code for the creation

  Scenario: Update a user password
    Given I have a new password
    And a user id
    When I PATCH the /users/userId endpoint
    Then I receive a 200 status code for the update