package org.michaelbae.recipeplanner.api.controller;

import org.michaelbae.recipeplanner.api.dto.RecipeDTO;
import org.michaelbae.recipeplanner.model.Recipe;
import org.michaelbae.recipeplanner.service.FileStorageService;
import org.michaelbae.recipeplanner.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/recipes")
public class RecipeController
{


    private RecipeService recipeService;
    private FileStorageService fileStorageService;

    @Autowired
    public RecipeController(RecipeService recipeService, FileStorageService fileStorageService)
    {
        this.recipeService = recipeService;
        this.fileStorageService = fileStorageService;
    }

//    @GetMapping()
//    public List<RecipeDTO> getRecipes()
//    {
//        return recipeService.getAllRecipes();
//    }

    //    get existing recipes to show in cards on the page
    @GetMapping
    public String getRecipeHome(Model model)
    {
        List<RecipeDTO> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipes/recipe-home";
    }

    @GetMapping("/new")
    public String getRecipeForm(Model model)
    {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "recipes/recipe-form";
    }

    @PostMapping("/save")
    public String createOrUpdateRecipe(@ModelAttribute Model model, Recipe recipe, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile)
    {
        // Handle image upload if present
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = fileStorageService.storeFile(imageFile);
            recipe.setImagePath(imagePath);
        }


        // if the task exists, update, if not, make a new task
        if (recipe.getId() != null)
        {
            recipeService.updateRecipe(recipe.getId(), recipe);
        } else
        {
            recipeService.addRecipe(recipe);
        }
        return "redirect:/recipes";
    }


    @GetMapping("/edit/{id}")
    public String editRecipe(@PathVariable Long id, Model model)
    {
        RecipeDTO recipeDTO = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipeDTO);
        return "recipes/recipe-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id)
    {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes";
    }
//    @PostMapping
//    public String addRecipe(@RequestBody Recipe recipe) {
//        System.out.println("Recipe received: " + recipe);
//        recipeService.addRecipe(recipe);
//        return "Success and added recipe";
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<RecipeDTO> editRecipe(@PathVariable Long id, @RequestBody Recipe recipe)
//    {
//        recipeService.updateRecipe(id, recipe);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteRecipe(@PathVariable Long id)
//    {
//        recipeService.deleteRecipe(id);
//    }
}