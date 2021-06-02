package com.mb.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
public class RecipeService {

	private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

	@Autowired
	RecipeDao recipeDao;

	@Autowired
	IngredientDao ingredientDao;

	public List<Recipe> listRecipes() {

		return recipeDao.findAll(Sort.by("id")).stream().map(r -> this.buildRecipeModel(r))
				.collect(Collectors.toList());
	}

	public Recipe getRecipe(String id) {
		Optional<RecipeEntity> recipeEntity = recipeDao.findById(Integer.valueOf(id));
		if (recipeEntity.isEmpty()) {
			return null;
		}
		return this.buildRecipeModel(recipeEntity.get());
	}

	public Recipe createRecipe(Recipe recipe) {

		RecipeEntity recipeEntity = new RecipeEntity();
		recipeEntity.setId(recipe.getId());
		recipeEntity.setName(recipe.getName());
		recipeEntity.setDescription(recipe.getDescription());
		if (recipe.getImagePng() != null) {
			try {
				InputStream io = recipe.getImagePng().getInputStream();
				byte[] targetArray = new byte[io.available()];
				io.read(targetArray);
				recipeEntity.setImage(targetArray);
			} catch (IOException e) {
				LOG.error("The image was not retrieved: \n" + e.getMessage());
			}
		}
		if (!recipe.getIngredients().isEmpty() || recipe.getIngredients().get(0).getId() != null) {
			List<Integer> ingredientIds = recipe.getIngredients().stream().map(i -> i.getId())
					.collect(Collectors.toList());
			List<IngredientEntity> ingredients = ingredientDao.findAllById(ingredientIds);
			if (!ingredients.isEmpty()) {
				recipeEntity.setIngredients(ingredients);
			}
		}
		RecipeEntity saved = recipeDao.save(recipeEntity);

		return buildRecipeModel(saved);

	}

	public int updateRecipe(String id, Recipe recipe) {
		RecipeEntity recipeEntity = new RecipeEntity();

		recipeEntity.setId(Integer.valueOf(id));
		recipeEntity.setName(recipe.getName());
		recipeEntity.setDescription(recipe.getDescription());
		if (recipe.getImagePng() != null) {
			try {
				InputStream io = recipe.getImagePng().getInputStream();
				byte[] targetArray = new byte[io.available()];
				io.read(targetArray);
				recipeEntity.setImage(targetArray);
			} catch (IOException e) {
				LOG.error("The image was not retrieved: \n" + e.getMessage());
			}
		}
		int updatedId = recipeDao.save(recipeEntity).getId();
		return updatedId;

	}

	public void deleteRecipe(String id) {
		Recipe recipe = getRecipe(id);
		if (recipe == null) {
			return;
		}
		recipeDao.deleteById(Integer.valueOf(id));
	}

	private Recipe buildRecipeModel(RecipeEntity recipeEntity) {
		Recipe recipe = new Recipe();

		recipe.setId(recipeEntity.getId());
		recipe.setName(recipeEntity.getName());
		recipe.setDescription(recipeEntity.getDescription());
		List<Ingredient> ingredients = ingredientDao.findByRecipeId(recipe.getId()).stream()
				.map(i -> buildIngredientModel(i)).collect(Collectors.toList());
		recipe.setIngredients(ingredients);

		return recipe;
	}

	private Ingredient buildIngredientModel(IngredientEntity ingredientEntity) {
		Ingredient ingredient = new Ingredient();

		ingredient.setId(ingredientEntity.getId());
		ingredient.setName(ingredientEntity.getName());

		return ingredient;
	}

}
