package com.rafal.fitapp.recipe_scraping.web_client;

import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import com.rafal.fitapp.recipe_scraping.utils.IngredientAssembler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WebRecipeClient {

    public static RecipeDto createRecipe(String url) {
        Document webPage = getWebPage(url);

        return RecipeDto.builder()
                .title(getTitle(webPage))
                .ingredients(getIngredients(webPage))
                .portions(getNumberOfPortions(webPage))
                .sourceUrl(url)
                .build();
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
                    description.append(paragraph.text()).append("\\n");
                });

        return description.toString();
    }

    private static String getNumberOfPortions(Document webPage) {
        return Optional.ofNullable(webPage.select("div.field-name-field-ilosc-porcji")
                .first())
                .map(Element::text)
                .orElse(null);
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
                .forEach(ingredient -> {
                    ingredients.add(IngredientAssembler.createIngredient(ingredient.text()));
                });

        return ingredients;
    }
}
