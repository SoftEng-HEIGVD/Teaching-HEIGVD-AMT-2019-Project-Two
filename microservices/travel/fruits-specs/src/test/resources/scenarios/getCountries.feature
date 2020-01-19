Feature: Get list of all country

  Background:
    Given there is a travel server

  Scenario:  Get list of all country
    When I POST it to the /countries endpoint
    Then I receive a 200 status code