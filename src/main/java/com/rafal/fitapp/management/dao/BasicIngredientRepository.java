package com.rafal.fitapp.management.dao;

import com.rafal.fitapp.management.entity.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicIngredientRepository extends JpaRepository<BasicIngredient, Long> {

    BasicIngredient findByName(String name);

}
