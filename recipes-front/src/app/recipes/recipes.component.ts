import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe';
import { RECIPES } from '../mock-recipes';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {
  recipes: Recipe[] = [];
  
  selectedRecipe?: Recipe;
  cols: any[] = [];

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.getRecipes();
    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'name', header: 'Name' },
      { field: 'category', header: 'Category' },
      { field: 'url', header: 'URL' }
  ];
  }

  getRecipes(): void {
    this.recipeService.getAllRecipes().subscribe( (data) => {
      if (data) {
        this.recipes = data;
      }
    });
  }

onSelect(recipe: Recipe): void {
  this.selectedRecipe = recipe;
}

}
