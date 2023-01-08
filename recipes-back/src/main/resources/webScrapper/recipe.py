import json
import os

from bs4 import BeautifulSoup
import requests

from database import select, insert


class Recipe:

    def __init__(self, url):
        self.url = url

    recipes_dico = {}

    def put_recipe_in_dico(self, recipe, i):
        if recipe.h3 and recipe.h3.a:
            dico = {}
            recipe_category = recipe.find('div', class_='category').text if recipe.find('div', class_='category') \
                else 'Misc'
            recipe_name = recipe.h3.text
            recipe_url = recipe.h3.a['href']
            # creating a new dictionary
            dico['No'] = i
            dico['Name'] = recipe_name
            dico['Category'] = recipe_category
            dico['URL'] = recipe_url[:-1]

            # converting urls to bitly in order to avoid too long urls for column limit
            # url = BitlyAPI.convert_long_url_to_bitly(recipe_url[:-1])
            # setting-up database for insert
            select_recipe = "SELECT * FROM recipe WHERE name = ? OR url = ?;"
            select_values = (recipe_name, recipe_url[:-1])
            recipes = select(select_recipe, select_values)
            if not recipes:
                insert_recipe = "INSERT INTO recipe (name, url, category) VALUES (?, ?, ?);"
                insert_values = (recipe_name, recipe_url[:-1], recipe_category)
                insert(insert_recipe, insert_values)

            self.recipes_dico[str(i)] = dico

    def get_recipes_from_url(self):
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/56.0.2924.76 Safari/537.36'}  # This is chrome, you can set whatever browser you like

        num = 1
        url = self.url.format(num)
        status_code = requests.get(url, headers=headers).status_code

        i = 1
        while status_code == 200:
            print('page number:', num)
            print('url:', url)
            html_text = requests.get(url, headers=headers).text

            soup = BeautifulSoup(html_text, 'lxml')
            recipes_html_tags = soup.find_all('div', class_='post-item')

            if len(recipes_html_tags) == 1 and recipes_html_tags[0].p.text == 'Recipes not found.':
                break
            else:
                for recipe in recipes_html_tags:
                    self.put_recipe_in_dico(recipe, i)
                    i += 1
            num += 1
            url = 'https://www.forksoverknives.com/recipes/page/{}/?type=grid'.format(num)
            status_code = requests.get(url, headers=headers).status_code

        try:
            os.remove("recipes.json")
        except OSError:
            pass
        file_name = (url.split('www.'))[1].split('.com')[0] + '-recipes.json'
        with open(file_name, 'w') as convert_file:
            convert_file.write(json.dumps(self.recipes_dico))
        return file_name

    @staticmethod
    def get_all_recipes():
        select_recipe = "SELECT * FROM recipe;"
        recipes = select(select_recipe, [])
        return recipes
