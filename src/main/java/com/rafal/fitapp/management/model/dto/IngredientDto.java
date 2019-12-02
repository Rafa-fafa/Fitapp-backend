package com.rafal.fitapp.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

    private Integer amount;
    private String unit;
    private String name;

}
