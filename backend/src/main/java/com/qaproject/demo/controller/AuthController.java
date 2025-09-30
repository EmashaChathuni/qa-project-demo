package com.qaproject.demo.controller;

import com.qaproject.demo.entity.User;
import com.qaproject.demo.entity.Recipe;
import com.qaproject.demo.repository.UserRepository;
import com.qaproject.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("username", username);
            return ResponseEntity.ok(response);
        }

        Map<String, String> error = new HashMap<>();
        error.put("message", "Invalid username or password");
        return ResponseEntity.badRequest().body(error);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (userRepository.existsByUsername(username)) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Username already exists");
            return ResponseEntity.badRequest().body(error);
        }

        User user = new User(username, password);
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/recipes")
    public ResponseEntity<?> addRecipe(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String description = request.get("description");

        if (title == null || title.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Title is required");
            return ResponseEntity.badRequest().body(error);
        }

        Recipe recipe = new Recipe(title, description);
        recipeRepository.save(recipe);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Recipe added successfully");
        return ResponseEntity.ok(response);
    }

    // Database viewer endpoint to see all data
    @GetMapping("/database/view")
    public ResponseEntity<?> viewDatabase() {
        Map<String, Object> dbData = new HashMap<>();

        // Get all users
        List<User> users = userRepository.findAll();
        dbData.put("users", users);

        // Get all recipes
        List<Recipe> recipes = recipeRepository.findAll();
        dbData.put("recipes", recipes);

        // Add counts
        dbData.put("userCount", users.size());
        dbData.put("recipeCount", recipes.size());

        return ResponseEntity.ok(dbData);
    }
}