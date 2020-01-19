Feature: Update of user profile

  Background:
    Given I have a user payload with username and password
    When I POST it to the /registrations endpoint
    Then I receive a 201 status code
    When I POST his credentials to the /authentications endpoint
    Then I receive a 200 status code
    Then I receive a jwt token

  Scenario: update a user profile
    Given I have a jwt token
    Given I have a profile update
    When I PUT a profile update to the /profileUpdates endpoint
    Then I receive a 200 status code
    When I GET the user
    Then I have an updated user