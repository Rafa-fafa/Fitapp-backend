package com.rafal.fitapp.recipe_scraping.stratedy;

import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import com.rafal.fitapp.management.model.dto.SubDescriptionDto;
import com.rafal.fitapp.recipe_scraping.utils.IngredientAssembler;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class AllRecipeScrapperImpl implements RecipeScrapperStrategy {

    private static final String SERVINGS = "Servings: ";

    @Override
    public RecipeDto getRecipe(String url) {
        Document webPage = getWebPage(url);

        return RecipeDto.builder()
                .title(getTitle(webPage))
                .description(getDescription(webPage))
                .ingredients(getIngredients(webPage))
                .portions(getPortions(webPage))
                .sourceUrl(url)
                .build();
    }

    @Override
    public String getTitle(Document webPage) {
        return webPage.select("h1.headline")
                .first()
                .text();
    }

    @Override
    public String getDescription(Document webPage) {
        StringBuilder description = new StringBuilder();

        webPage.select(".instructions-section p")
                .forEach(element -> {
                    description.append(element.text())
                            .append("\\n");
                });

        return description.toString();
    }

    @Override
    public List<IngredientDto> getIngredients(Document webPage) {
        List<IngredientDto> ingredients = new ArrayList<>();

        webPage.select(".ingredients-section span.ingredients-item-name")
                .forEach(ingredient -> {
                    ingredients.add(IngredientAssembler.createIngredient(ingredient.text()));
                });

        return ingredients;
    }

    @Override
    public String getPortions(Document webPage) {
        
        return SERVINGS + webPage.select("div.recipe-meta-item-header:contains(Servings)+div")
                .first()
                .text();
    }
}
