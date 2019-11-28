package com.rafal.fitapp.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredients_base")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicIngredient {

    public BasicIngredient(String name, String baseUnit) {
        this.name = name;
        this.baseUnit = baseUnit;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="base_unit")
    private String baseUnit;

    @Column(name="possible_units")
    private String possibleUnit;
}
