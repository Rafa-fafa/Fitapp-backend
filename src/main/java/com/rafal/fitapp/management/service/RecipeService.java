package com.rafal.fitapp.management.service;


import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.model.dto.RecipeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface RecipeService {
    List<RecipeDto> findAll();
    Recipe findById(int id);
    ResponseEntity<?> save(RecipeDto recipeDto);
    void delete(int recipeId);
}
