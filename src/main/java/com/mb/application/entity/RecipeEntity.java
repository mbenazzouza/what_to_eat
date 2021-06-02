package com.mb.application.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the recipe database table.
 * 
 */
@Entity
@Table(name = "recipe")
public class RecipeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 2147483647)
	private String description;

	private byte[] image;

	@Column(nullable = false, length = 2147483647)
	private String name;

	// bi-directional many-to-one association to Ingredient
	@OneToMany(mappedBy = "recipe")
	private List<IngredientEntity> ingredients;

	public RecipeEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IngredientEntity> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = ingredients;
	}

	public IngredientEntity addIngredient(IngredientEntity ingredient) {
		getIngredients().add(ingredient);
		ingredient.setRecipe(this);

		return ingredient;
	}

	public IngredientEntity removeIngredient(IngredientEntity ingredient) {
		getIngredients().remove(ingredient);
		ingredient.setRecipe(null);

		return ingredient;
	}
}