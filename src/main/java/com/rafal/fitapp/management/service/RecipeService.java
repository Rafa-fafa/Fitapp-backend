package com.rafal.fitapp.management.service;


        import com.rafal.fitapp.management.entity.Recipe;

        import java.util.List;


public interface RecipeService {
    List<Recipe> findAll();
    Recipe findById(int id);
    void save(Recipe recipe);
}
