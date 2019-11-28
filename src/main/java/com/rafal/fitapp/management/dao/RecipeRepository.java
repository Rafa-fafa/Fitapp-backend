package com.rafal.fitapp.management.dao;

import com.rafal.fitapp.management.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

    Optional<Recipe> findById(int id);


}
