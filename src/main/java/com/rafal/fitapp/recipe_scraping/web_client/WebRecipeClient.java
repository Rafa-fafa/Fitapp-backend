package com.rafal.fitapp.recipe_scraping.web_client;

import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.entity.Ingredient;
import com.rafal.fitapp.recipe_scraping.utils.IngredientAssembler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebRecipeClient {

    public static Recipe createRecipe(String url) {
        Document webPage = getWebPage(url);

        String title = getTitle(webPage);
        String description = getDescription(webPage);
        List<Ingredient> ingredients = getIngredients(webPage);
        int portions = getNumberOfPortions(webPage);

//        return new Recipe(title,description,ingredients,portions);
    return  null;
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

    private static int getNumberOfPortions(Document webPage) {
        return Integer.valueOf(webPage.select("div.field-name-field-ilosc-porcji").first().text().split(" ")[0]);
    }


    private static String getTitle(Document webPage) {
        return webPage.select("h1.przepis").first().text();
    }


    private static List<Ingredient> getIngredients(Document webPage) {
        List<Ingredient> ingredients = new ArrayList<>();

        webPage.select("div.field-name-field-skladniki")
                .first()
                .selectFirst("ul")
                .children()
                .forEach((ingredient) -> {
                    Ingredient ing = IngredientAssembler.createIngredient(ingredient.text());
                    ingredients.add(ing);
                });

        return ingredients;
    }
}
