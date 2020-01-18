Feature: Admin operations

  Background:
    Given there is a Users server
    Given there is an admin
    Given there is an admin token

  Scenario: get admin token
    When I POST admin credentials to the /authentications endpoint
    Then I receive a 200 status code
    Then I receive an admin token

  Scenario: get all users
    When I GET all users
    Then I receive a 200 status code
    Then I receive a list of users
    When I GET all users with pagination parameters
    Then I receive a list of users with the specified pagination size
