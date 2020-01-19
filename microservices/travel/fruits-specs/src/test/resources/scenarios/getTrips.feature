Feature: Get all our trips

  Background:
    Given there is a travel server

  Scenario: Get all our trips
    Given I have a admin token
    When I GET it to the /trips endpoint with offset 0 and limit 5
    Then I receive a 200 status code

  Scenario: Get all our trips with wrong offset or limit
    Given I have a admin token
    When I GET it to the /trips endpoint with offset 1 and limit 0
    Then I receive a 400 status code