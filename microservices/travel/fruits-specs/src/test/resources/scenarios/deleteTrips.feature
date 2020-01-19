Feature: Delete a trips by this id

  Background:
    Given there is a travel server



  Scenario: Delete our trips by this id with admin token
    Given I have a trip payload
    Given I have a admin token
    Given I POST it to the /trips endpoint
    When I DELETE it to the /trips/idTrip endpoint
    Then I receive a 204 status code

  Scenario: Delete a trips of another account by this id with admin token
    Given I have a trip payload
    Given I have a user token
    When I POST it to the /trips endpoint
    Given I have a admin token
    When I DELETE it to the /trips/idTrip endpoint
    Then I receive a 204 status code

  Scenario: Delete a trips of another account by this id with admin token
    Given I have a trip payload
    Given I have a admin token
    When I POST it to the /trips endpoint
    Given I have a user token
    When I DELETE it to the /trips/idTrip endpoint
    Then I receive a 403 status code

  Scenario: Delete our trips by this id with admin token
    Given I have a trip payload
    Given I have a admin token
    Given I POST it to the /trips endpoint
    When I DELETE it to the /trips/idTrip endpoint
    Then I deleted successfully a trip
    Then I receive a 404 status code

