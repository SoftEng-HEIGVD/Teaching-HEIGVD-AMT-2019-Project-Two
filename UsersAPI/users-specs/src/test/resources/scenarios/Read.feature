# Created by nathanael at 12/30/19
Feature: Retrieving users

  Background:
    Given There is a User Api server

  Scenario: Retrieving all users
    When I GET the users from the /users endpoint
    Then I receive a 200 status code

  Scenario: Retrieving a user
    When I GET a user from the /users endpoint
    Then I receive a 200 status code