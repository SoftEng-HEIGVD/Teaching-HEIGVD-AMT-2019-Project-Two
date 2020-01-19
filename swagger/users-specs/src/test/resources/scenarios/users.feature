Feature: Management users

  Background:
    Given there is an login server

  Scenario: create a user
    Given I have a user payload and a JWT token
    When I POST it to the /users endpoint
    Then I receive a 201 status code
    
    Scenario: Update password of a user
      Given I have a password payload and a JWT token
      When I PATCH it to the /users/john.doe@boozify.ch endpoint
      Then I receive a 201 status code