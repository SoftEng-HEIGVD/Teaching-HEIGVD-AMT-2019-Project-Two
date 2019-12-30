# Created by nathanael at 12/30/19
Feature: Update a user

  Background:
    Given there is a User API server

  Scenario: Update a user password
    Given I have a new password and a user id
    When I PATCH the /users/{userId} endpoint
    Then I receive a 200 status code