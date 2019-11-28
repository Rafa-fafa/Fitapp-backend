package com.rafal.fitapp.management.service;

import com.rafal.fitapp.management.dao.BasicIngredientRepository;
import com.rafal.fitapp.management.entity.BasicIngredient;
import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.dao.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private BasicIngredientRepository basicIngredientRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, BasicIngredientRepository basicIngredientRepository) {
        this.basicIngredientRepository = basicIngredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(int recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new EntityNotFoundException("Recipe was not found. Recipe id: " + recipeId));
    }

    @Override
    public void save(Recipe recipe) {
        recipe.getIngredients().forEach(ingredient -> {
            Optional<Long> ingId = Optional.ofNullable(basicIngredientRepository.findByName(ingredient.getName()).getId());
            if (ingId.isPresent()) {
                ingredient.setIngredientModelId(ingId.get());
            } else {
                BasicIngredient newIngredient = new BasicIngredient(ingredient.getName(), ingredient.getUnit());
                newIngredient = basicIngredientRepository.save(newIngredient);
                ingredient.setIngredientModelId(newIngredient.getId());
                System.out.println("ssssdaaasdesssss");
            }
        });

        recipeRepository.save(recipe);
    }

}
