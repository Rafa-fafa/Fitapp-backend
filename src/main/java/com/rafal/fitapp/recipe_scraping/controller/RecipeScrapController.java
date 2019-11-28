package com.rafal.fitapp.recipe_scraping.controller;

import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.recipe_scraping.web_client.WebRecipeClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scrap")
public class RecipeScrapController {

    @PostMapping("/recipe")
    public Recipe getScrappedRecipe(@RequestBody String url){
        return WebRecipeClient.createRecipe(url);
    }
}
