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
import java.util.Objects;
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
                        // id added for now, maybe take out later for
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getDescription(),
                        recipe.getInstructions(),
                        recipe.getImagePath()))
                .collect(Collectors.toList());
    }

    @Override
    public Recipe addRecipe(Recipe recipe)
    {
        Objects.requireNonNull(recipe, "Recipe cannot be null");
        Objects.requireNonNull(recipe.getName(), "Recipe name cannot be null");
        Objects.requireNonNull(recipe.getDescription(), "Recipe description cannot be null");
        Objects.requireNonNull(recipe.getInstructions(), "Recipe instructions cannot be null");
        // return empty string if image is not present / null
        Objects.requireNonNullElse(recipe.getImagePath(), "");
        return recipeRepository.save(recipe);

    }

    @Override
    public RecipeDTO getRecipeById(Long id)
    {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid recipe id " + id));

        return new RecipeDTO(
                // temp id for now, may take out later
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getInstructions(),
                recipe.getImagePath()
        );
    }

    @Override
    public void updateRecipe(Long id, Recipe recipe)
    {
        // Recipe cannot be null
        Objects.requireNonNull(recipe, "Recipe cannot be null");
        // check whether the Recipe is in the database or not
        recipeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid recipe id " + id));
        recipe.setId(id);
        recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long id)
    {
        if (recipeRepository.existsById(id))
        {
            recipeRepository.deleteById(id);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find recipe id: " + id);
    }
}