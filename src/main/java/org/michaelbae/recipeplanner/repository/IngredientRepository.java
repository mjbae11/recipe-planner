package org.michaelbae.recipeplanner.repository;

import org.michaelbae.recipeplanner.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}