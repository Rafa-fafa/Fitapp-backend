package com.rafal.fitapp.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
public class RecipeDto {

    private Integer id;
    private String title;
    private List<SubDescriptionDto> subDescriptions;
    private List<IngredientDto> ingredients =  new ArrayList<>();
    private String portions;
    private String sourceUrl;

}
