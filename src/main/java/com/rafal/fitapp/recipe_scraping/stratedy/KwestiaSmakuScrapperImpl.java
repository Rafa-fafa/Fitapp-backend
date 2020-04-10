package com.rafal.fitapp.recipe_scraping.stratedy;

import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import org.jsoup.nodes.Document;

import java.util.List;

public class KwestiaSmakuScrapperImpl implements RecipeScrapperStrategy {

    @Override
    public RecipeDto getRecipe(String url) {
        return null;
    }

    @Override
    public String getTitle(Document webPage) {
        return null;
    }

    @Override
    public String getDescription(Document webPage) {
        return null;
    }

    @Override
    public List<IngredientDto> getIngredients(Document webPage) {
        return null;
    }

    @Override
    public String getPortions(Document webPage) {
        return null;
    }


}
