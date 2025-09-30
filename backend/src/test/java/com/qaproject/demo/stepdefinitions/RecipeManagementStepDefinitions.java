package com.qaproject.demo.stepdefinitions;

import com.qaproject.demo.entity.Recipe;
import com.qaproject.demo.service.RecipeService;
import com.qaproject.demo.repository.RecipeRepository;
import io.cucumber.java.en.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecipeManagementStepDefinitions {

    @MockitoBean
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    private Recipe testRecipe;
    private Recipe createdRecipe;
    private Exception thrownException;
    private String errorMessage;

    @Given("the recipe management system is available")
    public void the_recipe_management_system_is_available() {
        assertNotNull(recipeService);}
    @Given("I am a registered user")
    public void i_am_a_registered_user() {
        // Assume user is already authenticated and authorized
        assertTrue(true, "User is registered and authenticated");}
    @When("I create a recipe with title {string} and description {string}")
    public void i_create_a_recipe_with_title_and_description(String title, String description) {
        try { testRecipe = new Recipe(title, description);
            testRecipe.setId(1L);
            when(recipeRepository.save(any(Recipe.class))).thenReturn(testRecipe);
            createdRecipe = recipeService.addRecipe(title, description);
            thrownException = null;
        } catch (Exception e) {
            thrownException = e;
            errorMessage = e.getMessage();}}
    @When("I try to create a recipe with empty title and description {string}")
    public void i_try_to_create_a_recipe_with_empty_title_and_description(String description) {
        try { createdRecipe = recipeService.addRecipe("", description);
        } catch (IllegalArgumentException e) {
            thrownException = e;
            errorMessage = e.getMessage();
        }} @When("I try to create a recipe with null title and description {string}")
    public void i_try_to_create_a_recipe_with_null_title_and_description(String description) {
        try {createdRecipe = recipeService.addRecipe(null, description);
        } catch (IllegalArgumentException e) {
            thrownException = e;
            errorMessage = e.getMessage(); }}
    @Then("the recipe should be saved successfully")
    public void the_recipe_should_be_saved_successfully() {
        assertNull(thrownException, "No exception should be thrown");
        assertNotNull(createdRecipe, "Recipe should be created");
        verify(recipeRepository, times(1)).save(any(Recipe.class)); }
 @Then("the recipe should have title {string}")
    public void the_recipe_should_have_title(String expectedTitle) {
        assertNotNull(createdRecipe, "Recipe should exist");
        assertEquals(expectedTitle, createdRecipe.getTitle(), "Recipe title should match");
    }

    @Then("the recipe should have description {string}")
    public void the_recipe_should_have_description(String expectedDescription) {
        assertNotNull(createdRecipe, "Recipe should exist");
        assertEquals(expectedDescription, createdRecipe.getDescription(), "Recipe description should match");
    }

    @Then("I should get an error message {string}")
    public void i_should_get_an_error_message(String expectedMessage) {
        assertNotNull(thrownException, "Exception should be thrown");
        assertEquals(expectedMessage, errorMessage, "Error message should match");
    }

    @Then("the recipe should not be saved")
    public void the_recipe_should_not_be_saved() {
        assertNull(createdRecipe, "No recipe should be created");
        verify(recipeRepository, never()).save(any(Recipe.class));
    }
}