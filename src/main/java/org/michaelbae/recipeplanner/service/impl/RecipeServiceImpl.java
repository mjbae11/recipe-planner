package org.michaelbae.recipeplanner.service.impl;

import org.michaelbae.recipeplanner.api.dto.RecipeDTO;
import org.michaelbae.recipeplanner.model.Recipe;
import org.michaelbae.recipeplanner.repository.RecipeRepository;
import org.michaelbae.recipeplanner.repository.UserRepository;
import org.michaelbae.recipeplanner.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService
{


    private final UserRepository userRepository;
    private RecipeRepository recipeRepository;

    /**
     * Constructor:
     * RecipeService gets access to database through the RecipeRepository class,
     * where the data will be stored.
     * @param recipeRepository
     */
    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository)
    {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }


    /**
     * Gets all recipes from the RecipeRepository, and translates
     * each recipe enitity into RecipeDTOs.
     *
     * @return a list of all the RecipeDTOs
     */
    @Override
    public List<RecipeDTO> getAllRecipes()
    {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> new RecipeDTO(
                        recipe.getName(),
                        recipe.getDescription(),
                        recipe.getInstructions()))
                .collect(Collectors.toList());
    }

    @Override
    public Recipe addRecipe(Recipe recipe)
    {
        return recipeRepository.save(recipe);
    }

    @Override
    public RecipeDTO getRecipeById(Long id)
    {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid recipe id " + id));

        return new RecipeDTO(
                recipe.getName(),
                recipe.getDescription(),
                recipe.getInstructions()
        );
    }

    @Override
    public void updateRecipe(Long id, Recipe recipe)
    {
        // check whether the user is in the database or not
        recipeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid recipe id " + id));
        recipe.setId(id);
        recipeRepository.save(recipe);
    }
}