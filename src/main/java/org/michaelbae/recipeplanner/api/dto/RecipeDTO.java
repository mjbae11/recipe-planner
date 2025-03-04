package org.michaelbae.recipeplanner.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeDTO {
    // temp id to make things easier for now
    private Long id;
    private String name;
    private String description;
    private String instructions;
}