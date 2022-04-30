import json

from recipes import Recipes
from recipe import Recipe


# recipes_obj = Recipes('https://www.forksoverknives.com/recipes/page/{}/?type=grid')
# recipes_file_name = recipes_obj.get_recipes_from_url()
with open('forksoverknives-recipes.json', 'r') as file:
    data = json.load(file)
    for key in data:
        recipe = Recipe(data[key]['URL'])
        recipe.get_recipe_ingredients()
