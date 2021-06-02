package com.mb.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mb.application.entity.IngredientEntity;

@Repository
public interface IngredientDao extends JpaRepository<IngredientEntity, Integer>  {
	
	@Query(value="select i from IngredientEntity i where i.recipe.id = :id ")
	List<IngredientEntity> findByRecipeId(Integer id);
	
}
