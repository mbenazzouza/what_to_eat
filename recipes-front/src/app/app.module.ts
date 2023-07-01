import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipesComponent } from './recipes/recipes.component';

import { HttpClientModule } from '@angular/common/http';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';
import { HomeComponent } from './home/home.component';
import { PanelModule } from 'primeng/panel';

@NgModule({
  declarations: [
    AppComponent,
    RecipesComponent,
    IngredientsComponent,
    RecipeDetailComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    BrowserAnimationsModule,
    PanelModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
