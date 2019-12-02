package com.rafal.fitapp.management.service;


import com.rafal.fitapp.management.entity.Recipe;
import com.rafal.fitapp.management.model.dto.RecipeDto;

import java.util.List;


public interface RecipeService {
    List<RecipeDto> findAll();

    Recipe findById(int id);

    void save(RecipeDto recipeDto);
}
