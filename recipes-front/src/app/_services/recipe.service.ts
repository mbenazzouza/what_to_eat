import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../recipes/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  params = new HttpParams({
    fromObject: {
      page: 0,
      size: 150,
    }
  });
  constructor(private httpClient: HttpClient) { }

  public getRecipes(): Observable<any> {
    return this.httpClient.get('/api/v1/recipes/', {params: this.params});
  }

  public getRecipe(id : number) : Observable<any> {
    return this.httpClient.get('/api/v1/recipes/' + id);
  }

  public saveRecipe(id: number, recipe: Recipe): Observable<any> {
    return this.httpClient.put('/api/v1/recipes/' + id, recipe);
  }
}
