package org.michaelbae.recipeplanner.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDTO {
    private String name;
    private String unit;
    private double quantity;
}