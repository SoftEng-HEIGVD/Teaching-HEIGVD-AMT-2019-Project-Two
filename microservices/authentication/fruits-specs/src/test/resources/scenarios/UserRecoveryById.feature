Feature: Get all user
  Background:
    Given there is a Users server

  Scenario: Get all users by this id
    Given I have an admin credential
    Given I have an admin token
    When I GET it to the /users/user@user.ch endpoint
    Then I receive a 200 status code

  Scenario: Get all users by this id
    Given I have an user credential
    Given I have a user token
    When I GET it to the /users/user@user.ch endpoint
    Then I receive a 200 status code

  Scenario: Get all users by this id
    Given I have an admin credential
    Given I have an admin token
    When I GET it to the /users/toto@toto.ch endpoint
    Then I receive a 404 status code

  Scenario: Get all users by this id
    Given I have an user credential
    Given I have a user token
    When I GET it to the /users/admin@admin.ch endpoint
    Then I receive a 403 status code

  Scenario: Get all users by this id
    Given I have an admin credential
    Given I have an admin token
    When I GET it to the /users/user@user.ch endpoint
    Then I receive a user with email user@user.ch