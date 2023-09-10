import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forEach, take } from 'lodash';
import { Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { Ingredient } from '../ingredients/ingredient';
import { Recipe } from '../recipes/recipe';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {


  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<any> {
    return this.httpClient.get('/api/v1/ingredients');
  }

  getIngredientsByName(name: string): Observable<any> {
    return this.httpClient.get('/api/v1/ingredients', {params: {name: name}});
  }

}
