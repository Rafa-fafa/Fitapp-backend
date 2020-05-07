package com.rafal.fitapp.management.service;

import com.rafal.fitapp.management.dao.BasicIngredientRepository;
import com.rafal.fitapp.management.dao.RecipeRepository;
import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import com.rafal.fitapp.management.model.mapper.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeMapper recipeMapper;
    private RecipeRepository recipeRepository;
    private BasicIngredientRepository basicIngredientRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, BasicIngredientRepository basicIngredientRepository) {
        this.basicIngredientRepository = basicIngredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<RecipeDto> findAll() {
        return recipeMapper.recipesToRecipeDtos(recipeRepository.findAll());
    }

    @Override
    public Recipe findById(int recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new EntityNotFoundException("Recipe was not found. Recipe id: " + recipeId));
    }

    @Override
    public ResponseEntity<?> save(RecipeDto recipeDto) {
        if (!recipeRepository.existsRecipeByTitle(recipeDto.getTitle())) {
            saveRecipe(recipeDto);
            return new ResponseEntity<>(recipeDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(getRecipeExistsMessage(recipeDto),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
        recipeRepository.save(recipe);
    }

    private String getRecipeExistsMessage(RecipeDto recipeDto) {
        return String.format("Recipe '%s' already exists",recipeDto.getTitle());
    }

    @Override
    public void delete(int recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
