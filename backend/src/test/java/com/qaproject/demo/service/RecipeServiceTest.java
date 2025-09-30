package com.qaproject.demo.service;

import com.qaproject.demo.entity.Recipe;
import com.qaproject.demo.entity.User;
import com.qaproject.demo.repository.RecipeRepository;
import com.qaproject.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * TDD Unit Tests for Service Layer
 * 3 MAIN OPERATIONS: CREATE, DELETE, LOGIN
 * Following Red-Green-Refactor cycle
 */
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RecipeService recipeService;

    @InjectMocks
    private UserService userService;

    private Recipe sampleRecipe;
    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleRecipe = new Recipe();
        sampleRecipe.setId(1L);
        sampleRecipe.setTitle("Test Recipe");
        sampleRecipe.setDescription("Test Description");

        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setUsername("testuser");
        sampleUser.setPassword("password123");
    }

    // =================
    // TDD OPERATION 1: CREATE - addRecipe() [EXISTING - ALREADY GREEN]
    // =================

    @Test
    void testCreateRecipe_ShouldReturnRecipe_WhenValidInputProvided() {
        // ARRANGE
        String title = "Pasta Carbonara";
        String description = "Classic Italian pasta dish";

        when(recipeRepository.save(any(Recipe.class))).thenReturn(sampleRecipe);

        // ACT
        Recipe result = recipeService.addRecipe(title, description);

        // ASSERT
        assertNotNull(result, "Recipe should not be null");
        assertEquals("Test Recipe", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testCreateRecipe_ShouldThrowException_WhenTitleIsNull() {
        // ARRANGE
        String title = null;
        String description = "Valid description";

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            recipeService.addRecipe(title, description);
        }, "Should throw exception when title is null");
    }

    @Test
    void testCreateRecipe_ShouldThrowException_WhenTitleIsEmpty() {
        // ARRANGE
        String title = "";
        String description = "Valid description";

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            recipeService.addRecipe(title, description);
        }, "Should throw exception when title is empty");
    }

    // =================
    // TDD OPERATION 2: DELETE - deleteRecipe() [TEMPORARILY COMMENTED FOR
    // LOGIN-ONLY GREEN PHASE]
    // =================

    /*
     * @Test
     * void testDeleteRecipe_ShouldReturnTrue_WhenRecipeExists() {
     * // GREEN PHASE: This test will pass with minimal implementation
     * Long recipeId = 1L;
     * when(recipeRepository.existsById(recipeId)).thenReturn(true);
     * 
     * boolean result = recipeService.deleteRecipe(recipeId);
     * 
     * assertTrue(result);
     * verify(recipeRepository, times(1)).existsById(recipeId);
     * verify(recipeRepository, times(1)).deleteById(recipeId);
     * }
     * 
     * @Test
     * void testDeleteRecipe_ShouldReturnFalse_WhenRecipeNotFound() {
     * // GREEN PHASE: This test will pass with minimal implementation
     * Long recipeId = 999L;
     * when(recipeRepository.existsById(recipeId)).thenReturn(false);
     * 
     * boolean result = recipeService.deleteRecipe(recipeId);
     * 
     * assertFalse(result);
     * verify(recipeRepository, times(1)).existsById(recipeId);
     * verify(recipeRepository, never()).deleteById(recipeId);
     * }
     * 
     * @Test
     * void testDeleteRecipe_ShouldThrowException_WhenIdIsNull() {
     * // GREEN PHASE: This test will pass with minimal implementation
     * assertThrows(IllegalArgumentException.class, () -> {
     * recipeService.deleteRecipe(null);
     * }, "Should throw exception when recipe ID is null");
     * }
     */

    // =================
    // TDD OPERATION 3: LOGIN - authenticateUser() [GREEN PHASE - WILL PASS]
    // =================

    @Test
    void testLoginUser_ShouldReturnTrue_WhenCredentialsAreValid() {
        // GREEN PHASE: This test will pass with minimal implementation
        String username = "testuser";
        String password = "password123";

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(sampleUser));

        boolean result = userService.authenticateUser(username, password);

        assertTrue(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoginUser_ShouldReturnFalse_WhenUserNotFound() {
        // GREEN PHASE: This test will pass with minimal implementation
        String username = "nonexistent";
        String password = "password123";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        boolean result = userService.authenticateUser(username, password);

        assertFalse(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoginUser_ShouldReturnFalse_WhenPasswordIsIncorrect() {
        // GREEN PHASE: This test will pass with minimal implementation
        String username = "testuser";
        String password = "wrongpassword";

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(sampleUser));

        boolean result = userService.authenticateUser(username, password);

        assertFalse(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testAddRecipe_ShouldReturnRecipe_WhenValidInputProvided() {
        // ARRANGE (Red Phase - This test will fail initially)
        String title = "Pasta Carbonara";
        String description = "Classic Italian pasta dish";

        when(recipeRepository.save(any(Recipe.class))).thenReturn(sampleRecipe);

        // ACT
        Recipe result = recipeService.addRecipe(title, description);

        // ASSERT
        assertNotNull(result, "Recipe should not be null");
        assertEquals("Test Recipe", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testAddRecipe_ShouldThrowException_WhenTitleIsNull() {
        // ARRANGE
        String title = null;
        String description = "Valid description";

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            recipeService.addRecipe(title, description);
        }, "Should throw exception when title is null");
    }

    @Test
    void testAddRecipe_ShouldThrowException_WhenTitleIsEmpty() {
        // ARRANGE
        String title = "";
        String description = "Valid description";

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            recipeService.addRecipe(title, description);
        }, "Should throw exception when title is empty");
    }

    // =================
    // CORE FEATURE 2: VALIDATE USER INPUT
    // =================

    @Test
    void testValidateRecipeInput_ShouldReturnTrue_WhenValidInputProvided() {
        // ARRANGE (Red Phase - This test will fail initially)
        String title = "Valid Recipe Title";
        String description = "Valid recipe description";

        // ACT
        boolean result = recipeService.validateRecipeInput(title, description);

        // ASSERT
        assertTrue(result, "Should return true for valid input");
    }

    @Test
    void testValidateRecipeInput_ShouldReturnFalse_WhenTitleIsNull() {
        // ARRANGE
        String title = null;
        String description = "Valid description";

        // ACT
        boolean result = recipeService.validateRecipeInput(title, description);

        // ASSERT
        assertFalse(result, "Should return false when title is null");
    }

    @Test
    void testValidateRecipeInput_ShouldReturnFalse_WhenTitleIsEmpty() {
        // ARRANGE
        String title = "";
        String description = "Valid description";

        // ACT
        boolean result = recipeService.validateRecipeInput(title, description);

        // ASSERT
        assertFalse(result, "Should return false when title is empty");
    }

    @Test
    void testValidateRecipeInput_ShouldReturnFalse_WhenDescriptionIsNull() {
        // ARRANGE
        String title = "Valid Title";
        String description = null;

        // ACT
        boolean result = recipeService.validateRecipeInput(title, description);

        // ASSERT
        assertFalse(result, "Should return false when description is null");
    }

    @Test
    void testValidateRecipeInput_ShouldReturnFalse_WhenTitleTooShort() {
        // ARRANGE
        String title = "AB"; // Too short (less than 3 characters)
        String description = "Valid description";

        // ACT
        boolean result = recipeService.validateRecipeInput(title, description);

        // ASSERT
        assertFalse(result, "Should return false when title is too short");
    }

    @Test
    void testValidateRecipeInput_ShouldReturnFalse_WhenTitleTooLong() {
        // ARRANGE
        String title = "A".repeat(101); // Too long (more than 100 characters)
        String description = "Valid description";

        // ACT
        boolean result = recipeService.validateRecipeInput(title, description);

        // ASSERT
        assertFalse(result, "Should return false when title is too long");
    }

    @Test
    void testValidateRecipeInput_ShouldReturnTrue_WhenDescriptionIsEmpty() {
        // ARRANGE
        String title = "Valid Title";
        String description = ""; // Empty description should be allowed

        // ACT
        boolean result = recipeService.validateRecipeInput(title, description);

        // ASSERT
        assertTrue(result, "Should return true when description is empty but title is valid");
    }
}