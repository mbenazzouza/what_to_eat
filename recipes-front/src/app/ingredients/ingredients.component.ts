import { Component, OnInit } from '@angular/core';
import { IngredientService } from '../_services/ingredient.service';
import { Ingredient } from './ingredient';
import * as _ from 'lodash';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.scss']
})
export class IngredientsComponent implements OnInit {

  ingredients!: Ingredient[];
  uniqIngredients!: Ingredient[];

  constructor(private ingredientService: IngredientService) {}

  ngOnInit(): void {
      this.ingredientService.getAll().subscribe(data => {
        this.ingredients = data;
        this.uniqIngredients = _.sortedUniqBy(this.ingredients, 'name');
      })
  }

}
