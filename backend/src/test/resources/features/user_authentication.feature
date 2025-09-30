Feature: User Authentication
  As a user
  I want to authenticate into the system
  So that I can access my personal recipe collection

  Background:
    Given the authentication system is available
    And a user exists with username "testuser" and password "password123"

  @smoke @authentication
  Scenario: Successful user login
    When I login with username "testuser" and password "password123"
    Then I should be authenticated successfully
    And I should receive a valid session token

  @negative @authentication
  Scenario: Failed login with wrong password
    When I login with username "testuser" and password "wrongpassword"
    Then authentication should fail
    And I should get an error message "Invalid username or password"
    And I should not receive a session token

  @negative @authentication  
  Scenario: Failed login with non-existent user
    When I login with username "nonexistent" and password "password123"
    Then authentication should fail
    And I should get an error message "Invalid username or password"
    And I should not receive a session token

  @validation @authentication
  Scenario: Cannot login with empty credentials
    When I login with empty username and password
    Then authentication should fail
    And I should get an error message "Username and password are required"

  @security
  Scenario: Multiple failed login attempts
    When I attempt login 3 times with username "testuser" and wrong password "wrongpass"
    Then all authentication attempts should fail
    And the account should remain secure