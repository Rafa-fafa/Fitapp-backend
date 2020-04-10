package com.rafal.fitapp.recipe_scraping.stratedy;

import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface RecipeScrapperStrategy {
    RecipeDto getRecipe(String url);

    String getTitle(Document webPage);

    String getDescription(Document webPage);

    List<IngredientDto> getIngredients(Document webPage);

    String getPortions(Document webPage);

    default Document getWebPage(String url){
            Document webPage = null;
            try {
                webPage = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return webPage;
    }

}
