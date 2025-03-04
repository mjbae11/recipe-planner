package org.michaelbae.recipeplanner.service;
import org.michaelbae.recipeplanner.api.dto.RecipeDTO;
import org.michaelbae.recipeplanner.model.Recipe;

import java.util.List;

public interface RecipeService
{
    List<RecipeDTO> getAllRecipes();

    Recipe addRecipe(Recipe recipe);

    RecipeDTO getRecipeById(Long id);

    void updateRecipe(Long id, Recipe recipe);

    void deleteRecipe(Long id);
}
