Feature: Recipe Management
  As a user
  I want to manage my recipes
  So that I can organize my cooking ideas
  Background:
    Given the recipe management system is available
 @smoke
  Scenario: Add a new recipe successfully
    Given I am a registered user
    When I create a recipe with title "Pasta Carbonara" and description "Classic Italian pasta dish"
    Then the recipe should be saved successfully
    And the recipe should have title "Pasta Carbonara"
    And the recipe should have description "Classic Italian pasta dish"
 @regression
  Scenario: Cannot add recipe without title
    Given I am a registered user
    When I try to create a recipe with empty title and description "Some description"
    Then I should get an error message "Title cannot be null or empty"
    And the recipe should not be saved
 @regression  
  Scenario: Cannot add recipe with null title
    Given I am a registered user
    When I try to create a recipe with null title and description "Valid description"
    Then I should get an error message "Title cannot be null or empty"
    And the recipe should not be saved
 Scenario Outline: Add multiple recipes with different data
    Given I am a registered user
    When I create a recipe with title "<title>" and description "<description>"
    Then the recipe should be saved successfully
    And the recipe should have title "<title>"
    And the recipe should have description "<description>"
      Examples:
      | title           | description                    |
      | Pizza Margherita| Traditional Italian pizza      |
      | Chocolate Cake  | Rich and moist chocolate cake  |
      | Caesar Salad    | Fresh romaine with caesar dressing |