Feature: Update a trips

  Background:
    Given there is a travel server

  Scenario: Update our trips with admin token
    Given I have a trip payload
    Given I have a admin token
    When I PATCH it to the /trips/1 endpoint
    Then I receive a 202 status code

  Scenario: Update a trips of another account with admin token
    Given I have a trip payload
    Given I have a admin token
    When I PATCH it to the /trips/15 endpoint
    Then I receive a 202 status code

  Scenario: Update a trips of another account with user token
    Given I have a trip payload
    Given I have a user token
    When I PATCH it to the /trips/1 endpoint
    Then I receive a 403 status code