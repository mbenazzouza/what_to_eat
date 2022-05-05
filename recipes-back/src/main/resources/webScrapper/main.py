import json

from recipe import Recipe

from ingredient import Ingredient
from tqdm import tqdm



# recipes_obj = Recipe('https://www.forksoverknives.com/recipes/page/{}/?type=grid')
# recipes_file_name = recipes_obj.get_recipes_from_url()
# with open('forksoverknives-recipes.json', 'r') as file:
#     data = json.load(file)
#     for key in data:
#         recipe = Recipe(data[key]['URL'])
#         recipe.get_recipe_ingredients()
recipes = Recipe.get_all_recipes()
if recipes:
    for recipe in tqdm(recipes):
        ingredient = Ingredient(str(recipe[2]))
        ingredient.get_recipe_ingredients()


