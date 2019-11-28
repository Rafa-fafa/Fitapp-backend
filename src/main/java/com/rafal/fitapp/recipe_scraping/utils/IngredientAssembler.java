package com.rafal.fitapp.recipe_scraping.utils;

import com.rafal.fitapp.management.entity.Ingredient;

public class IngredientAssembler {

    public static Ingredient createIngredient(String ingredientString) {
        String[] ingredientAtributes = ingredientString.split(" ");
        Integer amount;
        String unit;
        String name;

//        if (!NumberUtils.isCreatable(ingredientAtributes[0])) {
////            return new Ingredient(0,null, null, ingredientString);
//            return new Ingredient(0,null, null);
//        }
//
//        if (ingredientAtributes.length == 2) {
//            amount = Integer.valueOf(ingredientAtributes[0]);
//            unit = "szt.";
//            name = ingredientAtributes[1];
////            return new Ingredient(0,amount, unit, name);
//            return new Ingredient(0,amount, unit);
//        }
//
//        if (ingredientAtributes.length >= 3) {
//            amount = Integer.valueOf(ingredientAtributes[0]);
//            unit = ingredientAtributes[1];
//            StringBuilder concatedName = new StringBuilder();
//            for (int i = 2; i < ingredientAtributes.length; i++) {
//                concatedName.append(ingredientAtributes[i])
//                        .append(" ");
//            }
////            return new Ingredient(amount, unit, concatedName.toString());
//            return new Ingredient(0,amount, unit);
//        }

        return null;
    }
}
