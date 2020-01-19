Feature: Login for users

  Background:
    Given there is an login server

  Scenario: login to the api
    Given I have a credential payload
    When I POST it to the /login endpoint
    Then I receive a 20 status code and a token

  Scenario: login with invalid credentials
    Given I have a wrong credential payload
    When I POST it to the /login endpoint
    Then I receive a 401 status code