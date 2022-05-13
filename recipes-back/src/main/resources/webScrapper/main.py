from recipe import Recipe

from ingredient import Ingredient
from instruction import Instruction
from tqdm import tqdm

recipes = Recipe.get_all_recipes()
if recipes:
    for recipe in tqdm(recipes):
        ingredient = Ingredient(str(recipe[2]))
        ingredient.get_recipe_ingredients()
        instruction = Instruction(str(recipe[2]))
        instruction.get_recipe_instructions()


