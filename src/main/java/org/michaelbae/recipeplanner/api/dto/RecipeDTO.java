package org.michaelbae.recipeplanner.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class RecipeDTO {

    private Long id;
    private String name;
    private String description;
    private String instructions;
    private String imagePath;
}