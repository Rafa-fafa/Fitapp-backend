package com.rafal.fitapp.management.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class RecipeDto {

    private Integer id;
    private String title;
    private String description;
    private List<IngredientDto> ingredients =  new ArrayList<>();
    private String portions;
    private String sourceUrl;

}
