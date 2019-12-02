package com.rafal.fitapp.recipe_scraping.controller;

import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import com.rafal.fitapp.management.service.RecipeServiceImpl;
import com.rafal.fitapp.recipe_scraping.web_client.WebRecipeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scrap")
public class RecipeScrapController {

    @Autowired
    RecipeServiceImpl recipeService;

    @PostMapping("/recipe")
    public RecipeDto getScrappedRecipe(@RequestBody String url) {
        RecipeDto recipeDto = WebRecipeClient.createRecipe(url);
        recipeService.save(recipeDto);
        return recipeDto;
    }
}
