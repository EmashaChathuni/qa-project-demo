Feature: Recipe Deletion
  As a user
  I want to delete my recipes
  So that I can remove recipes I no longer need

  Background:
    Given the recipe management system is available
    And I am a registered user
    And a recipe exists with id 1 and title "Test Recipe"

  @smoke
  Scenario: Delete an existing recipe
    When I delete the recipe with id 1
    Then the recipe should be deleted successfully
    And the recipe with id 1 should no longer exist

  @negative
  Scenario: Cannot delete non-existent recipe
    When I try to delete a recipe with id 999
    Then I should get an error message "Recipe not found with ID: 999"
    And no recipe should be deleted

  @validation  
  Scenario: Cannot delete recipe with null id
    When I try to delete a recipe with null id
    Then I should get an error message "Recipe ID cannot be null"
    And no recipe should be deleted

  @edge-case
  Scenario: Delete recipe that was already deleted
    Given the recipe with id 1 has been deleted
    When I try to delete the recipe with id 1 again
    Then I should get an error message "Recipe not found with ID: 1"
    And the operation should fail gracefully