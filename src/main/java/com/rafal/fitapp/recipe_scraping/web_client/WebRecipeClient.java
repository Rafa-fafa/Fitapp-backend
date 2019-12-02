package com.rafal.fitapp.recipe_scraping.web_client;

import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.entity.Ingredient;
import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import com.rafal.fitapp.recipe_scraping.utils.IngredientAssembler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebRecipeClient {

    public static RecipeDto createRecipe(String url) {
        Document webPage = getWebPage(url);

        String title = getTitle(webPage);
        String description = getDescription(webPage);
        List<IngredientDto> ingredients = getIngredients(webPage);
        Integer portions = getNumberOfPortions(webPage);

        return new RecipeDto(0,title,description,ingredients,portions);
    }

    private static Document getWebPage(String url) {
        Document webPage = null;
        try {
            webPage = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return webPage;
    }

    private static String getDescription(Document webPage) {
        StringBuilder description = new StringBuilder();

        webPage.select("div.field-name-field-przygotowanie")
                .first()
                .selectFirst("ul")
                .children()
                .forEach((paragraph) -> {
                    description.append(paragraph.text()).append("\n");
                });

        return description.toString();
    }

    private static Integer getNumberOfPortions(Document webPage) {
        try {
            return Integer.valueOf(webPage.select("div.field-name-field-ilosc-porcji").first().text().split(" ")[0]);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private static String getTitle(Document webPage) {
        return webPage.select("h1.przepis").first().text();
    }


    private static List<IngredientDto> getIngredients(Document webPage) {
        List<IngredientDto> ingredients = new ArrayList<>();

        webPage.select("div.field-name-field-skladniki")
                .first()
                .selectFirst("ul")
                .children()
                .forEach((ingredient) -> {
                    ingredients.add(IngredientAssembler.createIngredient(ingredient.text()));
                });

        return ingredients;
    }
}
