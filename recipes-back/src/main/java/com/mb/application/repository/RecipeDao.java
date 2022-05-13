package com.mb.application.repository;

import com.mb.application.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeDao extends JpaRepository<RecipeEntity, Integer> {
	
}
