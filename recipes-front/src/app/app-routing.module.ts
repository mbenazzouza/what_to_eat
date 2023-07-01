import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipesComponent } from './recipes/recipes.component';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path:'dashboard', component:HomeComponent},
  {path:'recipes', component: RecipesComponent},
  {path:'recipes/detail/:id', component: RecipeDetailComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
