package com.mb.application.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipe")
public class RecipeEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "url", length = 500)
    private String url;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

	// bi-directional many-to-one association to Ingredient
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipeid")
	public List<IngredientEntity> ingredients;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<IngredientEntity> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = ingredients;
	}
}