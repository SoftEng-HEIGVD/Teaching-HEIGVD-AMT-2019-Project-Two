Feature: Creation of movies

  Background:
    Given there is an application server
    Given I have a jwt token

  Scenario: create a new movie
    Given I have a valid movie payload
    When I POST it to the /movies endpoint
    Then I receive a 201 status code