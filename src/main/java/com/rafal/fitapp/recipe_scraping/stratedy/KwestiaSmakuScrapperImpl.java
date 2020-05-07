package com.rafal.fitapp.recipe_scraping.stratedy;

import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
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
                .description(getDescription(webPage))
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
    public String getDescription(Document webPage) {
        StringBuilder description = new StringBuilder();


        webPage.select("div.field-name-field-przygotowanie")
                .first()
                .children()
                .forEach(element -> {
                    description.append(createDescriptionDependingOnHtmlTag(element))
                            .append("\\n");
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

    private String createDescriptionDependingOnHtmlTag(Element element) {
        if (element.is("div")) {
            return element.text();
        } else if (element.is("ul")) {
            return createDescriptionOutOfBulletPoints(element);
        }else {
            return null;
        }
    }

    private String createDescriptionOutOfBulletPoints(Element element) {
        StringBuilder subDescription = new StringBuilder();
        element.children()
                .forEach((paragraph) -> {
                    subDescription.append(paragraph.text())
                            .append("\\n");
                });
        return subDescription.toString();
    }


}
