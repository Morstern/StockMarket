import sys
import requests
import re
from bs4 import BeautifulSoup

output_string = ""

def get_isin_numbers():
    return sys.argv[1].split(',')

def get_soup(isin):
    return BeautifulSoup(get_stock_data_html("https://www.gpw.pl/spolka?isin="+isin), 'html.parser')

def get_stock_data_html(isin):
    return requests.get(isin).text

def get_prices(soup):
    rows = soup.find("table",{"class": "margin-bottom-0"})
    prices = []
    for wrapper in rows.find_all_next("td",{"align": "right"})[0:2]:
        prices.append(wrapper.text) 
    return prices

if __name__ == "__main__":
    isin_numbers = get_isin_numbers()
    for isin in isin_numbers:
        soup = get_soup(isin)
        [sellout, buyout] = get_prices(soup)
        output_string = output_string + isin + ":" + sellout + ":" + buyout + ";"
    
    print(str(output_string))

        
