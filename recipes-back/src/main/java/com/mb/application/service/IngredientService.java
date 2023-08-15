package com.mb.application.service;

import com.github.jknack.handlebars.internal.lang3.StringUtils;
import com.mb.application.entity.IngredientEntity;
import com.mb.application.repository.IngredientDao;
import com.mb.application.repository.RecipeDao;
import com.mb.application.util.Util;
import com.mb.server.model.Ingredient;
import com.mb.server.model.Recipe;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    IngredientDao ingredientDao;

    @Autowired
    RecipeDao recipeDao;

    public List<Ingredient> listIngredients(String name) {

        List<Ingredient> ingredients;
        if (name != null) {
            ingredients = new ArrayList<>(ingredientDao.findByNameIgnoreCase(name).stream()
                    .map(this::buildIngredientModel).filter(Util.distinctByKey(Ingredient::getRecipeId)).toList());
        } else {
            ingredients = new ArrayList<>(ingredientDao.findAll().stream()
                    .map(this::buildIngredientModel).filter(Util.distinctByKey(Ingredient::getRecipeId)).toList());
        }

        ingredients
                .removeAll(ingredients.stream().filter(e -> !StringUtils.isAlpha(e.getName())).toList());

        ingredients.forEach(i -> {
            i.setName(i.getName().substring(0, 1).toUpperCase() + i.getName().substring(1));

        });

        return ingredients;
    }

    public Ingredient getIngredient(String id) {
        Optional<IngredientEntity> ingredientEntity = ingredientDao.findById(Integer.valueOf(id));
        return ingredientEntity.map(this::buildIngredientModel).orElse(null);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setName(ingredient.getName());
        ingredientEntity.setMeasure(ingredient.getMeasure());
        ingredientEntity.setSubtitle(ingredient.getSubtitle());

        IngredientEntity created = ingredientDao.save(ingredientEntity);
        return buildIngredientModel(created);
    }

    public int updateIngredient(String id, Ingredient ingredient) {
        IngredientEntity ingredientEntity = new IngredientEntity();

        ingredientEntity.setId(Integer.valueOf(id));
        ingredientEntity.setName(ingredient.getName());
        ingredientEntity.setSubtitle(ingredient.getSubtitle());
        ingredientEntity.setMeasure(ingredient.getMeasure());

        return ingredientDao.save(ingredientEntity).getId();

    }

    public void deleteRecipe(String id) {
        Ingredient recipe = getIngredient(id);
        if (recipe == null) {
            return;
        }
        ingredientDao.deleteById(Integer.valueOf(id));
    }

    public Ingredient buildIngredientModel(IngredientEntity ingredientEntity) {
        Ingredient ingredient = new Ingredient();

        ingredient.setId(ingredientEntity.getId());
        ingredient.setName(ingredientEntity.getName());
        ingredient.setMeasure(ingredientEntity.getMeasure());
        ingredient.setSubtitle(ingredientEntity.getSubtitle());
        ingredient.setRecipeId(ingredientEntity.getRecipe().getId());

        return ingredient;
    }

}
