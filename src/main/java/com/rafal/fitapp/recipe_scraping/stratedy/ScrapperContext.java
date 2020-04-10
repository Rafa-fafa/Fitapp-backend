package com.rafal.fitapp.recipe_scraping.stratedy;

import com.rafal.fitapp.management.model.dto.RecipeDto;
import org.springframework.stereotype.Component;

@Component
public class ScrapperContext {

    public static final String KWESTIA_SMAKU = "kwestiasmaku";
    public static final String ALL_RECIPES = "allrecipes";

    private RecipeScrapperStrategy recipeScrapperStrategy;

    public RecipeDto getRecipe(String url) {
        setStrategy(url);
        return this.recipeScrapperStrategy.getRecipe(url);
    }

    private void setStrategy(String url) {
        if(url.contains(KWESTIA_SMAKU)){
                this.recipeScrapperStrategy = new KwestiaSmakuScrapperImpl();
        }else if(url.contains(ALL_RECIPES)){
                this.recipeScrapperStrategy = new KwestiaSmakuScrapperImpl();
        }
    }
}
