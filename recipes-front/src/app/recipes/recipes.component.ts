import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Recipe } from './recipe';
import { RecipeService } from '../_services/recipe.service';
import { Column } from './column';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})

export class RecipesComponent implements OnInit {

  params = new HttpParams({
    fromObject: {
      page: 0,
      size: 150,
    }
  });

  recipes!: Recipe[];
  
  cols!: Column[];
  

  constructor(private recipeService: RecipeService) {}

  ngOnInit(): void {
    this.recipeService.getRecipes().subscribe((data) => {
      console.log(data);
      this.recipes = data;
    });

    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'name', header: 'Name' },
      { field: 'category', header: 'Category' },
      { field: 'page', header: 'Page' }
  ];
    
  }

  

}
