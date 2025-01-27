package org.michaelbae.recipeplanner.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeDTO {
    private String name;
    private String description;
    private String instructions;
}