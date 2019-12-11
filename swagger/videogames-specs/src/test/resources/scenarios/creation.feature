Feature: Creation of videogames

  Background:
    Given there is a Videogames server

  Scenario: create a videogame
    Given I have a videogame payload
    When I POST it to the /videogames endpoint
    Then I receive a 201 status code