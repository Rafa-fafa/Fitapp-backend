package com.rafal.fitapp.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private Integer id;
    private String title;
    private String description;
    private List<IngredientDto> ingredients =  new ArrayList<>();
    private Integer numberOfPortion;

}
