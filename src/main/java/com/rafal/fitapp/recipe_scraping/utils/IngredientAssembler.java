package com.rafal.fitapp.recipe_scraping.utils;

import com.rafal.fitapp.management.entity.Ingredient;
import com.rafal.fitapp.management.model.dto.IngredientDto;
import org.apache.commons.lang3.math.NumberUtils;

public class IngredientAssembler {
    public static final String SZT="szt.";


    public static IngredientDto createIngredient(String ingredient) {
        String[] ingredientAtributes = ingredient.split(" ");
        Integer amount;
        String unit;
        String name;

        if (!NumberUtils.isCreatable(ingredientAtributes[0])) {
            return new IngredientDto(null,null, ingredient);
        }

        if (ingredientAtributes.length == 2) {
            amount = Integer.valueOf(ingredientAtributes[0]);
            unit = null;
            name = ingredientAtributes[1];
            return new IngredientDto(amount,unit, name);
        }

        if (ingredientAtributes.length >= 3) {
            amount = Integer.valueOf(ingredientAtributes[0]);
            unit = ingredientAtributes[1];
            StringBuilder concatedName = new StringBuilder();
            for (int i = 2; i < ingredientAtributes.length; i++) {
                concatedName.append(ingredientAtributes[i])
                        .append(" ");
            }
            return new IngredientDto(amount, unit, concatedName.toString());
        }

        return null;
    }
}
