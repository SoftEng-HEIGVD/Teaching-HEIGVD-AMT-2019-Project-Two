Feature: Creation of actors

  Background:
    Given there is an application server
    Given I have a jwt token

  Scenario: create a new actor
    Given I have a valid actor payload
    When I POST it to the /actors endpoint
    Then I receive a 201 status code