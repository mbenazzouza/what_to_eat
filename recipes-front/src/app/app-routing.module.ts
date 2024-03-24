import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipesComponent } from './recipes/recipes.component';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';
import { HomeComponent } from './home/home.component';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { RelatedRecipesComponent } from './ingredients/related-recipes/related-recipes.component';

const routes: Routes = [
  {path:'dashboard', component:HomeComponent},
  {path:'recipes', component: RecipesComponent},
  {path:'recipes/detail/:id', component: RecipeDetailComponent},
  {path:'ingredients', component: IngredientsComponent},
  {path:'ingredients/:name/recipes', component: RelatedRecipesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
