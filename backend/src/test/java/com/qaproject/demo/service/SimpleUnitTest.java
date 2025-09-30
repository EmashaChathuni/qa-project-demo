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
 * 2 Automated Unit Tests for Demonstration
 * Test 1: Recipe Creation (CREATE operation)
 * Test 2: User Authentication (LOGIN operation)
 */
@ExtendWith(MockitoExtension.class)
public class SimpleUnitTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RecipeService recipeService;

    @InjectMocks
    private UserService userService;

    private Recipe testRecipe;
    private User testUser;

    @BeforeEach
    void setUp() {
        // Setup test data
        testRecipe = new Recipe();
        testRecipe.setId(1L);
        testRecipe.setTitle("Chocolate Cake");
        testRecipe.setDescription("Delicious chocolate cake recipe");

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
    }

    /**
     * TEST 1: Recipe Creation - Automated Unit Test
     * Tests successful recipe creation with valid data
     */
    @Test
    void testCreateRecipe_Success() {

        when(recipeRepository.save(any(Recipe.class))).thenReturn(testRecipe);

        Recipe result = recipeService.addRecipe("Chocolate Cake", "Delicious chocolate cake recipe");

        assertNotNull(result, "Recipe should not be null");
        assertEquals("Chocolate Cake", result.getTitle(), "Recipe title should match");
        assertEquals("Delicious chocolate cake recipe", result.getDescription(), "Recipe description should match");

        verify(recipeRepository, times(1)).save(any(Recipe.class));

        System.out.println("✅ TEST 1 PASSED: Recipe Creation Successful");
        System.out.println("   - Recipe Title: " + result.getTitle());
        System.out.println("   - Recipe Description: " + result.getDescription());
    }

   
    @Test
    void testUserAuthentication_Success() {
    
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        boolean isAuthenticated = userService.authenticateUser("testuser", "password123");

        assertTrue(isAuthenticated, "User should be authenticated with valid credentials");

        verify(userRepository, times(1)).findByUsername("testuser");

        System.out.println("TEST 2 PASSED: User Authentication Successful");
        System.out.println("   - Username: testuser");
        System.out.println("   - Authentication Result: " + isAuthenticated);
    }
}