import { Component, Input, OnInit } from '@angular/core';
import { RecipeService } from '../_services/recipe.service';
import { Recipe } from '../recipes/recipe';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.scss']
})


export class RecipeDetailComponent implements OnInit {
  

  @Input()
  id!: number;

  recipe!: Recipe;

  constructor(private recipeService: RecipeService) {}


  ngOnInit(): void {

    this.recipeService.getRecipe(this.id).subscribe((data) => {
      this.recipe = data;
    })
  }
}
