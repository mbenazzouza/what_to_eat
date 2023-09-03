import { Component, Input, OnInit } from '@angular/core';
import { RecipeService } from '../_services/recipe.service';
import { Recipe } from '../recipes/recipe';
import { ActivatedRoute } from '@angular/router';
import { Ingredient } from '../ingredients/ingredient';
import { Instruction } from '../recipes/instruction';
import { DomSanitizer } from '@angular/platform-browser';


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
  imagePath: any;

  loading = true;
  constructor(private recipeService: RecipeService, 
              private route: ActivatedRoute,
              private sanitizer: DomSanitizer) {}


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = +params['id']
    });
    
    this.recipeService.getRecipe(this.id).subscribe((data) => {
      this.recipe = data;
      console.log(this.recipe);
      this.imagePath = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' 
                 + this.recipe.image);
      this.loading = false;
    })
  }
}
