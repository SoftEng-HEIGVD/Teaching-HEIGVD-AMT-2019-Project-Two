Feature: Get list of all reason

  Background:
    Given there is a travel server

  Scenario:  Get list of all reason
    When I POST it to the /reasons endpoint
    Then I receive a 200 status code