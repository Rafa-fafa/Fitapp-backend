package com.rafal.fitapp.management.model.mapper;

import com.rafal.fitapp.management.entity.Ingredient;
import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.model.dto.IngredientDto;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeMapper instance= Mappers.getMapper(RecipeMapper.class);


    RecipeDto recipeToRecipeDto(Recipe recipe);
    Recipe recipeDtoToRecipe(RecipeDto recipeDto);

    List<RecipeDto> recipesToRecipeDtos(List<Recipe> recipe);
    List<Recipe> recipeDtosToRecipes(List<RecipeDto> recipeDto);


    @Mappings({
            @Mapping(target = "name", source="basicIngredient.name")
    })
    IngredientDto ingredientDtoToIngredient(Ingredient ingredient);
    @Mappings({
            @Mapping(target = "basicIngredient.name",source = "name")
    })
    Ingredient ingredientToIngredienDto(IngredientDto ingredientDto);


}
