import json

from bs4 import BeautifulSoup
import requests


def get_ingredient_name_and_measure(dico, i, ingredient_dico, ingredient_name):
    if not ingredient_name.text[0].isalpha():
        chars = ingredient_name.text.split(' ')
        measure = ' '.join(chars[0:2])
        ingredient_name_without_measure = ' '.join(chars[2:])
        ingredient_dico['measure'] = measure
        ingredient_dico['name'] = ingredient_name_without_measure
        dico[i] = ingredient_dico
    else:
        ingredient_name_without_measure = ingredient_name.text
        ingredient_dico['name'] = ingredient_name_without_measure
        dico[i] = ingredient_dico


def get_subtitles(dico, ingredients, subsection_ingredients, subtitles):
    if subtitles:
        dico['name'] = subtitles[subsection_ingredients.index(ingredients)].text
        print(dico['name'])
    ingredient = ingredients.find_all('li')
    return ingredient


class Recipe:

    def __init__(self, url):
        self.url = url

    recipe_dico = {}

    def get_recipe_ingredients(self):
        dico, i, subsection_ingredients, subtitles = self.define_constants()
        if subsection_ingredients:
            for ingredients in subsection_ingredients:

                ingredient = get_subtitles(dico, ingredients, subsection_ingredients, subtitles)

                for ingredient_name in ingredient:
                    ingredient_dico = {}
                    get_ingredient_name_and_measure(dico, i, ingredient_dico, ingredient_name)

                    i += 1
                    self.recipe_dico['ingredients'] = dico

            self.write_recipe_in_json_file()

    def write_recipe_in_json_file(self):
        recipe_name = self.url.split('/')[-1]
        file_name = recipe_name + '.json'
        with open(file_name, 'w') as convert_file:
            convert_file.write(json.dumps(self.recipe_dico))

    def define_constants(self):
        global subsection_ingredients, subtitles
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/56.0.2924.76 Safari/537.36'}  # This is chrome, you can set whatever browser you like
        html_page = requests.get(self.url, headers=headers).text
        soup = BeautifulSoup(html_page, 'lxml')
        recipe_ingredients = soup.find('section', class_='section-ingredients')
        if recipe_ingredients:
            subtitles = recipe_ingredients.find_all('b', class_='subtitle')
            subsection_ingredients = recipe_ingredients.find_all('ul')
        i = 0
        dico = {}
        return dico, i, subsection_ingredients, subtitles


recipe = Recipe('https://www.forksoverknives.com/recipes/vegan-desserts/outrageously-healthy-brownies')
recipe.get_recipe_ingredients()

