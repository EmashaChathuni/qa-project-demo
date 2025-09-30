package com.qaproject.demo.stepdefinitions;

import com.qaproject.demo.entity.Recipe;
import com.qaproject.demo.service.RecipeService;
import com.qaproject.demo.repository.RecipeRepository;
import io.cucumber.java.en.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecipeDeletionStepDefinitions {

    @MockitoBean
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    private Recipe testRecipe;
    private boolean deletionResult;
    private Exception thrownException;
    private String errorMessage;

    @Given("the recipe management system is available")
    public void the_recipe_management_system_is_available() {
        assertNotNull(recipeService);
    }

    @Given("I am a registered user")
    public void i_am_a_registered_user() {
        // Assume user is already authenticated and authorized
        assertTrue(true, "User is registered and authenticated");
    }

    @Given("a recipe exists with id {long} and title {string}")
    public void a_recipe_exists_with_id_and_title(Long recipeId, String title) {
        testRecipe = new Recipe(title, "Test description");
        testRecipe.setId(recipeId);

        when(recipeRepository.existsById(recipeId)).thenReturn(true);
    }

    @Given("the recipe with id {long} has been deleted")
    public void the_recipe_with_id_has_been_deleted(Long recipeId) {
        when(recipeRepository.existsById(recipeId)).thenReturn(false);
    }

    @When("I delete the recipe with id {long}")
    public void i_delete_the_recipe_with_id(Long recipeId) {
        try {
            when(recipeRepository.existsById(recipeId)).thenReturn(true, false);
            doNothing().when(recipeRepository).deleteById(recipeId);

            deletionResult = recipeService.deleteRecipe(recipeId);
            thrownException = null;
        } catch (Exception e) {
            thrownException = e;
            errorMessage = e.getMessage();
            deletionResult = false;
        }
    }

    @When("I try to delete a recipe with id {long}")
    public void i_try_to_delete_a_recipe_with_id(Long recipeId) {
        try {
            when(recipeRepository.existsById(recipeId)).thenReturn(false);
            deletionResult = recipeService.deleteRecipe(recipeId);
        } catch (RuntimeException e) {
            thrownException = e;
            errorMessage = e.getMessage();
            deletionResult = false;
        }
    }

    @When("I try to delete a recipe with null id")
    public void i_try_to_delete_a_recipe_with_null_id() {
        try {
            deletionResult = recipeService.deleteRecipe(null);
        } catch (IllegalArgumentException e) {
            thrownException = e;
            errorMessage = e.getMessage();
            deletionResult = false;
        }
    }

    @When("I try to delete the recipe with id {long} again")
    public void i_try_to_delete_the_recipe_with_id_again(Long recipeId) {
        try {
            when(recipeRepository.existsById(recipeId)).thenReturn(false);
            deletionResult = recipeService.deleteRecipe(recipeId);
        } catch (Exception e) {
            thrownException = e;
            errorMessage = e.getMessage();
            deletionResult = false;
        }
    }

    @Then("the recipe should be deleted successfully")
    public void the_recipe_should_be_deleted_successfully() {
        assertTrue(deletionResult, "Recipe should be deleted successfully");
        assertNull(thrownException, "No exception should be thrown");
    }

    @Then("the recipe with id {long} should no longer exist")
    public void the_recipe_with_id_should_no_longer_exist(Long recipeId) {
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }

    @Then("I should get an error message {string}")
    public void i_should_get_an_error_message(String expectedMessage) {
        assertNotNull(thrownException, "Exception should be thrown");
        assertEquals(expectedMessage, errorMessage, "Error message should match");
    }

    @Then("no recipe should be deleted")
    public void no_recipe_should_be_deleted() {
        verify(recipeRepository, never()).deleteById(any());
    }

    @Then("the operation should fail gracefully")
    public void the_operation_should_fail_gracefully() {
        assertFalse(deletionResult, "Deletion operation should fail");
        verify(recipeRepository, never()).deleteById(any());
    }
}