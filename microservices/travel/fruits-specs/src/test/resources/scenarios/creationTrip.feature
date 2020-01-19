Feature: Creation of trips

  Background:
    Given there is a travel server

  Scenario: create a trips
    Given I have a trip payload
    When I POST it to the /trips endpoint
    Then I receive a 201 status code

  Scenario: create a trips
    Given I have a trip payload
    When I POST it to the /trips endpoint
    Then I added successfully a trip