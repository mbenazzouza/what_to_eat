package com.mb.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.application.entity.RecipeEntity;


@Repository
public interface RecipeDao extends JpaRepository<RecipeEntity, Integer> {
	
}
