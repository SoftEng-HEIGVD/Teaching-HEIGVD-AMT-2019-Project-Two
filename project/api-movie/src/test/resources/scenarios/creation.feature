Feature: Creation of movies

  Background:
    Given there is a Movies server

  Scenario: create a user
    Given I have a user payload with username and password
    When I POST it to the /registrations endpoint
    Then I receive a 201 status code

  Scenario: fail when no username or password provided
    Given I have a user payload with no username or password
    When I POST it to the /registrations endpoint
    Then I receive a 400 status code