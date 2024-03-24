import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Recipe } from './recipe';
import { RecipeService } from '../_services/recipe.service';
import { Column } from './column';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

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
  
  imagePath!: SafeResourceUrl;

  recipes!: Recipe[];
  cols!: Column[];
  

  constructor(private recipeService: RecipeService, 
              private router: Router,
              private sanitizer: DomSanitizer
              ) {}

  ngOnInit(): void {
    this.recipeService.getRecipes().subscribe((data) => {
      console.log(data);
      this.recipes = data;
      this.recipes.forEach(recipe =>{
      recipe.imagePath = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + recipe.image);

      });
    });

    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'name', header: 'Name' },
      { field: 'category', header: 'Category' },
      { field: 'image', header: 'Image' },
  ];
    
  }
  

}
