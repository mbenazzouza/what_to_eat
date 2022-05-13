package com.mb.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "url", length = 500)
    private String url;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    public Integer getId() {
        return id;
    }

	// bi-directional many-to-one association to Ingredient
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipeid")
	public List<IngredientEntity> ingredients;

	@Column
	private String category;

	@Column
	private String url;

    public String getName() {
        return name;
    }

	public void setId(Integer id) {
		this.id = id;
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

    public Integer getId() {
        return id;
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<IngredientEntity> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = ingredients;
	}
}