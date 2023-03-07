package com.mb.application.controller;

import com.mb.application.service.IngredientService;
import com.mb.server.api.IngredientsApi;
import com.mb.server.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${openapi.recipeManagement.base-path:/mb/v1}")
public class IngredientApiController implements IngredientsApi {

	@Autowired
	IngredientService is;

	public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
		Ingredient created = is.createIngredient(ingredient);
		return new ResponseEntity<>(created,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<Void> deleteIngredient(String id) {
		is.deleteRecipe(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	public ResponseEntity<List<Ingredient>> listIngredients(String fields, Integer offset, Integer limit, String name,Integer recipeId) {
		List<Ingredient> ingredients = is.listIngredients();
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
	}
	
	public ResponseEntity<Ingredient> retrieveIngredient(String id) {
		Ingredient ingredient = is.getIngredient(id);
		if (ingredient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ingredient, HttpStatus.OK);
		
	}
	
	public ResponseEntity<Ingredient> updateIngredient(String id, @RequestBody Ingredient ingredient) {
		int updatedId = is.updateIngredient(id, ingredient);
		return retrieveIngredient(Integer.toString(updatedId));
	}
}
