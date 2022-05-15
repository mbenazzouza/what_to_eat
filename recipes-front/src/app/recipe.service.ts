import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Recipe } from './recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  recipes: Recipe[] = [];

  constructor(private http: HttpClient) { }


  getAllRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>('/mb/v1/recipes');
  }

  getRecipe(id: number): Observable<Recipe> {
    return this.http.get<Recipe>('/mb/v1/recipes/' + id);
  }
}
