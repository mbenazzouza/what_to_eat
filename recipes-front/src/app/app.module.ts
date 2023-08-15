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
import { RelatedRecipesComponent } from './ingredients/related-recipes/related-recipes.component';
import {MatCardModule} from '@angular/material/card';

import {MatButtonModule} from '@angular/material/button';

@NgModule({
  declarations: [
    AppComponent,
    RecipesComponent,
    IngredientsComponent,
    RecipeDetailComponent,
    HomeComponent,
    RelatedRecipesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    BrowserAnimationsModule,
    PanelModule,
    MatCardModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
