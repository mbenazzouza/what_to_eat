package com.mb.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mb.application.entity.IngredientEntity;
import com.mb.application.entity.RecipeEntity;
import com.mb.application.repository.IngredientDao;
import com.mb.application.repository.RecipeDao;
import com.mb.server.model.Ingredient;
import com.mb.server.model.Recipe;

@Service
public class IngredientService {

	private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

	@Autowired
	IngredientDao ingredientDao;

	@Autowired
	RecipeDao recipeDao;

	public List<Ingredient> listIngredients() {

		return ingredientDao.findAll().stream().map(r -> this.buildIngredientModel(r))
				.collect(Collectors.toList());
	}

	public Ingredient getIngredient(String id) {
		Optional<IngredientEntity> ingredientEntity = ingredientDao.findById(Integer.valueOf(id));
		if (ingredientEntity.isEmpty()) {
			return null;
		}
		return this.buildIngredientModel(ingredientEntity.get());
	}

	public Ingredient createIngredient(Ingredient ingredient) {
		IngredientEntity ingredientEntity = new IngredientEntity();
		ingredientEntity.setName(ingredient.getName());
		if (ingredient.getImagePng() != null) {
			try {
				ingredientEntity.setImage(ingredient.getImagePng().getInputStream().readAllBytes());
			} catch (IOException e) {
				LOG.error("The ingredient image couldn't be retrieved \n" + e.getMessage());
			}
		}
		if (!ingredient.getRecipes().isEmpty() || ingredient.getRecipes().get(0).getId() != null) {
			Optional<RecipeEntity> recipe = recipeDao.findById(ingredient.getRecipes().get(0).getId());
			if (recipe.isPresent()) {
				ingredientEntity.setRecipe(recipe.get());
			}
		}
		IngredientEntity created = ingredientDao.save(ingredientEntity);
		return buildIngredientModel(created);
	}

	public int updateIngredient(String id, Ingredient ingredient) {
		IngredientEntity ingredientEntity = new IngredientEntity();

		ingredientEntity.setName(ingredient.getName());
		if (ingredient.getImagePng() != null) {
			try {
				InputStream io = ingredient.getImagePng().getInputStream();
				byte[] targetArray = new byte[io.available()];
				io.read(targetArray);
				ingredientEntity.setImage(targetArray);
			} catch (IOException e) {
				LOG.error("The image was not retrieved: \n" + e.getMessage());
			}
		}
		int updatedId = ingredientDao.save(ingredientEntity).getId();
		return updatedId;

	}

	public void deleteRecipe(String id) {
		Ingredient recipe = getIngredient(id);
		if (recipe == null) {
			return;
		}
		ingredientDao.deleteById(Integer.valueOf(id));
	}

	private Ingredient buildIngredientModel(IngredientEntity ingredientEntity) {
		Ingredient ingredient = new Ingredient();

		ingredient.setId(ingredientEntity.getId());
		ingredient.setName(ingredientEntity.getName());
		Recipe recipe = new Recipe();
		recipe.setId(ingredientEntity.getRecipe().getId());
		recipe.setName(ingredientEntity.getRecipe().getName());
		ingredient.addRecipesItem(recipe);

		return ingredient;
	}

}
