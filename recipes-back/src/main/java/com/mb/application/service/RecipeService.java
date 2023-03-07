package com.mb.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mb.application.entity.IngredientEntity;
import com.mb.application.entity.RecipeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mb.application.repository.IngredientDao;
import com.mb.application.repository.RecipeDao;
import com.mb.server.model.Ingredient;
import com.mb.server.model.Recipe;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeService {

	private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

	@Autowired
	RecipeDao recipeDao;

	@Autowired
	IngredientDao ingredientDao;

	public List<Recipe> listRecipes() {
		List<RecipeEntity> recipes = recipeDao.findAll();
		return recipes.stream().map(this::buildRecipeModel)
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
		recipeEntity.setUrl(recipe.getUrl());
		recipeEntity.setCategory(recipe.getCategory());

		RecipeEntity savedRecipe = recipeDao.save(recipeEntity);

		return buildRecipeModel(savedRecipe );

	}

	public int updateRecipe(String id, Recipe recipe) {
		RecipeEntity recipeEntity = new RecipeEntity();

		recipeEntity.setId(Integer.valueOf(id));
		recipeEntity.setName(recipe.getName());
		recipeEntity.setCategory(recipe.getCategory());

		return recipeDao.save(recipeEntity).getId();

	}

	@Transactional
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
		recipe.setCategory(recipeEntity.getCategory());
		recipe.setUrl(recipeEntity.getUrl());
		List<Ingredient> ingredients = ingredientDao.findByRecipeId(recipe.getId()).stream()
				.map(this::buildIngredientModel).collect(Collectors.toList());
		recipe.setIngredients(ingredients);


		return recipe;
	}

	private Ingredient buildIngredientModel(IngredientEntity ingredientEntity) {
		Ingredient ingredient = new Ingredient();

		ingredient.setId(ingredientEntity.getId());
		ingredient.setName(ingredientEntity.getName());
		ingredient.setSubtitle(ingredientEntity.getSubtitle());
		ingredient.setMeasure(ingredientEntity.getMeasure());

		return ingredient;
	}

}
