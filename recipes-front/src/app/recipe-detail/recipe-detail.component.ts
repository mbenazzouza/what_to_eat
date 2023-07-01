import { Component, Input, OnInit } from '@angular/core';
import { RecipeService } from '../_services/recipe.service';
import { Recipe } from '../recipes/recipe';
import { ActivatedRoute } from '@angular/router';
import { Ingredient } from '../ingredients/ingredient';
import { Instruction } from '../recipes/instruction';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.scss']
})


export class RecipeDetailComponent implements OnInit {
  
  id!: number;
  recipe!: Recipe;
  ingredients!: Ingredient[];
  instructions!: Instruction[];

  loading = true;
  constructor(private recipeService: RecipeService, 
              private route: ActivatedRoute) {}


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      console.log("TEST");
      this.id = +params['id']
    });
    
    this.recipeService.getRecipe(this.id).subscribe((data) => {
      this.recipe = data;
      this.ingredients = this.recipe.ingredients;
      this.instructions = this.recipe.instructions;
      console.log(this.instructions)
      this.loading = false;
    })
  }
}
