package org.michaelbae.recipeplanner.api.controller;

import org.michaelbae.recipeplanner.api.dto.RecipeDTO;
import org.michaelbae.recipeplanner.model.Recipe;
import org.michaelbae.recipeplanner.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {


    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public List<RecipeDTO> getRecipes()
    {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipe(@PathVariable Long id)
    {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public String addRecipe(@RequestBody Recipe recipe) {
        System.out.println("Recipe received: " + recipe);
        recipeService.addRecipe(recipe);
        return "Success and added recipe";
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> editRecipe(@PathVariable Long id, @RequestBody Recipe recipe)
    {
        recipeService.updateRecipe(id, recipe);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id)
    {
        recipeService.deleteRecipe(id);
    }
}