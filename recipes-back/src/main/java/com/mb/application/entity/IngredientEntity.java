package com.mb.application.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ingredient")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "measure", length = 100)
    private String measure;

    @Column(name = "subtitle", length = 200)
    private String subtitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeid")
    public RecipeEntity recipe;

    @Column(name = "recipe_id", length = 200)
    private String recipe_id;
}