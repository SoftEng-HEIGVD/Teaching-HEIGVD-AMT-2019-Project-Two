Feature: Update a trips

  Background:
    Given there is a travel server

  Scenario: Update a trips
    Given I have a trip payload
    When I PATCH it to the /trips/1 endpoint
    Then I receive a 202 status code