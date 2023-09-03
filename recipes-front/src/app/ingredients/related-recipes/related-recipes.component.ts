import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IngredientService } from 'src/app/_services/ingredient.service';
import { Ingredient } from '../ingredient';
import { RecipeService } from 'src/app/_services/recipe.service';
import { Recipe } from 'src/app/recipes/recipe';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-related-recipes',
  templateUrl: './related-recipes.component.html',
  styleUrls: ['./related-recipes.component.scss']
})
export class RelatedRecipesComponent implements OnInit  {
  
  name !: string;
  relatedRecipes : Recipe[] = [];
  loading = true;
  
  constructor(private route: ActivatedRoute,
              private ingredientService: IngredientService,
              private recipeService: RecipeService) {

    this.route.params.subscribe(async (params) => {
      this.name = params['name']
      console.log(this.name);
      this.ingredientService.getIngredientsByName(this.name).subscribe((ingredients: Ingredient[]) => {
        ingredients.forEach(ingredient => {
          this.recipeService.getRecipe(ingredient.recipe_id).subscribe((recipe: Recipe) => {
            this.relatedRecipes.push(recipe);
          });
        });
        
      });
    console.log(this.relatedRecipes);
    this.loading = false;
    console.log(this.loading)
    });
              }

  ngOnInit(): void {
    
  }

  
}
