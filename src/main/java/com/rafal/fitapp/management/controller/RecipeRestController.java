package com.rafal.fitapp.management.controller;

import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.service.RecipeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class RecipeRestController {

    private RecipeService recipeService;

    @Autowired
    public RecipeRestController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public List<Recipe> findAll() {
        return recipeService.findAll();
    }

    @GetMapping("/recipe/{recipeId}")
    public Recipe findById(@PathVariable int recipeId) {
        return recipeService.findById(recipeId);
    }

    @PostMapping("/recipe")
    public Recipe save(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
        return recipe;
    }
}
