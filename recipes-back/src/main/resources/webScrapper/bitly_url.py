import bitlyshortener
import os

API_KEY = [os.environ.get('bitly_api_token')]


class BitlyAPI:
    def __int__(self, url):
        self.url = url

    @staticmethod
    def convert_long_url_to_bitly(url):
        shortener = bitlyshortener.Shortener(tokens=API_KEY)

        response = shortener.shorten_urls([url])[0]
        return response
