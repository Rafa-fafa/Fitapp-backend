package com.rafal.fitapp.management.controller;

import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import com.rafal.fitapp.management.service.RecipeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.POST})
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
    public List<RecipeDto> findAll() {
        return recipeService.findAll();
    }

    @GetMapping("/recipe/{recipeId}")
    public Recipe findById(@PathVariable int recipeId) {
        return recipeService.findById(recipeId);
    }

    @PostMapping("/recipe")
    public ResponseEntity<?> save(@RequestBody RecipeDto recipeDto) {
        return recipeService.save(recipeDto);
    }

    @DeleteMapping("/recipe/{recipeId}")
    public Integer delete(@PathVariable int recipeId) {
        Optional.ofNullable(recipeService.findById(recipeId)).orElseThrow(() -> new RuntimeException("Recipe not found"));
        recipeService.delete(recipeId);
        return recipeId;
    }

}
