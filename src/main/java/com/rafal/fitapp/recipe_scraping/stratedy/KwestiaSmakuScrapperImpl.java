package com.rafal.fitapp.recipe_scraping.stratedy;

import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import com.rafal.fitapp.management.model.dto.SubDescriptionDto;
import com.rafal.fitapp.recipe_scraping.utils.IngredientAssembler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KwestiaSmakuScrapperImpl implements RecipeScrapperStrategy {
    @Override
    public RecipeDto getRecipe(String url) {
        Document webPage = getWebPage(url);

        return RecipeDto.builder()
                .title(getTitle(webPage))
                .subDescriptions(getSubDescription(webPage))
                .ingredients(getIngredients(webPage))
                .portions(getPortions(webPage))
                .sourceUrl(url)
                .build();
    }

    @Override
    public String getTitle(Document webPage) {
        return webPage.select("h1.przepis").first().text();
    }

    @Override
    public List<SubDescriptionDto> getSubDescription(Document webPage) {
        List<SubDescriptionDto> subDescriptions = new ArrayList<>();

        List<Element> elements = webPage.select("div.field-name-field-przygotowanie")
                .first()
                .children();

        for (int i = 0; i < elements.size(); i += 2) {
            SubDescriptionDto subDescription = SubDescriptionDto.builder()
                    .subDescriptionTitle(elements.get(i).text())
                    .description(createDescription(elements.get(i + 1)))
                    .build();
            subDescriptions.add(subDescription);
        }

        return subDescriptions;
    }

    private String createDescription(Element element) {
        StringBuilder description =  new StringBuilder();
        element.children()
                .forEach((paragraph) -> {
                    description.append(paragraph.text()).append("\\n");
                });
        return description.toString();
    }

    @Override
    public List<IngredientDto> getIngredients(Document webPage) {
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

    @Override
    public String getPortions(Document webPage) {
        return Optional.ofNullable(webPage.select("div.field-name-field-ilosc-porcji")
                .first())
                .map(Element::text)
                .orElse(null);
    }
}
